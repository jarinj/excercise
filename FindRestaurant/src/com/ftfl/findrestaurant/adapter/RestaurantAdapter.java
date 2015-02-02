package com.ftfl.findrestaurant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.findrestaurant.R;
import com.ftfl.findrestaurant.utils.RestaurantModel;

public class RestaurantAdapter extends ArrayAdapter<RestaurantModel>{
	private final Activity context;
	private ArrayList<RestaurantModel> restaurantArray;;

	static class ViewHolder {
		public TextView name,address;
		//public ImageView image;
	}

	/*
	 * private ArrayList<String> names; private ArrayList<String> times; private
	 * ArrayList<String> menus;
	 */

	public RestaurantAdapter(Activity context,
			ArrayList<RestaurantModel> res_info_obj) {
		super(context, R.layout.home_row, res_info_obj);
		this.context = context;
		this.restaurantArray = res_info_obj;
		/*
		 * this.names = names; this.times = times; this.menus = menus;
		 */
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (convertView == null) {
			// LayoutInflater
			// inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.home_row, parent,false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView.findViewById(R.id.tvRestaurantName);
			viewHolder.address = (TextView) rowView.findViewById(R.id.tvRestaurantAddress);
			
			//viewHolder.image = (ImageView) rowView.findViewById(R.id.restaurantImage);
			
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.name.setText(restaurantArray.get(position).getName());
		holder.address.setText(restaurantArray.get(position).getAddrsss());
		//holder.image.setImageResource(R.drawable.pic);
		
		return rowView;
	}
}

