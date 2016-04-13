package com.stufinish.findproject.activity;

import android.app.Application;

import com.stufinish.findproject.R;

public class MainApplication extends Application{
	
	private int pageNum=5;
	private static MainApplication mInstance = null;
	private static int tag=0;//标明退出的是哪个activity，初始化为第一个
	public static MainApplication getInstance() {
        
        if(mInstance==null) {
            mInstance = new MainApplication();
        }
        return mInstance;
    }
	public MainApplication() {
		super();
	}
	public void close(){//完全退出程序
//		super.
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public static int getTag() {
		if(tag==0){
			return R.id.message_radio;
		}
		return tag;
	}
	public static void setTag(int tag) {
		MainApplication.tag = tag;
	}
	
	
}
