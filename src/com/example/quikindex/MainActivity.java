package com.example.quikindex;

import java.util.ArrayList;
import java.util.Collections;

import com.example.quikindex.adapter.PersonAdapter;
import com.example.quikindex.bean.Person;
import com.example.quikindex.ui.QuikIndexBar;
import com.example.quikindex.ui.QuikIndexBar.OnLetterUpdateListener;
import com.example.quikindex.util.Cheeses;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView mMainList;
	private ArrayList<Person> persons;
	private TextView tvCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 设置监听
		QuikIndexBar bar = (QuikIndexBar) findViewById(R.id.bar);
		bar.setListener(new OnLetterUpdateListener() {

			@Override
			public void onLetterUpdate(String letter) {
				// ToastUtils.showToastShort(MainActivity.this, letter);

				showLetter(letter);
				// 根据字母定位Listview,找到集合中第一个以letter为拼音首字母的对象，得到索引
				for (int i = 0; i < persons.size(); i++) {
					Person person = persons.get(i);
					String l = person.getPinyin().charAt(0) + "";
					if (TextUtils.equals(letter, l)) {
						// 匹配成功
						mMainList.setSelection(i);
						break;
					}
				}
			}

		});

		mMainList = (ListView) findViewById(R.id.lv_main);

		persons = new ArrayList<Person>();

		// 填充数据并排序
		fillAndSortData(persons);

		mMainList.setAdapter(new PersonAdapter(MainActivity.this, persons));
		tvCenter = (TextView) findViewById(R.id.tv_center);
	}

	private Handler mHandler = new Handler();

	private void showLetter(String letter) {
		tvCenter.setVisibility(View.VISIBLE);
		tvCenter.setText(letter);
		mHandler.removeCallbacksAndMessages(null);
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				tvCenter.setVisibility(View.GONE);
			}
		}, 2000);
	}

	private void fillAndSortData(ArrayList<Person> persons) {
		// 填充数据
		for (int i = 0; i < Cheeses.NAMES.length; i++) {
			String name = Cheeses.NAMES[i];
			persons.add(new Person(name));
		}
		// 进行排序
		Collections.sort(persons);
	}

}
