package com.ftfl.icare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		setContentView(R.layout.splash);
		
			
		/****** Create Thread that will sleep for 3 seconds *************/
		Thread mSplash = new Thread() {
			public void run() {
				try {
					// Thread will sleep for 3 seconds
					sleep(1 * 1000);
					// After 3 seconds redirect to another intent
					Intent intent = new Intent(getBaseContext(), ICareInfoActivity.class);
					startActivity(intent);
					// Remove activity
					finish();
				} catch (Exception e) {
				}
			}
		};
		
		
		// start thread
		mSplash.start();
	}
}