package com.stufinish.findproject.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.RenZhenAdapter;
import com.stufinish.findproject.model2.LevelBean;
import com.stufinish.findproject.model2.PersonBean;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.model2.RenZheng;
import com.stufinish.findproject.service.NewThread;
import com.stufinish.findproject.utils.Constant;
import com.stufinish.findproject.utils.HttpUploadUtil;

public class RenZhenActivity extends BaseActivity implements OnClickListener {
	private RenZheng renzhen;
	// private TextView tv_renzhen_info;
	private ListView listView;
	private ArrayList<RenZheng> renzhenList;
	private String tv_info;
	private String proj_id;
	private Button bt_add, bt_delete;
	private Button bt_deploy;
	private String renUrl = Constant.renzhenUrl;
	private String addrenUrl = Constant.AddRenzhentUrl;
	private Handler handler, hd;
	private String x;
	private int result;
	private EditText et_renzhen;
	private RenZhenAdapter adapter;
	private int edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_renzhen);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			proj_id = bundle.getString("proj_id");
			edit = bundle.getInt("edit");
		}
		initView();
		new Thread(new Runnable() {
			public void run() {
				Log.i("run(", "线程开始");
				x = send(Constant.renzhenUrl);// 调用send()获取返回的字符串。
				renzhenList = jxJSON(x);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				adapter = new RenZhenAdapter(renzhenList, RenZhenActivity.this);
				listView.setAdapter(adapter);
				super.handleMessage(msg);
			}
		};
		

	}

	private void initView() {
		// tv_renzhen_info = (TextView)findViewById(R.id.tv_renzhen_info);
		listView = (ListView) findViewById(R.id.lv_renzhen);
		bt_add = (Button) findViewById(R.id.btn_info_renzhen);
		bt_add.setOnClickListener(this);
		
		bt_delete = (Button) findViewById(R.id.delete_renzhen);
		et_renzhen = (EditText) findViewById(R.id.et_renzhen);
		bt_deploy = (Button) findViewById(R.id.bt_deploy_renzhen);
		if(edit==0){
			bt_delete.setVisibility(View.GONE);
			bt_deploy.setVisibility(View.GONE);
			et_renzhen.setVisibility(View.GONE);
		}
		bt_deploy.setOnClickListener(this);
		renzhenList = new ArrayList<RenZheng>();
		// tv_info = tv_renzhen_info.getText().toString();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_renzhen_info:
			bt_add.setVisibility(v.GONE);
			bt_delete.setVisibility(v.VISIBLE);
			et_renzhen.setVisibility(v.VISIBLE);

			break;
		case R.id.bt_deploy_renzhen:
			tv_info = et_renzhen.getText().toString();
			hd = new Handler() {
				public void handleMessage(Message msg) {
					Log.i("Looper", String.valueOf(msg.what));
					String tag = msg.getData().getString("msg");
					if(tag.equals("添加成功")){
						finish();
					}
					Toast.makeText(RenZhenActivity.this,
							String.valueOf(msg.what), Toast.LENGTH_SHORT)
							.show();
				}
			};
			if (tv_info != "") {
				final Map<String, String> params = new HashMap<String, String>();
				params.put("renzhen_info", tv_info);
				params.put("proj_id", proj_id);
				NewThread.newThread(hd, params, addrenUrl);
//				new Thread() {
//					public void run() {
//						String msgStr = HttpUploadUtil.postWithoutFile(
//								addrenUrl, params);
//						Bundle b = new Bundle();
//						// 将内容字符串放进数据Bundle中
//						b.putString("msg", msgStr);
//						// 、创建消息对象
//						Message msg = new Message();
//						// 设置数据Bundle到消息中
//						msg.setData(b);
//						// 设置消息标识
//						msg.what = 100;
//						// 发送消息
//						hd.sendMessage(msg);
//
//					} // run
//
//				}.start();
			}// thread
		default:
			break;
		}
	}

	private String send(String renzhenUrl) {
		String str = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(renzhenUrl);
		NameValuePair loginpasvaluePair = new BasicNameValuePair("proj_id",
				proj_id);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(loginpasvaluePair);
		try {
			request.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Log.i("send(", "try外面");
		HttpResponse response;
		try {
			Log.i("send(", "执行请求1");
			response = httpclient.execute(request);// 执行请求
			Log.i("response", response.toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				str = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send(", str);
				return str;
			} else {
				str = "请求失败";
				Log.i("send(", "请求失败");
				return str;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;

	}

	private ArrayList<RenZheng> jxJSON(String result) {
		ArrayList<RenZheng> list = new ArrayList<RenZheng>();
		if (result == null) {
			return null;
		}
		try {
			JSONArray jsonarr = new JSONArray(result);
			for (int i = 0; i < jsonarr.length(); i++) {
				// private int renzhen_flag; //认证编号
				// private String renzhen_info; //认证信息
				// private String renzhen_time; //添加认证时间
				JSONObject obj = jsonarr.getJSONObject(i);
				int renzhen_flag = obj.getInt("renzhen_flag");
				String renzhen_info = obj.getString("renzhen_info");
				String renzhen_time = obj.getString("renzhen_time");
				RenZheng info_renzhen = new RenZheng(renzhen_flag,
						renzhen_info, renzhen_time);
				list.add(info_renzhen);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
