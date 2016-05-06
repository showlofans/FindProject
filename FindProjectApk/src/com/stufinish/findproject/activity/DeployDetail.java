package com.stufinish.findproject.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.PersonBean;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.service.NewThread;
import com.stufinish.findproject.utils.Constant;

public class DeployDetail extends Activity implements OnClickListener {

	private TextView tv_head;
	private Button bt_back, bt_deploy, bt_add_link, del_proj_link;
	private EditText et_link_proj, et_theme_proj, et_description_proj; // 项目链接，项目主题
	private Spinner spinner;
	// private EditText et_link_price, et_proj_price;
	private ProjectBean project;
	private SharedPreferences sp;
	private Handler hd;
	private String tv_title, stret_mind;
	private String stret_link, stret_theme, stret_description;
	private String email;
	private String url = Constant.AddProjectUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
						| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		setContentView(R.layout.deploy_project);

		sp = getSharedPreferences("loginconfig", MODE_PRIVATE);
		tv_title = this.getIntent().getStringExtra("title");
		initView();
		hd = new Handler() {
			public void handleMessage(Message msg) {
				Log.i("Looper", String.valueOf(msg.what));
				String msg_str = msg.getData().getString("msg");
				Toast.makeText(DeployDetail.this, msg_str, Toast.LENGTH_SHORT)
						.show();
				finish();
			}
		};

	}

	/**
	 * @param params
	 * @param newThread
	 * @return null 为发布功能准备参数， 并开启线程
	 */
	private void deploy_proj() {
		stret_theme = et_theme_proj.getText().toString();
		stret_description = et_description_proj.getText().toString();
		// stret_proj_price = et_proj_price.getText().toString();
		PersonBean pbean = LoginActivity.loginBean;
		email = pbean.getE_mail();
		if (check_et()) {// 判断编辑框里的内容是否为空
			final Map<String, String> params = new HashMap<String, String>();
			params.put("proj_type", tv_title);
			params.put("proj_mind", stret_mind);
			params.put("proj_theme", stret_theme);
			params.put("proj_description", stret_description);
			params.put("person_email", email);
			if (et_link_proj.getVisibility() != View.GONE) {
				stret_link = et_link_proj.getText().toString();
				Pattern pattern = Pattern
						.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
				Matcher matcher = pattern.matcher(stret_link); // 以验证127.400.600.2为例
				if (matcher.matches()) {// 判断ip地址
					params.put("proj_link", stret_link);
					NewThread.newThread(hd, params, url);
				} else {
					Toast.makeText(DeployDetail.this, "格式不对",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				params.put("proj_link", "空");
				NewThread.newThread(hd, params, url);
			}

			// project = new ProjectBean(stret_link, tv_title,
			// stret_mind,
			// stret_theme, stret_description);
			// 准备参数列表

		}
	}

	/**
	 * 1.初始化控件 2.为控件绑定监听器
	 */
	private void initView() {
		// link_price_layout = (LinearLayout)
		// findViewById(R.id.link_price_layout);
		// sp_price = (Spinner) findViewById(R.id.sp_price);// 价格单位
		// et_link_price = (EditText) findViewById(R.id.edit_link_price);//
		// 资源链接价格；
		// proj_price_layout = (LinearLayout)
		// findViewById(R.id.proj_price_layout);
		// et_proj_price = (EditText) findViewById(R.id.edit_proj_price);// 项目定价
		del_proj_link = (Button) findViewById(R.id.del_res_link);
		bt_add_link = (Button) findViewById(R.id.add_project_link);
		bt_back = (Button) findViewById(R.id.dep_cancel);
		bt_deploy = (Button) findViewById(R.id.project_dep);
		et_link_proj = (EditText) findViewById(R.id.edit_proj_link);
		et_theme_proj = (EditText) findViewById(R.id.edit_theme);
		et_description_proj = (EditText) findViewById(R.id.edit_description);
		spinner = (Spinner) findViewById(R.id.spDeployM);
		tv_head = (TextView) findViewById(R.id.tv_deploy);
		stret_mind = spinner.getSelectedItem().toString();

		// if (stret_mind.equals("在做项目")) {
		// proj_price_layout.setVisibility(View.VISIBLE);
		// }else{
		// proj_price_layout.setVisibility(View.GONE);
		// }
		tv_head.setText(tv_title);
		del_proj_link.setOnClickListener(this);
		bt_add_link.setOnClickListener(this);
		bt_back.setOnClickListener(this);
		bt_deploy.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.del_res_link:
			et_link_proj.setVisibility(v.GONE);
			bt_add_link.setVisibility(v.VISIBLE);
			del_proj_link.setVisibility(v.GONE);
			break;
		case R.id.add_project_link:
			et_link_proj.setVisibility(v.VISIBLE);
			bt_add_link.setVisibility(v.GONE);
			del_proj_link.setVisibility(v.VISIBLE);

			break;
		case R.id.project_dep:
			deploy_proj(); // 发布
			break;
		case R.id.dep_cancel:
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * @param null
	 * @return Boolean 检查编辑框数据
	 */
	private Boolean check_et() {
		// if (stret_theme != null
		// // && stret_price != null && stret_proj_price != null
		// && stret_description != null) {
		// return true;
		// }
		if (stret_theme == null) {
			Toast.makeText(DeployDetail.this, "主题为空！！", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else if (stret_description == null) {
			Toast.makeText(DeployDetail.this, "项目描述为空！！", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else {
			return true;
		}
		// if (stret_theme != null
		// // && stret_price != null
		// // && renzhen != null && resources != null
		// && stret_description != null) {
		// return true;
		// }
		// if (stret_theme != null && stret_description != null) {
		// return true;
		// }
		//
		// else
		// return false;
	}
}
