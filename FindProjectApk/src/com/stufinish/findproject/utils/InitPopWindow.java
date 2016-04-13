package com.stufinish.findproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.activity.AddTroubleActivity;
import com.stufinish.findproject.activity.LoginActivity;

public class InitPopWindow implements OnClickListener {
	public PopupWindow popwindow;
	private View view; // layoutinflater解析出的layout
	private String b;
	private String str;
	public Boolean bblea = false;
	private Thread thread;
	private String proj_type;

	public String getStr() {
		return this.str;
	}
//	public void setStr(String str) {
//		this.str = str;
//	}
	private Button bt1, bt2, bt3;
	public Button bt_app_head, bt_web_head, bt_level_head;
	private Button bt_renzhen, bt_resource, bt_link_;
	private Button bt_mind, bt_trouble, bt_description, bt_theme;
	private Context context;
	// private Handler handler;
	private ListView lv;

	public ListView getLv() {
		return lv;
	}

	public void setLv(ListView lv) {
		this.lv = lv;
	}

	public InitPopWindow(View view, String b, PopupWindow pop) {
		this.view = view;
		this.context = view.getContext();
		this.b = b;
		this.popwindow = pop;
		popwindow.setBackgroundDrawable(new BitmapDrawable());
		popwindow.setOutsideTouchable(true);
		popwindow.setFocusable(true);
	}

	public void getWindow() {
		if (popwindow != null && popwindow.isShowing()) {
			popwindow.dismiss();
		} else {
			createWindow();
		}
	}

	public void closeWindow() {
		if (popwindow.isShowing()) {
			popwindow.dismiss();
		}
	}

	private void createWindow() {

		if (b.equals("rightView")) {
			bt1 = (Button) view.findViewById(R.id.bt_1);
			bt1.setOnClickListener(this);
			bt2 = (Button) view.findViewById(R.id.bt_2);
			bt2.setOnClickListener(this);
			bt3 = (Button) view.findViewById(R.id.bt_3);
			bt3.setOnClickListener(this);
		} 
		else {
//			bt_app_head = (Button) view.findViewById(R.id.bt_app_head);
//			bt_web_head = (Button) view.findViewById(R.id.bt_web_head);
//			bt_level_head = (Button) view.findViewById(R.id.bt_level_head);
//			bt_app_head.setOnClickListener(this);
//			bt_web_head.setOnClickListener(this);
//			bt_level_head.setOnClickListener(this);
		}

		view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popwindow != null && popwindow.isShowing()) {
					popwindow.dismiss();
					popwindow = null;
				}
				return false;
			}
		});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bt_add_link:
			showToast(bt_link_);
			break;
		case R.id.bt_update_mind:
			showToast(bt_mind);
			break;
		case R.id.bt_add_description:
			showToast(bt_description);
			break;
		case R.id.bt_update_theme:
			showToast(bt_theme);
			break;
		case R.id.bt_add_resource:
			showToast(bt_resource);
			break;
		case R.id.bt_add_renzhen:
			showToast(bt_renzhen);
			break;
		// if(popwindow != null && popwindow.isShowing()){
		// popwindow.dismiss();
		// popwindow = null;
		// }
		// Intent renzhenIntent = new
		// Intent(ProjectDetailActivity.this,AddTroubleActivity.class);
		// // if(proj != null){
		// // if (my_change==1) {
		// // renzhenIntent.putExtra("edit", 1);
		// // }else{
		// // renzhenIntent.putExtra("edit", 0);
		// // }
		// // renzhenIntent.putExtra("proj_id", proj.getProject_id());
		// // }
		// startActivity(renzhenIntent);
		case R.id.bt_add_trouble:
			context.startActivity(new Intent(context, AddTroubleActivity.class));
			closeWindow();
			break;
		// Intent troubleIntent = new Intent(ProjectDetailActivity.this,
		// AddTroubleActivity.class);
		// // troubleIntent.putExtra("proj_id", proj.getProject_id());
		// startActivity(troubleIntent);
		case R.id.bt_1:
			context.startActivity(new Intent(context, LoginActivity.class));
			closeWindow();
			break;
		case R.id.bt_2:
			showToast(bt2);
			// startActivity(new Intent(ProjectsService.this,
			// ProjectDetailActivity.class));
			closeWindow();
			break;
		case R.id.bt_3:
			showToast(bt3);
			break;
		case R.id.bt_app_head:
			str = bt_app_head.getText().toString();
			bblea = true;
			showToast(bt_app_head);
			break;
		case R.id.bt_web_head:
			bblea = true;
			proj_type = bt_web_head.getText().toString();
			if (bblea) {
				thread.start();
			}
			Toast.makeText(bt_web_head.getContext(), proj_type, Toast.LENGTH_SHORT)
					.show();
			// showToast(bt_web_head);
			break;
		case R.id.bt_level_head:
			bblea = true;
			str = bt_level_head.getText().toString();
			showToast(bt_level_head);
			break;
		default:
			break;

		}

	}

	private void showToast(Button btn) {
		String proj_info = btn.getText().toString();
		closeWindow();
		Toast.makeText(btn.getContext(), proj_info, Toast.LENGTH_SHORT).show();
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public String getProj_type() {
		return proj_type;
	}

	public void setProj_type(String proj_type) {
		this.proj_type = proj_type;
	}

}
