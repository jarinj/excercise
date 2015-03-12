package com.ftfl.icare;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.icare.adapter.DietAdapter;
import com.ftfl.icare.database.DietDataSource;
import com.ftfl.icare.model.DietModel;

public class DietListActivity extends ActionBarActivity {
	
	// Variable Declaration
	ListView mAllDietList = null;	
	//TextView mDietIDTV = null;
	DietModel mDietModel = null;
	DietDataSource mDietDataSource = null;
	ArrayList<DietModel> mModel = null;
	DietAdapter mCustomAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diet_list);
		
		// definition - gives variable a reference
		mAllDietList =(ListView)findViewById(R.id.allDietList);
		mDietDataSource = new DietDataSource(this);
		mModel = mDietDataSource.getAllDietList();

		mCustomAdapter = new DietAdapter(this, mModel);
		mAllDietList.setAdapter(mCustomAdapter);
	}
}
