package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import java.util.List;

import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.LevelBean;
import com.stufinish.findproject.model2.ProjectBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyProjectAdapter extends BaseAdapter {
	private Context context;
	private List<ProjectBean>list;
	private ArrayList<LevelBean>levelList;
	private ProjectBean projectBean;
	private LevelBean levelBean;
	private TextView tv_theme,tv_time;
	private Button bt_order,bt_comment,bt_gets;
	
	
	public MyProjectAdapter(Context context,List<ProjectBean>list,ArrayList<LevelBean>levelList) {
		super();
		this.context = context;
		this.list = list;
		this.levelList = levelList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ProjectBean getItem(int position) {
		// TODO Auto-generated method stub
		return (list != null && list.size() > position) ? list.get(position) : null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(R.layout.my_listitem, null);
		tv_theme = (TextView)convertView.findViewById(R.id.myproject_theme);
		tv_time = (TextView)convertView.findViewById(R.id.myproject_time);
		bt_comment = (Button)convertView.findViewById(R.id.mybt_comment);
		bt_gets = (Button)convertView.findViewById(R.id.mybt_gets);
		bt_order = (Button)convertView.findViewById(R.id.mybt_order);
		
		projectBean = list.get(position);
		levelBean = levelList.get(position);
		
		tv_theme.setText(projectBean.getProject_theme());
		tv_time.setText(projectBean.getProject_time());
		bt_comment.setText(levelBean.getComment_gets());
		bt_gets.setText(levelBean.getContact_gets());
		bt_order.setText(levelBean.getProject_order());
		return convertView;
	}
	
}
