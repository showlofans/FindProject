package com.stufinish.findproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.utils.InitPopWindow;
/**
 * 
 * @author jefferson
 * 功能点：
 * 它是一个项目发布窗口
 * @category 和MainActivity是联系在一起的
 *
 */
public class DeployActivity extends Activity implements OnClickListener {

	private LinearLayout linear_platform, linear_app ;
	private LinearLayout linear_web ,linear_container;
	private String app_theme,web_theme,platform_theme;
	private TextView tv_app,tv_web,tv_platform;
	private static int deploy_app_count=0;
	private static int deploy_web_count=0;
	private static int deploy_level_count=0;
//	private Spinner sp_home, sp_function;
	

	public static int getDeploy_app_count() {
		return deploy_app_count;
	}



	public static int getDeploy_web_count() {
		return deploy_web_count;
	}



	public static int getDeploy_level_count() {
		return deploy_level_count;
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contacts_view);
		initView();
		//init_sp();
		
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.app_deploy:
			app_theme = tv_app.getText().toString();
			Intent app_intent = new Intent();
			app_intent.setClass(DeployActivity.this,DeployDetail.class);
			app_intent.putExtra("title", app_theme);
			startActivity(app_intent);
			deploy_app_count++;
			break;
		case R.id.web_deploy:
			web_theme = tv_web.getText().toString();
			Intent web_intent = new Intent();
			web_intent.setClass(DeployActivity.this,DeployDetail.class);
			web_intent.putExtra("title", web_theme);
			deploy_web_count++;
			startActivity(web_intent);
			break;
		case R.id.platform_deploy:
			platform_theme = tv_platform.getText().toString();
			Intent platform_intent = new Intent();
			platform_intent.setClass(DeployActivity.this,DeployDetail.class);
			platform_intent.putExtra("title", platform_theme);
			deploy_level_count++;
			startActivity(platform_intent);
			break;
		default:
			break;
		}

	}

	private void initView() {
//		bt1.setOnClickListener(this);
//		bt1.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(v.getContext(),LoginActivity.class));
//			}
//		});
//		bt2.setOnClickListener(this);
//		bt3.setOnClickListener(this);
		
		
		
		linear_container = (LinearLayout)findViewById(R.id.linear_container_head);
		tv_app = (TextView)findViewById(R.id.app_theme_name);
		linear_app = (LinearLayout) findViewById(R.id.app_deploy);
		linear_app.setOnClickListener(this);
		linear_platform = (LinearLayout) findViewById(R.id.platform_deploy);
		tv_platform = (TextView)findViewById(R.id.platform_theme_name);
		linear_platform.setOnClickListener(this);
		linear_web = (LinearLayout) findViewById(R.id.web_deploy);
		
		tv_web = (TextView)findViewById(R.id.web_theme_name);
		linear_web.setOnClickListener(this);
		
//		sp_home = (Spinner)findViewById(R.id.sp_home);	//功能
//		sp_function = (Spinner) findViewById(R.id.sp_my_space);	//我的家
	}
	
	private static long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					SharedPreferences sp = getSharedPreferences("tab", MODE_PRIVATE);
					Editor editor = sp.edit();
					editor.putInt("id", R.id.contacts_radio);
					editor.commit();
					Toast.makeText(this, "关闭应用", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
//					MainApplication application = MainApplication.getInstance();
//					MainApplication.setTag(R.id.contacts_radio);
					SharedPreferences sp = getSharedPreferences("tab", MODE_PRIVATE);
					Editor editor = sp.edit();
					editor.putInt("id", R.id.contacts_radio);
					editor.commit();
//					MainActivity.setId(R.id.contacts_radio);
					finish();
				}
				return false;
			}
			
		return super.onKeyDown(keyCode, event);
	}
}
