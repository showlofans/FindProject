package com.stufinish.findproject.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
	private Button bt_back, bt_deploy, bt_add_link;
	private EditText et_link_proj, et_theme_proj, et_description_proj; // 项目链接，项目主题
	private Spinner spinner, sp_price;
	private EditText et_link_price, et_proj_price;
	private LinearLayout link_price_layout, proj_price_layout;

	private ProjectBean project;
	private SharedPreferences sp;
	private Handler hd;
	private String tv_title, stret_mind, stret_sp_price;
	private String stret_price, stret_proj_price;
	private String stret_link, stret_theme, stret_description;
	private String email;
	private String url = Constant.AddProjectUrl;
//	private static int deploy_d_app = 0;
//	private static int deploy_d_web = 0;
//	private static int detail_d_level = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setSoftInputMode(
//				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
//						| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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
		// email = sp.getString("loginPassword", "");
		if (link_price_layout.getVisibility() != View.GONE) {
//			stret_price = et_link_price.getText().toString();
//			stret_sp_price = sp_price.getSelectedItem().toString();
			stret_link = et_link_proj.getText().toString();
			stret_theme = et_theme_proj.getText().toString();
			stret_description = et_description_proj.getText().toString();
//			stret_proj_price = et_proj_price.getText().toString();
			PersonBean pbean = LoginActivity.loginBean;
			email = pbean.getE_mail();
			if (check_et()) {// 判断编辑框里的内容是否为空
				final Map<String, String> params = new HashMap<String, String>();
				params.put("proj_type", tv_title);
				params.put("proj_mind", stret_mind);
				params.put("proj_theme", stret_theme);
				params.put("proj_description", stret_description);
				params.put("person_email", email);
				if (stret_link.equals("")) {
					project = new ProjectBean(tv_title, stret_mind,
							stret_theme, stret_description);
					params.put("proj_link", "空");
				} else {
					params.put("proj_link", stret_link);
					project = new ProjectBean(stret_link, tv_title, stret_mind,
							stret_theme, stret_description);
				}

				// 准备参数列表
				NewThread.newThread(hd, params, url);
			}
		}

	}

	/**
	 * 1.初始化控件 2.为控件绑定监听器
	 */
	private void initView() {
		link_price_layout = (LinearLayout) findViewById(R.id.link_price_layout);
		sp_price = (Spinner) findViewById(R.id.sp_price);// 价格单位
		et_link_price = (EditText) findViewById(R.id.edit_link_price);// 资源链接价格；
		proj_price_layout = (LinearLayout) findViewById(R.id.proj_price_layout);
		et_proj_price = (EditText) findViewById(R.id.edit_proj_price);// 项目定价
		bt_add_link = (Button) findViewById(R.id.project_res_link);
		bt_back = (Button) findViewById(R.id.dep_cancel);
		bt_deploy = (Button) findViewById(R.id.project_dep);
		et_link_proj = (EditText) findViewById(R.id.edit_proj_link);
		et_theme_proj = (EditText) findViewById(R.id.edit_theme);
		et_description_proj = (EditText) findViewById(R.id.edit_description);
		spinner = (Spinner) findViewById(R.id.spDeployM);
		tv_head = (TextView) findViewById(R.id.tv_deploy);
		stret_mind = spinner.getSelectedItem().toString();
		if (stret_mind.equals("在做项目")) {
			proj_price_layout.setVisibility(View.VISIBLE);
		}
		tv_head.setText(tv_title);
		bt_add_link.setOnClickListener(this);
		bt_back.setOnClickListener(this);
		bt_deploy.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.project_res_link:
			et_link_proj.setVisibility(v.VISIBLE);
			bt_add_link.setVisibility(v.GONE);
			link_price_layout.setVisibility(v.VISIBLE);

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
		if (proj_price_layout.getVisibility() == View.VISIBLE) {
			if (stret_theme != null 
//					&& stret_price != null && stret_proj_price != null
					&& stret_description != null) {
				return true;
			}
			if (link_price_layout.getVisibility() == View.VISIBLE) {
				if (stret_theme != null 
//						&& stret_price != null
				// && renzhen != null && resources != null
						&& stret_description != null) {
					return true;
				}
				return false;
			} else {
				if (stret_theme != null && stret_description != null) {
					return true;
				}
				return false;
			}

		}
		return false;
		//
		// else
		// return false;
	}
//	public static int getDeploy_d_app() {
//		return deploy_d_app;
//	}
//
//	public static int getDeploy_d_web() {
//		return deploy_d_web;
//	}
//
//	public static int getDetail_d_level() {
//		return detail_d_level;
//	}
}
