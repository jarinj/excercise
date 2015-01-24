package com.ftfl.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class FTFLTextView extends TextView {
	String m_text = "Type name here";

	public FTFLTextView(Context context) {
		super(context);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		this.setText(m_text);
	}

	public FTFLTextView(Context context, AttributeSet as) {
		super(context, as);
		init();
	}

	public FTFLTextView(Context context, AttributeSet as, double defStyle) {
		super(context, as);
		init();
	}
}
