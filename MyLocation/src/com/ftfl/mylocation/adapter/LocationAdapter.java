package com.ftfl.mylocation.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract.Profile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.mylocation.R;
import com.ftfl.mylocation.model.LocationModel;

public class LocationAdapter extends ArrayAdapter<Profile> {
	Activity mContext;
	LocationModel mLocation;

	// Profile ArrayList
	ArrayList<LocationModel> mLocationArray;

	// Photo Object
	String mPhotoPath = null;
	Bitmap bitmap = null;

	public LocationAdapter(Activity context, ArrayList<LocationModel> eLocationArrayList) {
		super(context, R.layout.retrieve_row);
		this.mContext = context;
		this.mLocationArray = eLocationArrayList;
	}

	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public ImageView ivImage;
		public TextView mtvRemark;
		public TextView mtvDateTime;
		public TextView mtvDistance;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mLocation = mLocationArray.get(position);
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {
			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.retrieve_row, parent, false);
			mVHolder = new ViewHolder();
			mVHolder.ivImage = (ImageView) rowView
					.findViewById(R.id.myImage);
			mVHolder.mtvRemark = (TextView) rowView
					.findViewById(R.id.tvRemark);
	/*		mVHolder.mtvDateTime = (TextView) rowView
					.findViewById(R.id.tvSingleRowDateTime);*/
			mVHolder.mtvDistance = (TextView) rowView
					.findViewById(R.id.tvDistance);
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();
		mVHolder.mtvRemark.setText(mLocation.getmImage().toString());
		
		mVHolder.mtvDistance.setText(mLocation.getmDistance().toString());

		// Image Preview
		previewCapturedImage();
		mVHolder.ivImage.setImageBitmap(bitmap);
		return rowView;
	}

	/**
	 * Display image from a path to ImageView
	 */
	private void previewCapturedImage() {
		try {

			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();

			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 10;

			bitmap = BitmapFactory.decodeFile(mPhotoPath, options);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
