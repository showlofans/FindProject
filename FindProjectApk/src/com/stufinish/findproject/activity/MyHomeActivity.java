package com.stufinish.findproject.activity;

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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.MyAdapter;
import com.stufinish.findproject.dao.PicDao;
import com.stufinish.findproject.model2.LevelBean;
import com.stufinish.findproject.model2.PersonBean;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.service.Send;
import com.stufinish.findproject.utils.Constant;
import com.stufinish.findproject.utils.File2StringUtil;
import com.stufinish.findproject.utils.NetUtils;

public class MyHomeActivity extends Activity implements OnClickListener,
		SwipeRefreshLayout.OnRefreshListener, OnScrollListener {
	private static final String TAG = "MyHomeActivity";// 定义当前标签
	private static final int resultPicCode = 1;
	private static int my_home_count = -1;
	private RelativeLayout personlayout;
	private ImageView iv_headpic;
	private TextView tv_name, tv_resource;
	private ListView lv_container;
	private List<ProjectBean> list;
	private String x;
	private Handler handler;
	private ArrayList<LevelBean> levelList;
	private SharedPreferences sp;
	private String personName, person_resource;
	private MyAdapter adapter;
	private PersonBean pbean = LoginActivity.loginBean;
	private String email = pbean.getE_mail();
	private Send send;
	// private RefreshLayout swiplayout;
	private SwipeRefreshLayout swiplayout;
	// 加载更多
	private ProgressBar pbBar;
	private Button bt_load_more;
	private View v_moreView;
	private static int index = 1;// 默认当前页
	private String sizes = "5";// 每页的大小
	private int num;// 数据总记录数
	private String tag;
	// private int MaxDataNum;
	private int LastVisibleIndex = 0;// 可视最后索引

	private String pic_result;
	private String fileName;
	private PicDao dao = new PicDao(MyHomeActivity.this);
	private ProjectBean proj;
	private Handler hd_refresh;

	// private Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hmy_home);

		initWidget();
		initView();

		// send = new SendService();
		lv_container.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(MyHomeActivity.this,
						ProjectDetailActivity.class);
				intent.putExtra("project", adapter.getItem(position));
				intent.putExtra("my_change", "my_service");
				adapter.setSelectItem(position);
				startActivity(intent);
			}
		});
		if (NetUtils.isConnected(MyHomeActivity.this)) {
			startRefresh();
		}
		list = new ArrayList<ProjectBean>();
		hd = new Handler() {
			public void handleMessage(Message msg) {
				// levelList = projService.getLevelList();
				if (pic_result.equals("success")) {
					Toast.makeText(MyHomeActivity.this, fileName,
							Toast.LENGTH_LONG).show();
					// pbean.setPic_file(fileName);
					PersonBean pb = new PersonBean(pbean.getE_mail(), fileName);
					dao.updatePic(pb);
					iv_headpic.setImageBitmap(BitmapFactory
							.decodeFile(fileName));
				} else {
					Toast.makeText(MyHomeActivity.this, "更新失败",
							Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
			}
		};
		adapter = new MyAdapter(MyHomeActivity.this, list);
		lv_container.setAdapter(adapter);
		// 下拉刷新RefreshThread
		hd_refresh = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String tagMsg = msg.obj.toString();
				Log.i("msg", tagMsg);
				// Toast.makeText(Service2.this, index,
				// Toast.LENGTH_SHORT).show();
				if (tagMsg.equals("0")) {
					adapter = new MyAdapter(MyHomeActivity.this, list);
					lv_container.setAdapter(adapter);
					index = 2;
				} else if (tagMsg.equals("1")) {
					Toast.makeText(MyHomeActivity.this, "刷新数据失败",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MyHomeActivity.this, "网路异常2",
							Toast.LENGTH_SHORT).show();
				}
				if (swiplayout.getVisibility() == View.VISIBLE)
					swiplayout.setRefreshing(false);
				super.handleMessage(msg);

			}
		};
		swiplayout = (SwipeRefreshLayout) findViewById(R.id.swip_container);
		swiplayout.setColorSchemeResources(android.R.color.holo_blue_light,
				android.R.color.holo_red_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_green_light);
		swiplayout.setOnRefreshListener(this);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// levelList = projService.getLevelList();
				index++;
				String tagMsg = msg.obj.toString();
				Log.i("msg", tagMsg);
				// Toast.makeText(Service2.this, index,
				// Toast.LENGTH_SHORT).show();
				if (tagMsg.equals("0")) {
					adapter.notifyDataSetChanged();
				} else if (tagMsg.equals("1")) {
					Toast.makeText(MyHomeActivity.this, "加载数据失败",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MyHomeActivity.this, "网路异常2",
							Toast.LENGTH_SHORT).show();
				}
				if (swiplayout.getVisibility() == View.VISIBLE)
					swiplayout.setRefreshing(false);
				super.handleMessage(msg);
			}
		};
	}
	private void loadStart() {
		sp = getSharedPreferences("page", MODE_PRIVATE);
		sizes = sp.getInt("pageNum", 10) + "";
		new GoThread().start();
	}
	private void startRefresh() {
			index = 1;
			sp = getSharedPreferences("page", MODE_PRIVATE);
			sizes = sp.getInt("pageNum", 10) + "";
			new RefreshThread().start();
	}

	// public static String Serch(String e_mail, String url) {
	// String sss = null;
	//
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpPost request = new HttpPost(url);
	// try {
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// params.add(new BasicNameValuePair("email", e_mail)); // 用户名
	// request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
	// HttpResponse response = httpclient.execute(request);
	// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	// sss = EntityUtils.toString(response.getEntity()).trim(); // 获取返回的字符串
	// } else {
	// sss = null;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return sss;
	// }

	private void initWidget() {
		personlayout = (RelativeLayout) findViewById(R.id.person_layout);
		personlayout.setOnClickListener(this);
		iv_headpic = (ImageView) findViewById(R.id.headpic);
		iv_headpic.setOnClickListener(this);
		lv_container = (ListView) findViewById(R.id.lv_myproject);
		v_moreView = getLayoutInflater().inflate(R.layout.refresh_button, null);
		bt_load_more = (Button) v_moreView.findViewById(R.id.bt_load);
		pbBar = (ProgressBar) v_moreView.findViewById(R.id.pg);
		lv_container.addFooterView(v_moreView);
		bt_load_more.setOnClickListener(this);
		lv_container.setOnScrollListener(this);
	}

	class GoThread extends Thread {

		@Override
		public void run() {
			Log.i("run(", "线程开始");
			String re = httpGet(index, sizes, email, false);
			Message msg = handler.obtainMessage();
			msg.obj = re;
			handler.sendMessage(msg);
		}

	}

	class RefreshThread extends Thread {

		@Override
		public void run() {
			Log.i("run(", "线程开始");
			String re = httpGet(index, sizes, email, true);
			Message msg = hd_refresh.obtainMessage();
			msg.obj = re;
			hd_refresh.sendMessage(msg);
		}

	}

	private String httpGet(int index, String sizes3, String pEmail, Boolean b) {
		String sss = null;
		String re = "0";
		String isok = "0";
		String url = Constant.myProjectUrl;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("index", index + "")); // 当前页
			params.add(new BasicNameValuePair("sizes", sizes3)); // 页数
			params.add(new BasicNameValuePair("email", pEmail)); // 账号
			// params.add(new BasicNameValuePair("type", gtype)); // 账号
			request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				sss = EntityUtils.toString(response.getEntity()).trim(); // 获取返回的字符串
			} else {
				sss = null;
			}
			JSONObject json = new JSONObject(sss);
			isok = json.getString("isok");
			num = json.getInt("num");
			Log.i("msg", "------>sss" + sss);
			Log.i("msg", "------>isok" + isok);
			Log.i("msg", "------>num" + num);
			if(num==0){
				Toast.makeText(MyHomeActivity.this, "您还没有添加项目！",
						Toast.LENGTH_LONG).show();
			}
			// Toast.makeText(Service2.this, isok, Toast.LENGTH_LONG).show();
			if ("0".equals(isok)) {
				JSONObject object = json.getJSONObject("data");
				JSONArray array = object.getJSONArray("projlist");
				if (b && list != null) {
					list.clear();
				}
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.opt(i);
					int proj_id = obj.getInt("project_id");
					String proj_link = obj.getString("project_link");
					String proj_type = obj.getString("project_type");
					String proj_mind = obj.getString("project_mind");
					String proj_theme = obj.getString("project_theme");
					String proj_description = obj
							.getString("content_description");
					String proj_time = obj.getString("project_time");
					String proj_mail = obj.getString("person_email");
					proj = new ProjectBean(proj_id, proj_link, proj_type,
							proj_mind, proj_theme, proj_description, proj_time,
							proj_mail);
					list.add(proj);
				}
			} else {
				re = "1";// 获取数据失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			re = "2";
		}
		return re;
	}

	private void initView() {
		// sp = getSharedPreferences("picfile", Activity.MODE_PRIVATE);
		// picturePath = sp.getString("pic", "");
		picturePath = dao.getPic(pbean.getE_mail());
		if (picturePath != null) {
			iv_headpic.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		}

		tv_name = (TextView) findViewById(R.id.hmyhome_nickkname);
		tv_resource = (TextView) findViewById(R.id.hmyhome_resources);

		tv_name.setText(pbean.getPerson_name());
		tv_resource.setText(pbean.getProject_resource());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_load:
			loadStart();
			break;
		case R.id.person_layout:
			Intent intent = new Intent(MyHomeActivity.this,
					RegisterActivity.class);
			intent.putExtra("person", pbean);
			startActivity(intent);
			break;
		case R.id.headpic:
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, resultPicCode);
			break;
		/*
		 * case R.id.mime_layout_01:
		 * 
		 * Intent photointent=new Intent(MimePageActivity.this,
		 * MimePhotoActivity.class); startActivity(photointent);
		 * 
		 * break;
		 */
		default:
			break;
		}
	}

	private Handler hd;// 从图库上传照片通讯
	private String picturePath;// 图片路径

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == resultPicCode && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			fileName = cursor.getString(columnIndex);
			picturePath = File2StringUtil.file2String(fileName);// storage/sdcard0/screenshot/20160329_190159.png
			cursor.close();
			new Thread(new Runnable() {

				@Override
				public void run() {
					pic_result = getPic(picturePath);
					Message msg = hd.obtainMessage();
					hd.sendMessage(msg);
				}
			}).start();
		}
	}

	private String getPic(String picturePath2) {
		String sss = null;
		String turl = Constant.imgUrl;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(turl);
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", LoginActivity.loginBean
					.getE_mail())); //
			params.add(new BasicNameValuePair("picPath", picturePath2)); //

			request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				sss = EntityUtils.toString(response.getEntity()).trim(); // 获取返回的字符串
			} else {
				sss = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sss;

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		/**
		 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=4（这里因为服务端有4页数据）时，加载更多数据。
		 */
		if (LastVisibleIndex == adapter.getCount()
				&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {

			if (LastVisibleIndex == num) {
				lv_container.removeFooterView(v_moreView);
				Toast.makeText(MyHomeActivity.this, "全部数据加载完成",
						Toast.LENGTH_SHORT).show();
			} else {
				loadStart();
				Log.i("msg", "index=" + index);
				// adapter.notifyDataSetChanged();
				Log.i("msg", "OnScrollStateChanged--lastVisibleIndex="
						+ LastVisibleIndex);
				Log.i("msg",
						"nScrollStateChanged--adapter.getCount="
								+ adapter.getCount());
				Log.i("msg", "num=" + num);
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		LastVisibleIndex = firstVisibleItem + visibleItemCount - 1;
		Log.i("msg", "LastVisibleIndex=" + LastVisibleIndex);
	}

	@Override
	public void onRefresh() {
		startRefresh();
	}

	public static int getMy_home_count() {
		return ++my_home_count;
	}

	private static long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					SharedPreferences sp = getSharedPreferences("tab", MODE_PRIVATE);
					Editor editor = sp.edit();
					editor.putInt("id", R.id.news_radio);
					editor.commit();
					Toast.makeText(this, "关闭应用", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
//					MainActivity.setId(R.id.news_radio);
					SharedPreferences sp = getSharedPreferences("tab", MODE_PRIVATE);
					Editor editor = sp.edit();
					editor.putInt("id", R.id.news_radio);
					editor.commit();
////					MainApplication application = MainApplication.getInstance();
//					MainApplication.setTag(R.id.news_radio);
					finish();
				}
				return false;
			}
			
		return super.onKeyDown(keyCode, event);
	}
}
