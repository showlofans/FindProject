package com.stufinish.findproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.stufinish.findproject.R;

public class SelectActivity extends Activity{
	private ListView lv_page_select;
	private int str_res;
	private static int select_count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_select_page);
		initView();
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.page_num,
				android.R.layout.simple_list_item_checked); // 创建一个适配器
		lv_page_select.setAdapter(adapter);
		lv_page_select.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int pos,
					long id) {
				str_res = Integer.parseInt(parent.getItemAtPosition(pos).toString()); // 获取选择项的值
				//数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", str_res);
                getSelect_count();
                //设置返回数据
                SelectActivity.this.setResult(RESULT_OK, intent);
                //关闭Activity
                SelectActivity.this.finish();
//				Toast.makeText(SelectActivity.this, result, Toast.LENGTH_SHORT).show();
			}

		});

	}
	private void initView() {
		lv_page_select=(ListView)findViewById(R.id.lv_page_select);
	}
	public static int getSelect_count() {
		return ++select_count;
	}

}
