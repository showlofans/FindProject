package com.stufinish.findproject.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.Trouble;

public class TroubleActivity extends BaseActivity implements OnClickListener{
	private ListView lv_trouble;
	private ImageView back_detail_trouble,menu_trouble;
	private String url;
	private String x;
	private Trouble trouble;
	private ArrayList<Trouble> list_troubl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_trouble);
		initView();
	}
	private void initView() {
		back_detail_trouble = (ImageView)findViewById(R.id.back_detail_troble);
		back_detail_trouble.setOnClickListener(this);
		menu_trouble = (ImageView)findViewById(R.id.img_menu_trouble);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_detail_troble:
			finish();
			break;

		default:
			break;
		}
	}
	
}
