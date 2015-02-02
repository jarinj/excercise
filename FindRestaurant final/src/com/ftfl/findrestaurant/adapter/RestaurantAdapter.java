package com.ftfl.findrestaurant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.findrestaurant.R;
import com.ftfl.findrestaurant.model.RestaurantModel;

public class RestaurantAdapter extends ArrayAdapter<RestaurantModel> {
	Activity mContext = null;
	RestaurantModel mRestaurant = null;
	ArrayList<RestaurantModel> mArrayObject = null;

	public RestaurantAdapter(Activity context,
			ArrayList<RestaurantModel> objectArray) {
		super(context, R.layout.home_row, objectArray);
		this.mContext = context;
		this.mArrayObject = objectArray;
	}

	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView name = null;
		public TextView startDate = null;
		public TextView id = null;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mRestaurant = mArrayObject.get(position);
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {

			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();

			rowView = inflater.inflate(R.layout.home_row, parent, false);

			mVHolder = new ViewHolder();
			mVHolder.id = (TextView) rowView.findViewById(R.id.tvID);
			mVHolder.name = (TextView) rowView.findViewById(R.id.tvShowName);
			mVHolder.startDate = (TextView) rowView
					.findViewById(R.id.tvShowDate);
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();

		mVHolder.id.setText(mRestaurant.getId().toString());
		mVHolder.startDate.setText(mRestaurant.getmStartDate().toString());
		mVHolder.name.setText(mRestaurant.getmName().toString());

		return rowView;
	}
}
