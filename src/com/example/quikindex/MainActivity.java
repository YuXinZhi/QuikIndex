package com.example.quikindex;

import com.example.quikindex.ui.QuikIndexBar;
import com.example.quikindex.ui.QuikIndexBar.OnLetterUpdateListener;
import com.example.quikindex.util.ToastUtils;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		QuikIndexBar bar = (QuikIndexBar) findViewById(R.id.bar);
		bar.setListener(new OnLetterUpdateListener() {

			@Override
			public void onLetterUpdate(String letter) {
				ToastUtils.showToastShort(MainActivity.this, letter);
			}
		});
	}

}
