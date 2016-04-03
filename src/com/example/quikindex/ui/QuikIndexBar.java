package com.example.quikindex.ui;

import com.example.quikindex.util.ToastUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 快速索引 用于根据字母快速定位联系人
 * 
 * @author Ben
 *
 */
public class QuikIndexBar extends View {

	private static final String[] LETTERS = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private Paint mPaint;
	private int cellWidth;
	private float cellHeight;
	private int touchIndex;

	public QuikIndexBar(Context context) {
		this(context, null);
	}

	public QuikIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public QuikIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.WHITE);
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mPaint.setTextSize(25);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 计算坐标
		// 绘制文本A-Z
		for (int i = 0; i < LETTERS.length; i++) {
			String letter = LETTERS[i];
			int x = (int) (cellWidth / 2.0f - mPaint.measureText(letter) / 2.0f);

			// 获取文本高度
			Rect bounds = new Rect();
			mPaint.getTextBounds(letter, 0, letter.length(), bounds);
			int letterHeight = bounds.height();
			int y = (int) (i * cellHeight + cellHeight / 2.0f + letterHeight / 2.0f);
			// 绘制文本A-Z
			canvas.drawText(letter, x, y, mPaint);

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int index = -1;
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			// 获取当前触摸到的字母索引
			index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < LETTERS.length) {
				// 判断是否和上次触摸到的一样
				if (index != touchIndex) {
					ToastUtils.showToastShort(getContext(), LETTERS[index]);
					touchIndex = index;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			// 获取当前触摸到的字母索引
			index = (int) (event.getY() / cellHeight);
			if (index >= 0 && index < LETTERS.length) {
				// 判断是否和上次触摸到的一样
				if (index != touchIndex) {
					ToastUtils.showToastShort(getContext(), LETTERS[index]);
					touchIndex = index;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			touchIndex = -1;
			break;

		default:
			break;
		}

		return super.onTouchEvent(event);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// 获取单元格宽度和高度
		int mHeight = getMeasuredHeight();
		cellWidth = getMeasuredWidth();
		cellHeight = mHeight / LETTERS.length;
	}

}
