package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.TouziBean;


public class TouziAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<TouziBean>list;
	private TouziBean touziBean;
	private TextView tv_time,tv_project,tv_touzi;
	private String str_projid,str_time;
	private int touzi;
	
	public TouziAdapter(Context context, ArrayList<TouziBean> list) {
		super();
		this.context = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return (list != null && list.size() > position) ? list.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(R.layout.touzi_layout, null);
		tv_project = (TextView)convertView.findViewById(R.id.tv_touzi_project);
		tv_touzi = (TextView)convertView.findViewById(R.id.tv_touzi_count);
		tv_time = (TextView)convertView.findViewById(R.id.tv_touzi_time);
		touziBean = list.get(position);
		str_projid = touziBean.getProj_id();
		str_time = touziBean.getTouzi_time();
		touzi = touziBean.getTouzi_gets();
		tv_project.setText(str_projid);
		tv_time.setText(str_time);
		tv_touzi.setText(touzi);
		return convertView;
	}

}
