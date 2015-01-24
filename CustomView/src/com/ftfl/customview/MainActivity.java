package com.ftfl.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private ImageView m_imageView;
	private ViewGroup m_rootLayout;

	private FTFLImageView ftflImageViewObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		ftflImageViewObj = new FTFLImageView();

		m_rootLayout = (ViewGroup) findViewById(R.id.root);
		m_imageView = (ImageView) m_rootLayout.findViewById(R.id.imageView);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				150, 150);
		m_imageView.setLayoutParams(layoutParams);
		m_imageView.setOnTouchListener(ftflImageViewObj);

	}

}
