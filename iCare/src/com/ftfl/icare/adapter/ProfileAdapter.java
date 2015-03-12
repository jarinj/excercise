package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icare.R;
import com.ftfl.icare.model.UserProfileModel;


public class ProfileAdapter extends ArrayAdapter<UserProfileModel> {
	Activity mContext;
	UserProfileModel mProfile;
	
	// Profile ArrayList
	ArrayList<UserProfileModel> mProfileArrayList;

	public ProfileAdapter(Activity context, ArrayList<UserProfileModel> eProfileArrayList) {
		super(context, R.layout.row_user_profile, eProfileArrayList);
		this.mContext = context;
		this.mProfileArrayList = eProfileArrayList;
	}

	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView mShowName;
		public TextView mShowDOB;
		public TextView mShowHeight;
		public TextView mShowWeight;
		public TextView mShowGender;		
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mProfile = mProfileArrayList.get(position);
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {
			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.row_user_profile, parent, false);

			// ViewHolder Object
			mVHolder = new ViewHolder();
			// findViewById
			mVHolder.mShowName = (TextView) rowView.findViewById(R.id.showName);
			mVHolder.mShowDOB = (TextView) rowView.findViewById(R.id.showDOB);
			mVHolder.mShowHeight = (TextView) rowView.findViewById(R.id.showHeight);
			mVHolder.mShowWeight = (TextView) rowView.findViewById(R.id.showWeight);
			mVHolder.mShowGender = (TextView) rowView.findViewById(R.id.showGender);

			// Set ViewHolder 
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();
			mVHolder.mShowName.setText(mProfile.getmName().toString());
			mVHolder.mShowDOB.setText(mProfile.getmDOB().toString());
			mVHolder.mShowHeight.setText(mProfile.getmHeight().toString());
			mVHolder.mShowWeight.setText(mProfile.getmWeight().toString());
			mVHolder.mShowGender.setText(mProfile.getmGender().toString());
			
		
		return rowView;
	}
}
