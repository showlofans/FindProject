package com.stufinish.findproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.PageSettingAdapter;

public class PageSettingActivity extends Activity {
	private ListView lv_page;
	private static int RECODE = 1;
	private int result = 10;
	private PageSettingAdapter adapter;
	private String[] str = new String[] { result + "" };
	private SharedPreferences sp;
	private Button bt_save;

	// private MainApplication mainApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_setting);
		initView();
		// mainApp = MainApplication.getInstance();
		lv_page.addHeaderView(line());

		adapter = new PageSettingAdapter(PageSettingActivity.this, str);
		lv_page.setAdapter(adapter);
		lv_page.addFooterView(line());
		lv_page.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 1:
					Intent intent = new Intent(PageSettingActivity.this,
							SelectActivity.class);
					startActivityForResult(intent, RECODE);
					overridePendingTransition(android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
					break;
				default:
					break;
				}
				// String result =
				// parent.getItemAtPosition(position).toString();
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == RECODE && resultCode == RESULT_OK) {
			result = data.getExtras().getInt("result");
			str[0] = result + "";
			adapter.notifyDataSetChanged();
		}

		// super.onActivityResult(requestCode, resultCode, data);
	}

	private void initView() {
		sp = getSharedPreferences("page", MODE_PRIVATE);

		str[0] = sp.getInt("pageNum", 10) + "";
		lv_page = (ListView) findViewById(R.id.lv_page_settings);
		bt_save = (Button) findViewById(R.id.page_save);

		bt_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Editor editor = sp.edit();
				editor.putInt("pageNum", result);
				editor.commit();
				// mainApp.setPageNum(result);
				// Intent baIntent = new Intent();
				// baIntent.putExtra("pageNum", result);
				// PageSettingActivity.this.setResult(RESULT_OK, baIntent);
				PageSettingActivity.this.finish();
			}
		});
	}

	private View line() {
//		ImageView image2 = new ImageView
		ImageView image = new ImageView(PageSettingActivity.this); // 创建一个图像视图
//		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 5);
//		image.setLayoutParams(params);
		image.setImageResource(R.drawable.line1); // 设置要显示的图片\
//		image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,10));
//		image.setScaleType(ImageView.ScaleType.FIT_CENTER);
//		image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10));
		return image;
	}
}
