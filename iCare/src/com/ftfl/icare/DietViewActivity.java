package com.ftfl.icare;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.icare.adapter.DietAdapter;
import com.ftfl.icare.database.DietDataSource;
import com.ftfl.icare.model.DietModel;

public class DietViewActivity extends Activity {

	// Variable Declaration
	ImageView mAddDiet = null;
	ListView mDietList = null;
	TextView mViewAll = null;
	TextView mDietIDTV = null;
	DietModel mDietModel = null;
	DietDataSource mDietDataSource = null;
	ArrayList<DietModel> mModel = null;
	DietAdapter mAdapter = null;
	String msDietID = "";
	int mDietID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diet_list_today);

		// definition - gives variable a reference
		mAddDiet = (ImageView) findViewById(R.id.adddiet);
		mDietList = (ListView) findViewById(R.id.todaydiet);
		mViewAll = (TextView) findViewById(R.id.viewall);
		mDietDataSource = new DietDataSource(this);
		mModel = mDietDataSource.getTodayDietList();

		mAdapter = new DietAdapter(this, mModel);
		mDietList.setAdapter(mAdapter);

		// OnClick Activity of each row of list
		mDietList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mDietIDTV = (TextView) view.findViewById(R.id.tvDietID);
				msDietID = mDietIDTV.getText().toString();
				mDietID = Integer.parseInt(msDietID);

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						DietViewActivity.this);

				// Setting "Update" Button
				alertDialog.setPositiveButton("edit",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Creating Bundle object
								Bundle mBundle = new Bundle();

								// put id into bundle
								mBundle.putInt("id", mDietID);
								Intent iIntent = new Intent(
										DietViewActivity.this,
										DietAddActivity.class);

								// Storing bundle object into intent
								iIntent.putExtras(mBundle);
								startActivity(iIntent);

								// Remove activity
								finish();
								dialog.cancel();
							}
						});
				// Setting "Delete" Button
				alertDialog.setNegativeButton("delete",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to invoke NO event
								AlertDialog.Builder iDeleteAlert = new AlertDialog.Builder(
										DietViewActivity.this);

								// set dialog message
								iDeleteAlert
										.setMessage("Want to delete?")
										.setCancelable(false)
										.setPositiveButton(
												"Yes",
												new DialogInterface.OnClickListener() {
													public void onClick(
															DialogInterface dialog,
															int id) {
														// if this button is
														// clicked item
														// will be deleted

														mDietDataSource
																.deleteDietData(mDietID);
														Intent iIntent = new Intent(
																DietViewActivity.this,
																DietViewActivity.class);
														startActivity(iIntent);

														Toast.makeText(
																DietViewActivity.this,
																"data deleted",
																Toast.LENGTH_SHORT)
																.show();

														dialog.cancel();
														finish();
													}
												})
										.setNegativeButton(
												"No",
												new DialogInterface.OnClickListener() {
													public void onClick(
															DialogInterface dialog,
															int id) {
														// if this button is
														// clicked, just close
														// the dialog box and do
														// nothing
														dialog.cancel();
													}
												});

								// create alert dialog
								AlertDialog alertDialog = iDeleteAlert.create();

								// show it
								alertDialog.show();
							}
						});
				// Showing Alert Message
				alertDialog.show();
			}
		});

		// OnClick Activity of view
		mViewAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent iIntent = new Intent(DietViewActivity.this,
						DietListActivity.class);
				startActivity(iIntent);
			}
		});

		// OnClick Activity of add view
		mAddDiet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent iIntent = new Intent(DietViewActivity.this,
						DietAddActivity.class);
				startActivity(iIntent);
				finish();
			}
		});
	}
}
