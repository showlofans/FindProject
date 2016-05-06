package com.stufinish.findproject.view;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.activity.LoginActivity;
import com.stufinish.findproject.service.NewThread;
import com.stufinish.findproject.utils.Constant;

public class ToastTouzi extends Activity implements OnClickListener {
	private Button bt_sure, bt_not;
	private EditText et_money;
	private String str_money;//投资数
	private int proj_id;//投资的项目
	private String proj_theme;
	private String email = LoginActivity.loginBean.getE_mail();//投资人email
	private String url = Constant.touziGetsUrl;//ip
	private Handler handler;//处理返回的结果

	// private Toast;//toast对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			proj_id = bundle.getInt("proj_id");
			proj_theme = bundle.getString("proj_theme");
		}
		setTitle(proj_theme);
		setContentView(R.layout.taost_touzi);
		bt_not = (Button) findViewById(R.id.touzi_cancel);
		et_money = (EditText) findViewById(R.id.toast_editor);
		bt_sure = (Button) findViewById(R.id.touzi_dep);
		bt_sure.setOnClickListener(this);
		bt_not.setOnClickListener(this);
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				String msgstr = msg.getData().getString("msg");
				Toast.makeText(ToastTouzi.this, msgstr, Toast.LENGTH_LONG)
				.show();//
				ToastTouzi.this.finish();
				super.handleMessage(msg);
			}
			
		};

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.touzi_cancel:
			ToastTouzi.this.finish();
			break;
		case R.id.touzi_dep:
			str_money = et_money.getText().toString();
			/**
			 * 验证编辑框内容
			 * 执行网络请求
			 */
			final Map<String, String> params = new HashMap<String, String>();
			params.put("money", str_money);
			params.put("proj_id", proj_id+"");
			params.put("email", email);
			params.put("proj_theme", proj_theme);
			Toast.makeText(ToastTouzi.this, proj_id+"\n"+str_money, Toast.LENGTH_LONG)
					.show();//本应该toast网络请求结果，此为测试数据
			NewThread.newThread(handler, params, url);
//			ToastTouzi.this.finish();
			break;
		default:
			break;
		}

	}

}
