package com.stufinish.findproject.service;

import java.util.Map;

import com.stufinish.findproject.utils.HttpUploadUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class NewThread {
	public static void newThread(final Handler hd,final Map<String,String>params,final String url){
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url,
						params);
				Bundle b = new Bundle();
				// 将内容字符串放进数据Bundle中
				b.putString("msg", msgStr);
				// 、创建消息对象
				Message msg = hd.obtainMessage();
				// 设置数据Bundle到消息中
				msg.setData(b);
				// 设置消息标识
				msg.what = 100;
				// 发送消息
				hd.sendMessage(msg);

			} // run

		}.start();// thread
	}
}
