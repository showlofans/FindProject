package com.stufinish.findproject.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;

public class ToastResource extends Activity implements OnClickListener{
	private TextView tv_title;
	private EditText edt;
	private String str_resource,str_title;
	private Button bt_sure,bt_not;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast_resource);
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			str_title = bundle.getString("theme");
		}
		tv_title= (TextView) findViewById(R.id.tv_res_title);
		tv_title.setText(str_title);
		bt_not = (Button) findViewById(R.id.res_cancel);
		edt = (EditText) findViewById(R.id.toast_editor);
		bt_sure = (Button) findViewById(R.id.res_dep);
		bt_sure.setOnClickListener(this);
		bt_not.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.touzi_cancel:
			ToastResource.this.finish();
			break;
		case R.id.touzi_dep:
			str_resource = edt.getText().toString();
			/**
			 * 验证编辑框内容
			 * 执行网络请求
			 */
			Toast.makeText(ToastResource.this, str_resource, Toast.LENGTH_LONG)
					.show();//本应该toast网络请求结果，此为测试数据
			ToastResource.this.finish();
			break;
		default:
			break;
		}

	}

}
