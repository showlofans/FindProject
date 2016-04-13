package com.stufinish.findproject.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.util.Log;

import com.stufinish.findproject.activity.LoginActivity;
import com.stufinish.findproject.model2.PersonBean;
import com.stufinish.findproject.utils.Constant;

public class UserServiceImpl implements UserService {

	private static final String TAG = "UserServiceImpl";

	@Override
	public void userLogin(String loginName, String loginPassword)
			throws Exception {
		Log.d(TAG, loginName);
		Log.d(TAG, loginPassword);

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		//客户端请求超时
		HttpConnectionParams.setConnectionTimeout(params, 4000);
		//服务端响应超时
		HttpConnectionParams.setSoTimeout(params, 5000);
		SchemeRegistry schreg = new SchemeRegistry();
		schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 440));
		schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schreg);
		
		HttpClient client = new DefaultHttpClient(conman, params);
		String uri = Constant.loginUrl;
		HttpPost post = new HttpPost(uri);
		NameValuePair loginNamevaluePair = new BasicNameValuePair("LoginName", loginName);
		NameValuePair loginpasvaluePair = new BasicNameValuePair("LoginPassword", loginPassword);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(loginNamevaluePair);
		parameters.add(loginpasvaluePair);
		post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
		HttpResponse response = client.execute(post);
		
//		HttpClient client = new DefaultHttpClient();
//		String uri = Constant.loginUrl + "?LoginName=" + loginName
//				+ "&LoginPasssword=" + loginPassword;
//		HttpGet get = new HttpGet(uri);
//		HttpResponse response = client.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode != HttpStatus.SC_OK){
			throw new ServiceRulesException(LoginActivity.MSG_SERVER_ERROR);
		}
			
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		if(result.equals("success")){
			LoginActivity.loginBean = new PersonBean(loginName, 
					loginPassword);
		}else if(result.equals("nonexist")){
			throw new ServiceRulesException(LoginActivity.MSG_LOGIN_FAILED);
		}else{
			throw new ServiceRulesException(LoginActivity.MSG_LOGINPASS_FAILED);
		}
	}

	@Override
	public void userRegister(String loginName, String loginPassword, String resource)
			throws Exception {
		Log.d(TAG, loginName);
		Log.d(TAG, loginPassword);
		Log.d(TAG, resource);

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		//客户端请求超时
		HttpConnectionParams.setConnectionTimeout(params, 4000);
		//服务端响应超时
		HttpConnectionParams.setSoTimeout(params, 5000);
		SchemeRegistry schreg = new SchemeRegistry();
		schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 440));
		schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schreg);
		
		HttpClient client = new DefaultHttpClient(conman, params);
		String url = Constant.addPersonJ;
		HttpPost post = new HttpPost(url);
		NameValuePair loginNamevaluePair = new BasicNameValuePair("LoginName", loginName);
		NameValuePair resourceNamevaluePair = new BasicNameValuePair("resource", resource);
		NameValuePair loginpasvaluePair = new BasicNameValuePair("LoginPassword", loginPassword);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(loginNamevaluePair);
		parameters.add(loginpasvaluePair);
		parameters.add(resourceNamevaluePair);
		post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
		HttpResponse response = client.execute(post);
		
		/*HttpClient client = new DefaultHttpClient();
		String uri = Constant.loginUrl + "?LoginName=" + loginName
				+ "&LoginPasssword=" + loginPassword;
		HttpGet get = new HttpGet(uri);
		HttpResponse response = client.execute(get);
		*/
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode != HttpStatus.SC_OK){
			throw new ServiceRulesException(LoginActivity.MSG_SERVER_ERROR);
		}
			
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		if(result.equals("success")){
		}else if(result.equals("nonexist")){
			throw new ServiceRulesException(LoginActivity.MSG_LOGIN_FAILED);
		}else{
			throw new ServiceRulesException(LoginActivity.MSG_LOGINPASS_FAILED);
		}
	}
}
