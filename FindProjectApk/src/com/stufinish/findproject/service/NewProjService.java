package com.stufinish.findproject.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.stufinish.findproject.R;
import com.stufinish.findproject.activity.ProjectDetailActivity;
import com.stufinish.findproject.adapter.ProjectAdapter;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.utils.Constant;

public class NewProjService extends Activity {
	private ListView lv;
	private ProjectAdapter adapter;
	private static List<ProjectBean> list;
	private Handler handler;
	private String x;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		lv = (ListView) findViewById(R.id.lv_lv);// 找到listview
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(NewProjService.this,
						ProjectDetailActivity.class);
				intent.putExtra("project", adapter.getItem(position));
				startActivity(intent);
			}
		});
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				adapter = new ProjectAdapter(NewProjService.this, list);
				lv.setAdapter(adapter);
				super.handleMessage(msg);
			}

		};
		list = new ArrayList<ProjectBean>();
		new Thread(new Runnable() {
			public void run() {
				x = send();// 调用send()获取返回的字符串。
				list = jxJSON(x);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}

		}).start();
	}

	private String send() {
		String str = null;
		HttpClient httpclient = new DefaultHttpClient();
		String url = Constant.projectUrl;
		HttpPost request = new HttpPost(url);
		HttpResponse response;
		try {
			response = httpclient.execute(request);// 执行请求
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				str = EntityUtils.toString(response.getEntity()).trim();
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

	private List<ProjectBean> jxJSON(String result) {
		List<ProjectBean> ll = new ArrayList<ProjectBean>();
		if (result == null) {
			return null;
		}
		try {
			JSONArray jsonarr = new JSONArray(result);
			for (int i = 0; i < jsonarr.length(); i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
//				int proj_id = obj.getInt("project_id");
				String proj_link = obj.getString("project_link");
				String proj_type = obj.getString("project_type");
				String proj_mind = obj.getString("project_mind");
				String proj_theme = obj.getString("project_theme");
				String proj_description = obj.getString("content_description");
				String proj_time = obj.getString("project_time");
				String proj_mail = obj.getString("person_email");
//				String person_name = obj.getString("name");
//				String person_resource = obj.getString("project_resource");
//				personBean = new PersonBean(proj_mail, person_name);
//				pbean = new ProjectBean(proj_id, proj_link, proj_type, proj_mind, proj_theme, proj_description, proj_time, proj_mail, person_name);
				ProjectBean pbean = new ProjectBean( proj_link, proj_type,proj_mind, proj_theme, proj_description, proj_time,proj_mail);
				ll.add(pbean);
			}
			return ll;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
