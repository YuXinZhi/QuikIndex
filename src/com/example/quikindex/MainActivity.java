package com.example.quikindex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.example.quikindex.adapter.PersonAdapter;
import com.example.quikindex.bean.Person;
import com.example.quikindex.ui.QuikIndexBar;
import com.example.quikindex.ui.QuikIndexBar.OnLetterUpdateListener;
import com.example.quikindex.util.Cheeses;
import com.example.quikindex.util.ToastUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mMainList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 设置监听
		QuikIndexBar bar = (QuikIndexBar) findViewById(R.id.bar);
		bar.setListener(new OnLetterUpdateListener() {

			@Override
			public void onLetterUpdate(String letter) {
				ToastUtils.showToastShort(MainActivity.this, letter);
			}
		});

		mMainList = (ListView) findViewById(R.id.lv_main);

		// 存储人名和拼音集合
		ArrayList<Person> persons = new ArrayList<Person>();

		// 填充数据并排序
		fillAndSortData(persons);

		mMainList.setAdapter(new PersonAdapter(MainActivity.this, persons));
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
