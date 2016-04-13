package com.stufinish.findproject.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.ProjectBean;

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
			registerOnClick(viewHolder.bt1);
			registerOnClick(viewHolder.bt2);
			registerOnClick(viewHolder.bt3);

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
				case R.id.bt_gets:
					dogets();
					Toast.makeText(context, "投资失败", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});

	}

	public void dogets() {
		AlertDialog alert = new AlertDialog.Builder(context).create();
		LayoutInflater inflater = LayoutInflater.from(context);
		final View textEntryView = inflater.inflate(
				R.layout.alert_dialog_text_entry, null);
		final EditText touzi_quantityET = (EditText) textEntryView
				.findViewById(R.id.etv_dialog_number);
		alert.setTitle("请选择投资金额");
		alert.setView(textEntryView);
		alert.setButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// int touzi_gets = Integer.parseInt(touzi_quantityET.getText()
				// .toString());
				// if (touzi_gets > 0 && LoginActivity.loginBean != null) {
				// final Map<String, String> params = new HashMap<String,
				// String>();
				// // email,touzi_gets
				// params.put("proj_id", proj.getProject_id());
				// params.put("email", LoginActivity.loginBean.getE_mail());
				// params.put("touzi_gets", "touzi_gets");
				// new Thread() {
				// public void run() {
				// String msgStr = HttpUploadUtil.postWithoutFile(Url,
				// params);
				// Bundle b = new Bundle();
				// // 将内容字符串放进数据Bundle中
				// b.putString("msg", msgStr);
				// // 、创建消息对象
				// Message msg = new Message();
				// // 设置数据Bundle到消息中
				// msg.setData(b);
				// // 设置消息标识
				// msg.what = 100;
				// // 发送消息
				// handler.sendMessage(msg);
				//
				// } // run
				//
				// }.start();// thread
				// handler = new Handler() {
				// @Override
				// public void handleMessage(Message msg) {
				// Toast.makeText(context, "您已投出", Toast.LENGTH_SHORT).show();
				// super.handleMessage(msg);
				// }
				// };
				// }

				Toast.makeText(context, "投资额=", Toast.LENGTH_LONG).show();
				// bt_gets.setText(level.getContact_gets() + touzi_gets);
			}
		});
	}

	// public String send(String projecturl) {
	// String str = null;
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpPost request = new HttpPost(projecturl);
	// // NameValuePair loginpasvaluePair = new BasicNameValuePair("proj_id",
	// // proj.getProject_id());
	// // List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	// // parameters.add(loginpasvaluePair);
	// // try {
	// // request.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
	// // } catch (UnsupportedEncodingException e1) {
	// // // TODO Auto-generated catch block
	// // e1.printStackTrace();
	// // }
	// HttpResponse response;
	// try {
	// Log.i("send(", "执行请求1");
	// response = httpclient.execute(request);// 执行请求
	// Log.i("response", response.toString());
	// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { //
	// 如果请求成功
	// str = EntityUtils.toString(response.getEntity()).trim();
	// Log.i("send(", str);
	// return str;
	// } else {
	// str = "请求失败";
	// Log.i("send(", "请求失败");
	// return str;
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return str;
	// }
}
