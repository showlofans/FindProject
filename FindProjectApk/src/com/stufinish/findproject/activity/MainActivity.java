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
		OnCheckedChangeListener {
	public static final String TAG = "MainActivity";// 定义当前标签
	private TabHost tabHost;
	private RadioGroup radioderGroup;
	private int tab = R.id.message_radio;
	public StringBuffer sb_count_activity;

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
		MainApplication app = (MainApplication)getApplication();
		tab = app.getTag();
		radioderGroup.setOnCheckedChangeListener(this);
		radioderGroup.check(tab);
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.message_radio:
			tabHost.setCurrentTabByTag("1");
			Service2.getService2_count();
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


//	private void toastButtonText(Button bt) {
//		str_toast = bt.getText().toString();
//		Toast.makeText(MainActivity.this, str_toast, Toast.LENGTH_SHORT).show();
//	}
}
