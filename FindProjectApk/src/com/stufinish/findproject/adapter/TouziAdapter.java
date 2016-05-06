package com.stufinish.findproject.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.ProjectAdapter.ViewHolder;
import com.stufinish.findproject.model2.TouziBean;


public class TouziAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<TouziBean>list;
	private TouziBean touziBean;
	private String str_projid,str_time;
	private int touzi,proj_id;
	
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
	public TouziBean getItem(int position) {
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
		final int pos = position;
		ViewHolder viewHolder = null;
		if (null == convertView) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_touzi, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_project = (TextView)convertView.findViewById(R.id.touziproj_tv);
			viewHolder.tv_project.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
			viewHolder.tv_touzi = (TextView)convertView.findViewById(R.id.touzi_tv);
			viewHolder.tv_time = (TextView)convertView.findViewById(R.id.touzitime_tv);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		touziBean = list.get(position);
		str_projid = touziBean.getProj_name();
		str_time = touziBean.getTouzi_time();
		touzi = touziBean.getTouzi_gets();
		viewHolder.tv_project.setText(str_projid);
		viewHolder.tv_project.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				proj_id = getItem(pos).getProj_id();
			}
		});
		viewHolder.tv_time.setText(str_time);
		viewHolder.tv_touzi.setText(touzi+"");
		return convertView;
	}
	class ViewHolder {
		//tv_time,tv_project,tv_touzi
		TextView tv_touzi;
		TextView tv_project;
		TextView tv_time;
		Button bt_cancel;
	}

}
