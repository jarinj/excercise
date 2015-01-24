package com.ftfl.customview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class FTFLImageView implements OnTouchListener{
	private int m_X;
	private int m_Y;

	public FTFLImageView() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int x = (int) event.getRawX();
		int y = (int) event.getRawY();

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v
					.getLayoutParams();
			m_X = x - lParams.leftMargin;
			m_Y = y - lParams.topMargin;
			break;

		case MotionEvent.ACTION_UP:

			break;

		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
					.getLayoutParams();
			layoutParams.leftMargin = x - m_X;
			layoutParams.topMargin = y - m_Y;
			layoutParams.rightMargin = -250;
			layoutParams.bottomMargin = -250;
			v.setLayoutParams(layoutParams);
			break;
		}

		return true;

	}

}
