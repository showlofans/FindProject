package com.stufinish.findproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.stufinish.findproject.R;

public class MainSettingActivity extends Activity implements OnClickListener {

	private ListView lv_setting;
	private TextView tv_setting;
	private Button bt_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_main);
		initView();
		lv_setting.addHeaderView(line());
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.setting,
				android.R.layout.simple_list_item_checked); // 创建一个适配器
		lv_setting.setAdapter(adapter);
		lv_setting.addFooterView(line());
		lv_setting.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 1:
					startActivity(new Intent(MainSettingActivity.this,
							PageSettingActivity.class));
					break;
				default:
					break;
				}
				// String result =
				// parent.getItemAtPosition(position).toString();
			}
		});
	}

	private void initView() {
		lv_setting = (ListView) findViewById(R.id.lv_settings);
		bt_back = (Button)findViewById(R.id.setting_back);
		bt_back.setOnClickListener(this);
	}

	private View line() {
		ImageView image = new ImageView(this); // 创建一个图像视图
		image.setImageResource(R.drawable.line1); // 设置要显示的图片
		return image;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.setting_back:
			MainSettingActivity.this.finish();
			break;
		default:
			break;
		}
	}

}
