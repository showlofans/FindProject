package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stufinish.findproject.R;

public class PageSettingAdapter extends BaseAdapter{
	private String[]str = new String[12];//12表示共有12个设置item
	private Context context;
	
	
	public PageSettingAdapter(Context context,String[]str) {
		super();
		this.str = str;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return str.length;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return (str != null && str.length > position)?str[position]:null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(R.layout.settings_layout, null);
		TextView tv_page_num=(TextView)convertView.findViewById(R.id.setting_numbers); 
		tv_page_num.setText(str[position]);
		return convertView;
	}
	

}
