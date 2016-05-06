package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.activity.DeployDetail;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.view.ToastTouzi;

public class ProjectAdapter extends BaseAdapter {

	private Context context;
	private List<ProjectBean> list = new ArrayList<ProjectBean>();

	public ProjectAdapter(Context context, List<ProjectBean> list) {
		super();
		this.context = context;
		this.list = list;
	}

	// /**
	// * bindData用来传递数据给适配器。
	// * @param list
	// */
	// public void bindData(List<ProjectBean> list) {
	// this.list = list;
	// }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ProjectBean getItem(int position) {
		// TODO Auto-generated method stub
		return (list != null && list.size() > position) ? list.get(position)
				: null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		ViewHolder viewHolder = null;
		if (null == convertView) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.listitem, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_theme = (TextView) convertView
					.findViewById(R.id.project_theme);
			viewHolder.tv_project = (TextView) convertView
					.findViewById(R.id.proj_description);
			viewHolder.tv_time = (TextView) convertView
					.findViewById(R.id.deploy_time);
			// ImageView li_headpic = (ImageView)
			// convertView.findViewById(R.id.li_headpic);
			viewHolder.tv_level = (TextView) convertView
					.findViewById(R.id.type_level);
			viewHolder.tv_name = (TextView) convertView
					.findViewById(R.id.li_nickkname);
			viewHolder.bt1 = (TextView) convertView
					.findViewById(R.id.bt_comment);
			viewHolder.bt2 = (TextView) convertView.findViewById(R.id.bt_order);
			viewHolder.bt3 = (TextView) convertView.findViewById(R.id.bt_gets);
			viewHolder.bt3.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					int id = getItem(pos).getProject_id();
					String proj_theme = getItem(pos).getProject_theme();
//					Toast.makeText(context, id+"", Toast.LENGTH_SHORT)
//					.show();
					Intent touIntent = new Intent(v.getContext(),ToastTouzi.class);
					touIntent.putExtra("proj_id", id);
					touIntent.putExtra("proj_theme", proj_theme);
					context.startActivity(touIntent);
				}
				
			});
			registerOnClick(viewHolder.bt1);
			registerOnClick(viewHolder.bt2);

			// tv_project tv_time tv_level tv_name
			// tv_name.setText(proj.getPerson().getPerson_name());
			// int total_gets = proj.getLevel_bean().getTotal_gets();//项目等级
			// String leveltype = proj.getProject_type() + " :";// 项目类型
			// tv_level.setText(leveltype);

			// tv_level.setText(level.getLevel_type()+level.getTotal_gets());
			// 项目等级
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ProjectBean proj = list.get(position);
		viewHolder.tv_name.setText(proj.getPerson_email());
		viewHolder.tv_theme.setText(proj.getProject_mind() + ":"
				+ proj.getProject_theme()); // 项目主题
		viewHolder.tv_project.setText(proj.getContent_description()); // 项目介绍
		viewHolder.tv_time.setText(proj.getProject_time()); // 项目发布时间
		viewHolder.tv_level.setText(proj.getProject_type()
				+ proj.getProject_id());

		return convertView;
	}

	class ViewHolder {
		TextView tv_theme;
		TextView tv_project;
		TextView tv_time;
		TextView tv_name;
		TextView tv_level;
		TextView bt1;
		TextView bt2;
		TextView bt3;
	}
	private void registerOnClick(TextView bt) {
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.bt_comment:
					Toast.makeText(context, "评论失败", Toast.LENGTH_SHORT).show();
					// bt_comment.setText(level.getComment_gets());
					break;
				case R.id.bt_order:

					// new Thread(new Runnable() {
					// public void run() {
					// Log.i("run(", "线程开始");
					// x = send(Constant.levelUrl);// 调用send()获取返回的字符串。
					// }
					// }).start();
					// if (x.equals("success")) {
					// bt_order.setText(level.getProject_order() + 1);
					// } else {
					Toast.makeText(context, "订阅失败", Toast.LENGTH_SHORT).show();
					// }

					break;
				default:
					break;
				}
			}
		});

	}

//	public void dogets() {
//		AlertDialog alert = new AlertDialog.Builder(context).create();
//		LayoutInflater inflater = LayoutInflater.from(context);
//		final View textEntryView = inflater.inflate(
//				R.layout.alert_dialog_text_entry, null);
//		final EditText touzi_quantityET = (EditText) textEntryView
//				.findViewById(R.id.etv_dialog_number);
//		alert.setTitle("请选择投资金额");
//		alert.setView(textEntryView);
//		alert.setButton("确定", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//
//				Toast.makeText(context, "投资额=", Toast.LENGTH_LONG).show();
//			}
//		});
//	}

}
