package com.stufinish.findproject.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends Activity {

	protected String TAG = getClass().getSimpleName();
	private static ArrayList<Activity> mActivityList = new ArrayList<Activity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mActivityList.add(this);
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mActivityList.remove(this);
	}

	/**
	 * @return 返回栈顶Activity
	 */
	public static Activity GetTopActivity() {
		if (mActivityList.size() <= 0)
			return null;
		return mActivityList.get(mActivityList.size() - 1);
	}

	/**
	 * @param str
	 *            显示短时间toast信息
	 */
	protected void showShortToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	/**
	 * @param str
	 *            显示长时间toast信息
	 */
	protected void showLongToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_LONG).show();
	}
}
