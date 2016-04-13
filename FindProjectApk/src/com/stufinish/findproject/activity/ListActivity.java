//package com.stufinish.findproject.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.stufinish.findproject.R;
//import com.stufinish.findproject.utils.TimeUtil;
//
//public class ListActivity extends BaseActivity implements OnClickListener{
//
//	private LinearLayout listitem_layout;
//	private RelativeLayout project_layout;
//	private Button bt_comment,bt_order,bt_gets;
//	private ImageView headimg;
//	private TextView tv_level,tv_time,tv_name;
//	private TextView tv_project,tv_theme;
//	private ImageView li_headpic;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.listitem);
//		initView();
//	}
//
//	private void initView() {
//		bt_comment=(Button)findViewById(R.id.bt_comment);
//		bt_comment.setOnClickListener(this);
//		bt_order=(Button)findViewById(R.id.bt_order);
//		bt_order.setOnClickListener(this);
//		bt_gets=(Button)findViewById(R.id.bt_gets);
//		bt_gets.setOnClickListener(this);
//		project_layout=(RelativeLayout)findViewById(R.id.PersonInfo_layout);
//		project_layout.setOnClickListener(this);
//		
//		tv_project = (TextView)findViewById(R.id.project_content);
//		tv_theme = (TextView) findViewById(R.id.project_theme);
//		tv_time = (TextView) findViewById(R.id.deploy_time);
//		tv_level =( TextView) findViewById(R.id.type_level);
//		li_headpic = (ImageView) findViewById(R.id.li_headpic);
//		tv_name=(TextView)findViewById(R.id.li_nickkname);
//		tv_name.setText("С��");
//		li_headpic.setOnClickListener(this);
//		listitem_layout=(LinearLayout)findViewById(R.id.listitem_layout);
//		listitem_layout.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent detailintent=new Intent(ListActivity.this,RegisterActivity.class);
//				startActivity(detailintent);
//			}
//		});
//		tv_time.setText(TimeUtil.getDate());
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.bt_comment:
//			Toast.makeText(ListActivity.this, bt_comment.getText(), Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.bt_order:
//			Toast.makeText(ListActivity.this, bt_order.getText(), Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.bt_gets:
//			Toast.makeText(ListActivity.this, bt_gets.getText(), Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.li_headpic:
//			Toast.makeText(ListActivity.this, "����ת�����˽���", Toast.LENGTH_SHORT).show();
//			break;
//		case R.id.PersonInfo_layout:
//			Toast.makeText(ListActivity.this, "����ת�����˽���", Toast.LENGTH_SHORT).show();
//			break;
//		//case R.id.bt_gets: 
//		default:
//			break;
//		}
//	}
//}
