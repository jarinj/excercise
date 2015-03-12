package com.ftfl.icare;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.icare.adapter.VaccinationListAdapter;
import com.ftfl.icare.database.VaccinationDataSource;
import com.ftfl.icare.model.VaccinationModel;

public class VaccinationListActivity extends ActionBarActivity {

	// Variable Declaration
	//ImageView mAddVaccine = null;
	ListView mRemainingVacList = null;
	ListView mTakenVacList = null;
	TextView mVaccineIDTV = null;
	VaccinationModel mVaccinationModel = null;
	VaccinationDataSource mVaccinationDataSource = null;
	ArrayList<VaccinationModel> mRemainingModel = null;
	ArrayList<VaccinationModel> mTakenModel = null;
	VaccinationListAdapter mAdapter = null;
	VaccinationListAdapter mTakenAdapter = null;
	String msVaccineID = "";
	int mVaccineID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vaccine_list);

		// definition - gives variable a reference
		//mAddVaccine = (ImageView) findViewById(R.id.addvacc);
		mRemainingVacList = (ListView) findViewById(R.id.remainingvcclist);
		mTakenVacList = (ListView) findViewById(R.id.takenvcclist);

		mVaccinationDataSource = new VaccinationDataSource(this);

		mTakenModel = mVaccinationDataSource.getTakenVaccineList();
		mTakenAdapter = new VaccinationListAdapter(this, mTakenModel);
		mTakenVacList.setAdapter(mTakenAdapter);
		
		// OnClick Activity of each row
		mTakenVacList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						mVaccineIDTV = (TextView) view
								.findViewById(R.id.tvVaccineID);
						msVaccineID = mVaccineIDTV.getText().toString();
						mVaccineID = Integer.parseInt(msVaccineID);

						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								VaccinationListActivity.this);

						// Setting "Update" Button
						alertDialog.setPositiveButton("edit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// Creating Bundle object
										Bundle mBundle = new Bundle();

										// put id into bundle
										mBundle.putInt("id", mVaccineID);
										Intent iIntent = new Intent(
												VaccinationListActivity.this,
												VaccinationAddActivity.class);

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
										// Write your code here to invoke NO
										// event
										AlertDialog.Builder iDeleteAlert = new AlertDialog.Builder(
												VaccinationListActivity.this);

										// set dialog message
										iDeleteAlert
												.setMessage(
														"want to delete?")
												.setCancelable(false)
												.setPositiveButton(
														"Yes",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																// if this
																// button is
																// clicked item
																// will be
																// deleted

																mVaccinationDataSource
																		.deleteVaccineData(mVaccineID);
																Intent iIntent = new Intent(
																		VaccinationListActivity.this,
																		VaccinationListActivity.class);
																startActivity(iIntent);

																Toast.makeText(
																		VaccinationListActivity.this,
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
																// if this
																// button is
																// clicked, just
																// close
																// the dialog
																// box and do
																// nothing
																dialog.cancel();
															}
														});

										// create alert dialog
										AlertDialog alertDialog = iDeleteAlert
												.create();

										// show it
										alertDialog.show();
									}
								});
						// Showing Alert Message
						alertDialog.show();
					}
				});


		mRemainingModel = mVaccinationDataSource.getRemainingVaccineList();
		mAdapter = new VaccinationListAdapter(this, mRemainingModel);
		mRemainingVacList.setAdapter(mAdapter);

		// OnClick Activity of each row
		mRemainingVacList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						mVaccineIDTV = (TextView) view
								.findViewById(R.id.tvVaccineID);
						msVaccineID = mVaccineIDTV.getText().toString();
						mVaccineID = Integer.parseInt(msVaccineID);

						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								VaccinationListActivity.this);

						// Setting "Update" Button
						alertDialog.setPositiveButton("edit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// Creating Bundle object
										Bundle mBundle = new Bundle();

										// put id into bundle
										mBundle.putInt("id", mVaccineID);
										Intent iIntent = new Intent(
												VaccinationListActivity.this,
												VaccinationAddActivity.class);

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
										// Write your code here to invoke NO
										// event
										AlertDialog.Builder iDeleteAlert = new AlertDialog.Builder(
												VaccinationListActivity.this);

										// set dialog message
										iDeleteAlert
												.setMessage(
														"want to delete?")
												.setCancelable(false)
												.setPositiveButton(
														"Yes",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																// if this
																// button is
																// clicked item
																// will be
																// deleted

																mVaccinationDataSource
																		.deleteVaccineData(mVaccineID);
																Intent iIntent = new Intent(
																		VaccinationListActivity.this,
																		VaccinationListActivity.class);
																startActivity(iIntent);

																Toast.makeText(
																		VaccinationListActivity.this,
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
																// if this
																// button is
																// clicked, just
																// close
																// the dialog
																// box and do
																// nothing
																dialog.cancel();
															}
														});

										// create alert dialog
										AlertDialog alertDialog = iDeleteAlert
												.create();

										// show it
										alertDialog.show();
									}
								});
						// Showing Alert Message
						alertDialog.show();
					}
				});

		/*// OnClick Activity of add icon
		mAddVaccine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent iIntent = new Intent(VaccinationListActivity.this,
						VaccinationAddActivity.class);
				startActivity(iIntent);
				finish();
			}
		});*/
	}
}