package com.stufinish.findproject.activity;

import com.stufinish.findproject.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ResWebActivity extends Activity {

	private WebView web;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.res_web);
		web = (WebView) findViewById(R.id.web_res);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			url = bundle.getString("resUrl");
		}
		web.loadUrl(url);
		web.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}

		});
		WebSettings set = web.getSettings();
		set.setJavaScriptEnabled(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (web.canGoBack()) {
				web.goBack();// 返回上一页面
				return true;
			}
			// else
			// {
			// System.exit(0);//退出程序
			// }
		}
		return super.onKeyDown(keyCode, event);
	}

}
