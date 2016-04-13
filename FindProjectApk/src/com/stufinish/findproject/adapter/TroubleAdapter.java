package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.activity.MyHomeActivity;
import com.stufinish.findproject.model2.Trouble;

public class TroubleAdapter extends BaseAdapter{
	private Context context;
	private List<Trouble> list = new ArrayList<Trouble>();
	public TroubleAdapter(Context context, List<Trouble> list) {
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
		// TODO Auto-generated method stub
		return (list != null && list.size() > position) ? list.get(position)
				: null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		Trouble trouble = list.get(position);
		convertView = inflater.inflate(R.layout.list_trouble, null);
		TextView tv_name = (TextView)convertView.findViewById(R.id.tb_person);
		TextView tv_time = (TextView)convertView.findViewById(R.id.tb_time);
		TextView tv_content = (TextView)convertView.findViewById(R.id.tb_content);
		tv_name.setText(trouble.getTrouble_mail());
		tv_content.setText(trouble.getTrouble_info());
		tv_time.setText(trouble.getTrouble_time());
		tv_name.setOnClickListener(perClickLis);
		return convertView;
	}
	OnClickListener perClickLis = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			context.startActivity(new Intent(context,MyHomeActivity.class));//OthersActivity
		}
	};
		
}
