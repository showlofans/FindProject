package com.stufinish.findproject.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class SendJson {

	public static String send(String constanturl) {
		String str = null;
		String url = constanturl;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		Log.i("send(", "try外面");
		HttpResponse response;
		try {
			// Log.i("send(", "执行请求1");
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

	/**
	 * 网络数据请求
	 * 
	 * @return
	 */
	public static String requestData(String url) {
		int i;
		
		HttpGet get = new HttpGet(url);
//		get.setParams(params)
		HttpClient client = new DefaultHttpClient();
		String str = null;
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
					str = EntityUtils.toString(response.getEntity()).trim();
					Log.i("send(", str);
					return str;
				} else {
					str = "请求失败";
					Log.i("send(", "请求失败");
					return str;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
