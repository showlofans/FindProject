package com.stufinish.findproject.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.RenZheng;


public class RenZhenAdapter extends BaseAdapter {
	ArrayList<RenZheng>renzhenList  = new ArrayList<RenZheng>();
	Context context;
	TextView tv,tv2,tv3;
	
	public RenZhenAdapter(ArrayList<RenZheng> renzhenList, Context context) {
		super();
		this.renzhenList = renzhenList;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return renzhenList.size();
	}

	@Override
	public RenZheng getItem(int position) {
		return (renzhenList != null && renzhenList.size() > position) ? renzhenList.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(R.layout.adaper_renzhen, null);
		tv = (TextView)convertView.findViewById(R.id.tv_list_renzehn_item);
		tv2 = (TextView)convertView.findViewById(R.id.tv_renzhen_time_list);
		tv3 = (TextView)convertView.findViewById(R.id.tv_list_renzehn_flag);
		RenZheng renzhen = renzhenList.get(position);
		int flag = renzhen.getRenzhen_flag();
		String info= renzhen.getRenzhen_info();
		String time = renzhen.getRenzhen_time();
		tv.setText(info);
		tv2.setText(time);
		tv3.setText(flag);
		return null;
	}

}
