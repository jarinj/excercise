package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.icare.R;
import com.ftfl.icare.model.DietModel;

public class DietAdapter extends ArrayAdapter<DietModel> {
	private Activity context = null;

	static class ViewHolder {
		public TextView id = null;
		public TextView feast = null;
		public TextView time = null;
		public TextView menu = null;
		public TextView date = null;
		public ImageView image = null;
	}

	ArrayList<DietModel> dietModelArray;

	public DietAdapter(Activity context,
			ArrayList<DietModel> dietModelArray) {
		super(context, R.layout.diet_list_row, dietModelArray);
		this.context = context;
		this.dietModelArray = dietModelArray;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.diet_list_row, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.id = (TextView) rowView.findViewById(R.id.tvDietID);
			viewHolder.feast = (TextView) rowView.findViewById(R.id.tvFeast);
			viewHolder.time = (TextView) rowView.findViewById(R.id.tvDietTime);
			viewHolder.menu = (TextView) rowView.findViewById(R.id.tvMenu);
			viewHolder.date = (TextView) rowView.findViewById(R.id.tvDietDate);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.ivAlarmImage);

			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.id.setText(dietModelArray.get(position).getmDietId().toString());
		holder.feast.setText(dietModelArray.get(position).getmFeast());
		holder.time.setText(dietModelArray.get(position).getmTime());
		holder.menu.setText(dietModelArray.get(position).getmMenu());
		holder.date.setText(dietModelArray.get(position).getmDate());
		
		String str = dietModelArray.get(position).getmAlarm();
		if(str != null && str.equals("true")){
			 holder.image.setVisibility(View.VISIBLE);
		}
		
		return rowView;

	}

}