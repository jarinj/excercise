package com.ftfl.icare.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ftfl.icare.R;


public class GeneralHealthInfoFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_general_health_info, container, false);
		try {
		
			String value = getArguments().getString("YourKey");
			
			if(value!=null){
		
				TextView te=(TextView) rootView.findViewById(R.id.textView1);
				te.setText(value);
			}
		} catch (Exception e) {
			// TODO: handle exception
	
		}
        return rootView;
        
	}
}
