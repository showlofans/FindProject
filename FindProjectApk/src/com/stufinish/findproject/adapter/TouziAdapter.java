package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.activity.TouZiActivity;
import com.stufinish.findproject.model2.TouziBean;
import com.stufinish.findproject.utils.Constant;
import com.stufinish.findproject.utils.HttpUploadUtil;

public class TouziAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<TouziBean> list;
	private TouziBean touziBean;

	private String str_projid, str_time;
	private int touzi, proj_id;
	private String deleUrl = Constant.deleTouzi;
	private Map<String, String>params = new HashMap<String, String>();
	private  Handler handler;
	private int del_pos;

	public int getDel_pos() {
		return del_pos;
	}

	public void setDel_pos(int del_pos) {
		this.del_pos = del_pos;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public TouziBean getTouziBean() {
		return touziBean;
	}
	public void setTouziBean(TouziBean touziBean) {
		this.touziBean = touziBean;
	}
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

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
		return (list != null && list.size() > position) ? list.get(position)
				: null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		ViewHolder viewHolder = null;
		if (null == convertView) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.item_touzi, null);
			touziBean = list.get(position);
			str_projid = touziBean.getProj_name();
			str_time = touziBean.getTouzi_time();
			touzi = touziBean.getTouzi_gets();
			viewHolder = new ViewHolder();
			viewHolder.tv_project = (TextView)convertView.findViewById(R.id.touziproj_tv);
			viewHolder.tv_project.setText(str_projid);
			viewHolder.tv_project.setOnClickListener(new OnClickListener() {//单击项目名跳转到项目详情
				
				@Override
				public void onClick(View v) {
					proj_id = getItem(pos).getProj_id();
				}
			});
			viewHolder.tv_touzi = (TextView)convertView.findViewById(R.id.touzi_tv);
			viewHolder.tv_time = (TextView)convertView.findViewById(R.id.touzitime_tv);
			viewHolder.bt_cancel = (Button)convertView.findViewById(R.id.tzcancel_bt);
			viewHolder.tv_time.setText(str_time);
			viewHolder.tv_touzi.setText(touzi+"");
			viewHolder.bt_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Map<String, String> tt = new HashMap<String, String>();
					setDel_pos(list.get(pos).getTouzi_id());
					setTouziBean(list.get(pos));
					tt.put("touzi_id", list.get(pos).getTouzi_id()+"");
					Toast.makeText(context, list.get(pos).getTouzi_id()+"",
							Toast.LENGTH_SHORT).show();
					newThread(deleUrl,tt,handler);
				}
			});
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		return convertView;
	}

	class ViewHolder {
		// tv_time,tv_project,tv_touzi
		TextView tv_touzi;
		TextView tv_project;
		TextView tv_time;
		Button bt_cancel;
	}
	public void newThread(final String deleUrl, final Map<String, String>tt,final Handler handler_del){
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(deleUrl,
						tt);
				Bundle b = new Bundle();
				// 将内容字符串放进数据Bundle中
				b.putString("msg", msgStr);
				// 、创建消息对象
				Message msg = handler_del.obtainMessage();
				// 设置数据Bundle到消息中
				msg.setData(b);
				// 设置消息标识
				msg.what = 100;
				// 发送消息
				handler_del.sendMessage(msg);
			} // run
		}.start();
	}

}
