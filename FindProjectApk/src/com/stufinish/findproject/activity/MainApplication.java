package com.stufinish.findproject.activity;

import android.app.Application;

import com.stufinish.findproject.R;

public class MainApplication extends Application{
	
	private int pageNum=5;
	private static MainApplication mInstance = null;
	private int tag;//标明退出的是哪个activity，初始化为第一个
//	public static MainApplication getInstance() {
//        
//        if(mInstance==null) {
//            mInstance = new MainApplication();
//        }
//        return mInstance;
//    }
	public MainApplication() {
		super();
	}
//	public void close(){//完全退出程序
////		super.
//	}
	@Override
	public void onCreate() {
		super.onCreate();
		setTag(R.id.message_radio);
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
}
