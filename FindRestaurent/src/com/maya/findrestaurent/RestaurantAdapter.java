package com.maya.findrestaurent;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RestaurantAdapter extends ArrayAdapter<RestaurantModel>{
	
	private final Activity context;
	ArrayList<RestaurantModel> mRestaurant;
	
	static class ViewHolder {
		public TextView  name;	
	}
	
	public RestaurantAdapter(Activity context, ArrayList<RestaurantModel> eRestaurant) {
		super(context, R.layout.main_row, eRestaurant);
		this.context = context;
		this.mRestaurant = eRestaurant;
	
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.main_row, parent,false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView.findViewById(R.id.textView1);
		//	viewHolder.day = (TextView) rowView.findViewById(R.id.tvUpcomingDay);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		//holder.day.setText(objectArray.get(position).getMenu());
		holder.name.setText(mRestaurant.get(position).getName());

		// if(objectArray.get(position).getmIsAlarmChecked()==1){

		// holder.image.setImageResource(R.drawable.yess);
		// }

		return rowView;
	}

}
