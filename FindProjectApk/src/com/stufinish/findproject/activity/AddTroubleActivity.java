package com.stufinish.findproject.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.ProjectAdapter;
import com.stufinish.findproject.adapter.TroubleAdapter;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.model2.Trouble;
import com.stufinish.findproject.utils.Constant;
import com.stufinish.findproject.utils.HttpUploadUtil;
import com.stufinish.findproject.utils.SendJson;
import com.stufinish.findproject.utils.TimeUtil;

/**
 * @函数 主要有三个
 * 
 *     getEditText() delEditText() addEditText()
 * @author jefferson
 * 
 */
public class AddTroubleActivity extends Activity implements OnClickListener {
	private Button add_trouble, del_trouble, deploy_trouble;// 添加，删除，发表按钮
	private ImageView back_add;//
	private EditText et_trouble, et_add_et; // 最初的编辑框和添加的编辑框
	private LinearLayout linear_trouble; // 添加编辑框控件所用到的布局
	private StringBuffer sb_trouble; // 所有的问题字符串
	private String trouble; // sb_trouble.toString()
	private String proj_id; // 项目ID
	private Handler hd, hd_trouble; // handler句柄
	private String url = Constant.addTrouble; // 服务端地址：Add_trouble.jsp
	private ListView listView;
	private TextView tvhead_trouble;
	private String xString;
	private List<Trouble> list;
	private TroubleAdapter adapter;
	private ProjectBean pBean;
	private Trouble tbean;
	private int flag;
	private static int add_trouble_count=-1;
	private String email = LoginActivity.loginBean.getE_mail();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_trouble);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			pBean = (ProjectBean) bundle.get("trouble_proj");
		}
		initWidget();
		//加载问题的线程用hd更新主线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				xString = search(pBean.getProject_id());
				list = jxJSON(xString);
				Message msg = hd.obtainMessage();
				hd.sendMessage(msg);
			}
		}).start();
		/*
		 * hd_trouble
		 * 添加问题的线程执行完后，直接将添加的问题加入listView列表
		 */
		hd_trouble = new Handler() {
			public void handleMessage(Message msg) {
				Log.i("Looper", String.valueOf(msg.what));
				String str = msg.getData().getString("msg");
				Toast.makeText(AddTroubleActivity.this, str, Toast.LENGTH_SHORT)
						.show();
				if (str.equals("添加成功")) {
					// AddTroubleActivity.this.finish();
					Trouble trouble2 = (Trouble) adapter.getItem(0);
					String[] troubles = trouble.split(",");
					for (int i = 0; i < troubles.length; i++) {
						flag = trouble2.getTrouble_flag();
						tbean = new Trouble(pBean.getProject_id(),
								flag + i + 1, troubles[i], TimeUtil.getDate());
						list.add(0, tbean);
						adapter = new TroubleAdapter(AddTroubleActivity.this, list);
						listView.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}

					
//					adapter.notifyDataSetChanged();
					linear_trouble.removeViews(1,
							linear_trouble.getChildCount() - 2);
					EditText ettEditText = (EditText) linear_trouble
							.getChildAt(0);
					ettEditText.setText("");
				}
			}
		};
		hd = new Handler() {
			public void handleMessage(Message msg) {
				adapter = new TroubleAdapter(AddTroubleActivity.this, list);
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		};

	}

	private String search(int strp) {
		String sss = null;
		String turl = Constant.getTrouble;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(turl);
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("proj_id", strp + "")); // 用户名

			request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				sss = EntityUtils.toString(response.getEntity()).trim(); // 获取返回的字符串
			} else {
				sss = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sss;
	}

	public List<Trouble> jxJSON(String result) {
		List<Trouble> ll = new ArrayList<Trouble>();
		if (result == null) {
			return null;
		}
		try {
			JSONArray jsonarr = new JSONArray(result);
			for (int i = 0; i < jsonarr.length(); i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
				// int proj_id = obj.getInt("project_id");
				// trouble_content,proj_id,trouble_time,trouble_flag
				String trouble_content = obj.getString("trouble_info");
				int trouble_id = obj.getInt("trouble_id");
				int proj_id = obj.getInt("proj_id");
				String trouble_time = obj.getString("trouble_time");
				int trouble_flag = obj.getInt("trouble_flag");
				String trouble_mail = obj.getString("trouble_mail");

				// String person_name = obj.getString("name");
				// String person_resource = obj.getString("project_resource");
				// personBean = new PersonBean(proj_mail, person_name);
				// pbean = new ProjectBean(proj_id, proj_link, proj_type,
				// proj_mind, proj_theme, proj_description, proj_time,
				// proj_mail, person_name);
				Trouble trouble = new Trouble(trouble_id, proj_id,
						trouble_flag, trouble_content, trouble_time,trouble_mail);
				ll.add(trouble);
			}
			return ll;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能点：初始化控件 为控件绑定监听器
	 */
	private void initWidget() {
		tvhead_trouble = (TextView) findViewById(R.id.title_add_trouble);
		tvhead_trouble.setText(pBean.getProject_theme());
		listView = (ListView) findViewById(R.id.listV_trouble);
		add_trouble = (Button) findViewById(R.id.add_trouble);
		del_trouble = (Button) findViewById(R.id.del_trouble);
		deploy_trouble = (Button) findViewById(R.id.deploy_trouble);
		back_add = (ImageView) findViewById(R.id.back_add_trouoble);
		et_trouble = (EditText) findViewById(R.id.et_add_trouble);
		linear_trouble = (LinearLayout) findViewById(R.id.linear_trouble);

		add_trouble.setOnClickListener(this);
		del_trouble.setOnClickListener(this);
		deploy_trouble.setOnClickListener(this);
		back_add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_trouble:
			addEditText();
			break;
		case R.id.del_trouble:
			delEditText();
			break;
		case R.id.deploy_trouble:
			EditText et;
			String str_et = "";
			
			et = (EditText) linear_trouble.getChildAt(0);
			str_et = et.getText().toString();
			
			if (str_et.length() > 5) {
				trouble = getEditText();
				if (trouble != null) {
					final Map<String, String> params = new HashMap<String, String>();
					params.put("trouble_content", trouble);
					params.put("proj_id", "" + pBean.getProject_id());
					params.put("trouble_mail", email);
					
					new Thread() {
						public void run() {
							String msgStr = HttpUploadUtil.postWithoutFile(url,
									params);
							Bundle b = new Bundle();
							// 将内容字符串放进数据Bundle中
							b.putString("msg", msgStr);
							// 、创建消息对象
							Message msg = hd_trouble.obtainMessage();
							// 设置数据Bundle到消息中
							msg.setData(b);
							// 设置消息标识
							msg.what = 100;
							// 发送消息
							hd_trouble.sendMessage(msg);
						} // run

					}.start();
				}
			} else {
				Toast.makeText(AddTroubleActivity.this, "內容不足字數要求!!",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.back_add_trouoble:
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 功能点：获取所有的问题
	 * 
	 * @return String
	 */

	private String getEditText() {
		sb_trouble = new StringBuffer();
		int i = 0;
		for (i = 0; i < linear_trouble.getChildCount() - 2; i++) {
			et_add_et = (EditText) linear_trouble.getChildAt(i);
			if (et_add_et.getText().toString().length() > 3) {
				sb_trouble.append(et_add_et.getText().toString() + ",");
			}
		}
		return sb_trouble.toString();
		// Toast.makeText(AddTroubleActivity.this, sb_trouble.toString(),
		// Toast.LENGTH_LONG).show();
	}

	/**
	 * 功能点：让每个新加的编辑框获得焦点
	 * 
	 * @param et
	 */
	private void getfocuse(EditText et) {
		et.setSingleLine(true);
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		et.requestFocus();
		et.findFocus();
	}

	/**
	 * 功能点：根据删除编辑框
	 */
	private void delEditText() {
		if (linear_trouble.getChildCount() > 3) {
			et_add_et = (EditText) linear_trouble.getChildAt(linear_trouble
					.getChildCount() - 3);
			if (et_add_et.getText().toString().length() > 3) {
				AlertDialog alert = new AlertDialog.Builder(
						AddTroubleActivity.this).create();
				alert.setTitle("确定要删除这个问题吗");
				alert.setMessage(et_add_et.getText().toString());
				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(AddTroubleActivity.this, "取消",
										Toast.LENGTH_SHORT).show();
							}
						});
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								linear_trouble.removeViewAt(linear_trouble
										.getChildCount() - 3);
								Toast.makeText(
										AddTroubleActivity.this,
										"您已删除"
												+ "\n"
												+ et_add_et.getText()
														.toString(),
										Toast.LENGTH_SHORT).show();
							}

						});
				alert.show();
			} else {
				linear_trouble.removeViewAt(linear_trouble.getChildCount() - 3);
			}
		} else if (linear_trouble.getChildCount() == 3) {
			final EditText eText = (EditText) linear_trouble.getChildAt(0);
			if (eText.getText().toString().length() > 3) {
				AlertDialog alert = new AlertDialog.Builder(
						AddTroubleActivity.this).create();
				alert.setTitle("确定要删除这个问题吗");
				alert.setMessage(eText.getText().toString());
				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(AddTroubleActivity.this, "取消",
										Toast.LENGTH_SHORT).show();
							}
						});
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								del_trouble.setVisibility(View.GONE);
								eText.setText("");
								Toast.makeText(AddTroubleActivity.this,
										"您已删除" + "\n", Toast.LENGTH_SHORT)
										.show();
							}

						});
				alert.show();
			} else {
				del_trouble.setVisibility(View.GONE);
				eText.setText("");
			}
		}
	}

	/**
	 * 功能点：根据条件添加编辑框
	 */
	private void addEditText() {
		EditText edit = (EditText) linear_trouble.getChildAt(linear_trouble
				.getChildCount() - 3);
		if (edit.getText().toString().length() > 3) {
			if (edit.getText().toString().length() > 1) {
				et_add_et = new EditText(AddTroubleActivity.this);
				et_add_et.setHint("请输入问题");
				del_trouble.setVisibility(View.VISIBLE);
				linear_trouble.addView(et_add_et,
						linear_trouble.getChildCount() - 2);
				getfocuse(et_add_et);
			}
		} else {
			Toast.makeText(AddTroubleActivity.this,
					"您输入的字符数小于4" + "\n" + "请继续输入", Toast.LENGTH_SHORT).show();
		}
	}

	public static int getAdd_trouble_count() {
		return ++add_trouble_count;
	}
}
