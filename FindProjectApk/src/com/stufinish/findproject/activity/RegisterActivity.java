package com.stufinish.findproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.dao.RegisterDao;
import com.stufinish.findproject.model2.PersonBean;
import com.stufinish.findproject.utils.Constant;

public class RegisterActivity extends Activity implements OnClickListener{
	
	private static final String TAG = "RegisterActivity";// ���嵱ǰ��ǩ
	private LinearLayout linear_name,linear_follows;
	private RelativeLayout linear_email,linear_resources;
	private TextView tv_save,tv_back,tv_resources;
	private EditText etv_personname,etv_email,etv_resources;
	private String person_id,person_name,person_email,person_resources;
	private String flag="";
	private PersonBean perbean;
	private String url=Constant.addperUrl;
	private Handler hd1;
	private Context context;
	private Bundle bundle;
	private PersonBean person;
	private SharedPreferences sp;
	private String personName,person_resource,email;
	private ImageView img_resource,img_email;
	private TableLayout table_level;
	private AlertDialog dialog;
	//private SlidingMenu menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person);
		findView();
		bundle = RegisterActivity.this.getIntent().getExtras();
		
		if(bundle != null){
			person = (PersonBean)bundle.get("person");
		}
//		if(person != null){
//			etv_email.setText(person.getE_mail());
//			etv_personname.setText(person.getPerson_name());
//			etv_resources.setText(person.getProject_resource());
//		}
		/*context=this;
		menu=new SlidingMenu(this);
		menu.setMode(SlidingMenu.RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		View menuitemView=LayoutInflater.from(this).inflate(R.layout.login, null);
		menu.setMenu(menuitemView);*/
		initView();
		
	}

	private void initView() {
		if(person!= null){
			etv_email.setText(person.getE_mail());;
			etv_resources.setText(person.getProject_resource());
			etv_personname.setText(person.getPerson_name());
		}
		person_name=etv_personname.getText().toString();
		person_email=etv_email.getText().toString();
		if(person_email.length()>5){
			img_email.setVisibility(View.VISIBLE);
		}
		person_resources=etv_resources.getText().toString();
		if(person_resources.length()>5){
			img_resource.setVisibility(View.VISIBLE);
		}
	}

	public void findView() {
		etv_personname=(EditText)findViewById(R.id.etv_personname);
		etv_email=(EditText)findViewById(R.id.etv_email);
		etv_resources=(EditText)findViewById(R.id.etv_resources);
		img_email = (ImageView)findViewById(R.id.img_email);
		img_resource = (ImageView)findViewById(R.id.img_resource);
		table_level = (TableLayout) findViewById(R.id.table_level);
		table_level.setOnClickListener(this);
		
		linear_name=(LinearLayout)findViewById(R.id.linear_nicknamme);
		linear_name.setOnClickListener(this);
		linear_email=(RelativeLayout)findViewById(R.id.linear_email);
		linear_email.setOnClickListener(this);
		linear_follows=(LinearLayout)findViewById(R.id.linear_contactgets);
		linear_follows.setOnClickListener(this);
		linear_resources=(RelativeLayout)findViewById(R.id.linear_resources);
		linear_resources.setOnClickListener(this);
		
		tv_save=(TextView)findViewById(R.id.person_save);
		tv_save.setOnClickListener(this);
		tv_back=(TextView)findViewById(R.id.person_back);
		tv_back.setOnClickListener(this);
		tv_resources = (TextView)findViewById(R.id.tv_resources);
		tv_resources.setOnClickListener(this);
		
		hd1 = new Handler() {
			@Override
			public void handleMessage(Message msg) {

				super.handleMessage(msg);
				Bundle b;
				b = msg.getData();// ��ȡ��Ϣ�е�����
				String msgStr = b.getString("msg");// ��ȡ�����ַ���
				Toast.makeText(RegisterActivity.this, msgStr, Toast.LENGTH_SHORT)
						.show();
				if(msgStr.equals("success")){
					Toast.makeText(RegisterActivity.this, "�޸ĳɹ�", Toast.LENGTH_SHORT)
					.show();
					RegisterActivity.this.finish();
				}else
					Toast.makeText(RegisterActivity.this, "�޸�ʧ��", Toast.LENGTH_SHORT)
					.show();
			}
		};// //handler
	}
	@Override
	public void onClick(View v){
		
		switch(v.getId()){
		case R.id.person_save:
			initView();//获得编辑框里面的值ֵ
			modify();
			//menu.toggle();
			break;
		case R.id.person_back:
			RegisterActivity.this.finish();
			break;
		case R.id.tv_resources:
			Intent intintent = new Intent();
			intintent.setAction(Intent.ACTION_VIEW);
			intintent.setData(Uri.parse(person_resources));
			startActivity(intintent);
			break;
		case R.id.table_level:
			dialog = new AlertDialog.Builder(v.getContext()).create();
			dialog.setTitle("您的等级为：");
			dialog.setMessage("100");
			break;
		default:
			break;
		}
	}

	
	public void modify() {
		RegisterDao dao = new RegisterDao(RegisterActivity.this);
//		loginBean = new PersonBean(person_name, person_resources, person_email);
//		if(dao.check(person_email)){//更改注册email信息
//			dao.register(loginBean);
//			LoginActivity.setLoginBean(loginBean);
//		}else{//e_mail账号存在
//			dao.update(loginBean);
//			LoginActivity.setLoginBean(loginBean);
//			Toast.makeText(RegisterActivity.this, "您新的用户名是："+person_name+"\n"+"您新的资源链接方式是："+person_resources, 4000).show();
//		}
		
//		//parrams:user_id,username,email,resources
//		final Map<String, String>map=new HashMap<String, String>();
//		//map.put("user_id", perbean.getPerson_id().toString());
//		map.put("person_name", person_name);
//		map.put("e_mail", person_email);
//		map.put("project_resource", person_resources);
//		new Thread(){
//			public void run(){
//				try{
//					String msgStr=HttpUploadUtil.postWithoutFile(url, map);
//					Bundle b = new Bundle();
//					// �������ַ����Ž�����Bundle��
//					b.putString("msg", msgStr);
//					// ��������Ϣ����
//					/*Message msg = new Message();*/
//					Message msg=hd1.obtainMessage();
//					// ��������Bundle����Ϣ��
//					msg.setData(b);
//					// ������Ϣ��ʶ
//					msg.what = Constant.PerseonActivity;
//					// ������Ϣ
//					hd1.sendMessage(msg);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		}.start();
		
	}
	
}
