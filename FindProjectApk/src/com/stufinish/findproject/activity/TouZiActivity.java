package com.stufinish.findproject.activity;

import java.io.UnsupportedEncodingException;
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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.RenZhenAdapter;
import com.stufinish.findproject.adapter.TouziAdapter;
import com.stufinish.findproject.model2.RenZheng;
import com.stufinish.findproject.model2.TouziBean;
import com.stufinish.findproject.utils.Constant;

public class TouZiActivity extends BaseActivity implements OnClickListener{
	
	private ListView lv_touzi;
	private String x;
	private ArrayList<TouziBean> touziList;
	private Handler handler;
	private TouziAdapter adapter;
	private ImageView img_back;
	private TextView tv_touzi;
	private SharedPreferences sp;
	private String personName,person_resource,email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_touzi);
		initView();
//		new Thread(new Runnable() {
//			public void run() {
//				Log.i("run(", "线程开始");
//				x = send(Constant.touziUrl);// 调用send()获取返回的字符串。
//				touziList = jxJSON(x);
//				Message msg = handler.obtainMessage();
//				handler.sendMessage(msg);
//			}
//		}).start();
//		handler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				adapter = new TouziAdapter(TouZiActivity.this, touziList);
//				lv_touzi.setAdapter(adapter);
//				super.handleMessage(msg);
//			}
//		};
	}
	
	private void initView() {
		img_back = (ImageView)findViewById(R.id.back_touzi);
		lv_touzi = (ListView)findViewById(R.id.lv_project_touzi);
		tv_touzi = (TextView) findViewById(R.id.tv_total_gets);
		img_back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_touzi:
			finish();
			break;

		default:
			break;
		}
	}
	private String send(String renzhenUrl) {
		String str = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(renzhenUrl);
		sp = getSharedPreferences("loginconfig", MODE_PRIVATE);
		email = sp.getString("loginPassword", "");
		personName = sp.getString("loginName", "");
		NameValuePair loginpasvaluePair = new BasicNameValuePair("email",
				email);
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
	private ArrayList<TouziBean> jxJSON(String result) {
		ArrayList<TouziBean> list = new ArrayList<TouziBean>();
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
				int touzi_gets = obj.getInt("touzi_gets");
				String proj_id = obj.getString("proj_id");
				String touzi_time = obj.getString("touzi_time");
				TouziBean touzi_info = new TouziBean(proj_id, touzi_gets, touzi_time);
				list.add(touzi_info);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	
}
