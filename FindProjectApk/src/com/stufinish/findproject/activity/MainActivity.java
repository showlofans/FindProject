package com.stufinish.findproject.activity;

import android.R.string;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;
import com.stufinish.findproject.R;

public class MainActivity extends TabActivity implements
		OnCheckedChangeListener, OnClickListener {
	public static final String TAG = "MainActivity";// 定义当前标签
	protected com.slidingmenu.lib.SlidingMenu side_drawer;
	private TabHost tabHost;
	private RadioGroup radioderGroup;
	private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7;
	private String str_toast; // Toast出来的String
	private int tab = R.id.message_radio;
	public StringBuffer sb_count_activity;
//	private static int id= R.id.message_radio;

	// private FindApplication findApplication =
	// (FindApplication)this.getApplicationContext();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tabHost = this.getTabHost();
		tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1")// message_view
				.setContent(new Intent(this, Service2.class)));// ProjectsService
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2")// contacts_view
				.setContent(new Intent(this, DeployActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("3").setIndicator("3")// hmy_home
				.setContent(new Intent(this, MyHomeActivity.class)));
		sb_count_activity = new StringBuffer();
		radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
		radioderGroup.setOnCheckedChangeListener(this);
//		MainApplication app = MainApplication.getInstance();
//		tab = MainApplication.getTag();
		SharedPreferences sp = getSharedPreferences("tab", MODE_PRIVATE);
		tab = sp.getInt("id", R.id.message_radio);
//		tab = MainActivity.getId();
//		if(ta)
//		tab = R.id.message_radio;
		radioderGroup.check(tab);// Ĭ�ϵ�һ����ť
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.message_radio:
			tabHost.setCurrentTabByTag("1");
			Service2.getService2_count();
//			tab = R.id.message_radio;
			break;
		case R.id.contacts_radio:
			tabHost.setCurrentTabByTag("2");
//			tab = R.id.contacts_radio;
			break;
		case R.id.news_radio:
			tabHost.setCurrentTabByTag("3");
			MyHomeActivity.getMy_home_count();
//			tab = R.id.news_radio;
			break;
		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item1_answer:
			toastButtonText(bt1);
			// Intent renzhenIntent = new Intent(MainActivity.this,
			// OrderActivity.class);
			// renzhenIntent.putExtra("order",
			// bt1.getText().toString());
			// startActivity(renzhenIntent);
			// startActivity(new Intent(MainActivity.this,
			// OrderActivity.class));
			break;
		case R.id.item2_project:
			toastButtonText(bt2);
			// Intent renzhenIntent = new Intent(MainActivity.this,
			// OrderActivity.class);
			// renzhenIntent.putExtra("order",
			// bt1.getText().toString());
			// startActivity(renzhenIntent);
			break;
		case R.id.item3_trouble:
			toastButtonText(bt3);
			startActivity(new Intent(MainActivity.this,
					AddTroubleActivity.class));

			break;
		case R.id.item4_gets:
			toastButtonText(bt4);
			startActivity(new Intent(MainActivity.this, TouZiActivity.class));
			break;
		case R.id.item5_setting:
			startActivity(new Intent(MainActivity.this,
					MainSettingActivity.class));
			// toastButtonText(bt5);
			break;
		case R.id.item6_description:
			toastButtonText(bt6);
			break;
		case R.id.item7_theme:
			toastButtonText(bt7);
			break;
		default:

			break;
		}
	}

	private void toastButtonText(Button bt) {
		str_toast = bt.getText().toString();
		Toast.makeText(MainActivity.this, str_toast, Toast.LENGTH_SHORT).show();
	}

//	public static int getId() {
//		return id;
//	}
//
//	public static void setId(int id) {
//		MainActivity.id = id;
//	}
}
