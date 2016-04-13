//package com.stufinish.findproject.activity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.stufinish.findproject.R;
//import com.stufinish.findproject.adapter.ProjectAdapter;
//import com.stufinish.findproject.model2.ProjectBean;
//import com.stufinish.findproject.utils.Constant;
//import com.stufinish.findproject.utils.InitPopWindow;
//import com.stufinish.findproject.utils.RefreshableView;
//import com.stufinish.findproject.utils.RefreshableView.PullToRefreshListener;
//import com.stufinish.findproject.utils.SendJson;
//
///*
// * 功 能：加载项目列表
// * 界 面：和MainActivity联系在一起
// * 主界面：item
// */
//public class ProjectsService extends BaseActivity implements OnClickListener,
//		OnScrollListener {
//	public static final String TAG = "ProjectsService";// 定义当前标签
//	private ListView lv;
//	private ProjectAdapter adapter;
//	private List<ProjectBean> list;
//	private boolean is_divPage;// 是否进行分页操作
//	private List<ProjectBean> oneTotal = new ArrayList<ProjectBean>();// 用来存放一页数据
//	private ProjectBean pbean;
//	private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
//
//	// private PersonBean personBean;
//	private String x;
//	private RefreshableView refreshableView;
//	private Handler handler;
//	private Button bt_home, bt_functiino;
//	// private TextView tv_head_deploy;
//	private TextView sp_head_deploy;
//	private String type;
//	public Thread thread1;
//	private String p; // 搜索得到的结果
//	private String projUrl = Constant.servletUrl;
//
//	// 加载更多
//	private ProgressBar pbBar;
//	private Button bt_load_more;
//	private View v_moreView;
//	private int MaxDataNum;
//	private int LastVisibleIndex;
//	private Handler hd;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.item);
//		initView();
//
//		lv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// Toast.makeText(ProjectsService.this, "vdsf",
//				// Toast.LENGTH_LONG).show();
//				Intent detailIntent = new Intent(ProjectsService.this,
//						ProjectDetailActivity.class);
//				detailIntent.putExtra("project", adapter.getItem(position));
//				// getintent.putExtra("my_change", 0);
//				startActivity(detailIntent);
//				ProjectsService.this.overridePendingTransition(
//						R.anim.ani_anima_comb, 0);
//			}
//		});
//		list = new ArrayList<ProjectBean>();
//		// new Thread(new Runnable() {
//		// public void run() {
//		// Log.i("run(", "线程开始");
//		// x = SendJson.send(projUrl);// 调用send()获取返回的字符串。
//		// list = jxJSON(x);
//		// Message msg = handler.obtainMessage();
//		// handler.sendMessage(msg);
//		// }
//		// }).start();
////		handler = new Handler() {
////			@Override
////			public void handleMessage(Message msg) {
////				adapter = new ProjectAdapter(ProjectsService.this, oneTotal);
////				lv.setAdapter(adapter);
////				adapter.notifyDataSetChanged();
////				super.handleMessage(msg);
////			}
////		};
//		refreshableView = (RefreshableView) findViewById(R.id.refresh);
//		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
//			@Override
//			public void onRefresh() {
//				is_divPage = true;
//				pageNo = 1;
//				// Thread.sleep(3000);
////				new MyTask().execute(projUrl);
//				new MyTask().execute(projUrl + pageNo);
//				refreshableView.finishRefreshing();
//			}
//		}, 0);
//
//		/**
//		 * 用来获取数据...
//		 */
////		new MyTask().execute(projUrl);
//		new MyTask().execute(projUrl + pageNo);
//	}
//
//	/**
//	 * MyTask继承线程池AsyncTask用来网络数据请求、json解析、数据更新等操作。
//	 */
//	class MyTask extends AsyncTask<String, Void, String> {
//		/**
//		 * 数据请求前显示dialog。
//		 */
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			pbBar.setVisibility(View.VISIBLE);
//			bt_load_more.setVisibility(View.GONE);
//			// dialog.show();
//		}
//
//		/**
//		 * 在doInBackground方法中，做一些诸如网络请求等耗时操作。
//		 */
//		@Override
//		protected String doInBackground(String... params) {
////			 return SendJson.requestData(projUrl);
//			return SendJson.requestData(projUrl + pageNo);
//		}
//
//		/**
//		 * 在该方法中，主要进行一些数据的处理，更新。
//		 */
//		@Override
//		protected void onPostExecute(String result) {
//			super.onPostExecute(result);
//			if (result != null) {
//				if (result.equals("finish")) {
//					Toast.makeText(ProjectsService.this, "数据已满...",
//							Toast.LENGTH_LONG).show();
//					is_divPage = false;
////					pageNo = 1;
//				} else {
//					// 如果获取的result数据不为空，那么对其进行JSON解析。并显示在手机屏幕上。
//					oneTotal = jxJSON(result);
//					
//					adapter = new ProjectAdapter(ProjectsService.this, oneTotal);
//					/**
//					 * 当pageNo等于1的时候才会setAdapter，以后不会再设置，直接notifyDataSetChanged，
//					 * 进行数据更新 ，这样可避免每次加载更多数据的时候，都会重新回到第一页。
//					 */
//					if (pageNo == 1) {
//						lv.setAdapter(adapter);
//					}
//					adapter.notifyDataSetChanged();
//					pageNo++;
//				}
//				
//				pbBar.setVisibility(View.GONE);
//				bt_load_more.setVisibility(View.VISIBLE);
//			} else if (result == null) {
//				Toast.makeText(ProjectsService.this, "请求数据失败...",
//						Toast.LENGTH_LONG).show();
//			}
//			// dialog.dismiss();
//		}
//	}
//
//	private void initView() {
//		lv = (ListView) findViewById(R.id.lv_lv);// 找到listview
//		v_moreView = getLayoutInflater().inflate(R.layout.refresh_button, null);
//		bt_load_more = (Button) v_moreView.findViewById(R.id.bt_load);
//		pbBar = (ProgressBar) v_moreView.findViewById(R.id.pg);
//		hd = new Handler();
//		lv.addFooterView(v_moreView);
//		bt_load_more.setOnClickListener(this);
//		lv.setOnScrollListener(this);
//
//		sp_head_deploy = (TextView) findViewById(R.id.sp_head_deploy);
//		bt_home = (Button) findViewById(R.id.bt_my_space);
//		sp_head_deploy.setOnClickListener(this);
//		bt_functiino = (Button) findViewById(R.id.item_home);
//		bt_home.setOnClickListener(this);
//		bt_functiino.setOnClickListener(this);
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.bt_load:
//			if(is_divPage)
//			new MyTask().execute(projUrl + pageNo);
//			// pbBar.setVisibility(v.VISIBLE);
//			// bt_load_more.setVisibility(v.GONE);
//			//
//			// hd.postDelayed(new Runnable() {
//			//
//			// @Override
//			// public void run() {
//			// // loadMoreData();
//			// pbBar.setVisibility(View.VISIBLE);
//			// bt_load_more.setVisibility(View.GONE);
//			// bt_load_more.setVisibility(View.VISIBLE);
//			// adapter.notifyDataSetChanged();
//			// }
//			// }, 2000);
//
//			break;
//		case R.id.item_home:
//			super.showShortToast(bt_functiino.getText().toString());
//			break;
//		case R.id.bt_my_space:
//			View rightView = ProjectsService.this.getLayoutInflater().inflate(
//					R.layout.layout_button, null, false);
//			PopupWindow popRight = new PopupWindow(rightView, 180, 210);
//			InitPopWindow rightWindow = new InitPopWindow(rightView,
//					"rightView", popRight);
//			rightWindow.getWindow();
//			rightWindow.popwindow.showAsDropDown(v, 1, 5);
//			break;
//		case R.id.sp_head_deploy:
//			View headView = ProjectsService.this.getLayoutInflater().inflate(
//					R.layout.layout_head_button, null, false);
//			PopupWindow popHead = new PopupWindow(headView, 180, 210);
//			thread1 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					p = search(type);
//					list = jxJSON(p);
//					Message msg = handler.obtainMessage();
//					handler.sendMessage(msg);
//				}
//			});
//			InitPopWindow headWindow = new InitPopWindow(headView, "headView", popHead);
//			headWindow.getWindow();
//			headWindow.setThread(thread1);
//			headWindow.popwindow.showAsDropDown(v, -30, 5);
//		default:
//			break;
//		}
//	}
//
//	//
//	// protected void loadMoreData() {
//	// int count = adapter.getCount();
//	// if(count + 10 < MaxDataNum){
//	//
//	// }
//	//
//	// }
//
//	private String search(String strp) {
//		String sss = null;
//		String url = Constant.typeUrl;
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost request = new HttpPost(url);
//		try {
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			params.add(new BasicNameValuePair("search", strp)); // 用户名
//			request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
//			HttpResponse response = httpclient.execute(request);
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				sss = EntityUtils.toString(response.getEntity()).trim(); // 获取返回的字符串
//			} else {
//				sss = null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sss;
//	}
//
//	public List<ProjectBean> jxJSON(String result) {
//		List<ProjectBean> ll = new ArrayList<ProjectBean>();
//		if (result == null) {
//			return null;
//		}
//		try {
//			JSONArray jsonarr = new JSONArray(result);
//			for (int i = 0; i < jsonarr.length(); i++) {
//				JSONObject obj = jsonarr.getJSONObject(i);
//				int proj_id = obj.getInt("project_id");
//				String proj_link = obj.getString("project_link");
//				String proj_type = obj.getString("project_type");
//				String proj_mind = obj.getString("project_mind");
//				String proj_theme = obj.getString("project_theme");
//				String proj_description = obj.getString("content_description");
//				String proj_time = obj.getString("project_time");
//				String proj_mail = obj.getString("person_email");
//				// String person_name = obj.getString("name");
//				// String person_resource = obj.getString("project_resource");
//				// personBean = new PersonBean(proj_mail, person_name);
//				// pbean = new ProjectBean(proj_id, proj_link, proj_type,
//				// proj_mind, proj_theme, proj_description, proj_time,
//				// proj_mail, person_name);
//				pbean = new ProjectBean(proj_id, proj_link, proj_type, proj_mind,
//						proj_theme, proj_description, proj_time, proj_mail);
//				ll.add(pbean);
//			}
//			return ll;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//		/**
//		 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=4（这里因为服务端有4页数据）时，加载更多数据。
//		 */
//		if (is_divPage && scrollState == OnScrollListener.SCROLL_STATE_IDLE
//				) {
//
//			// Toast.makeText(ProjectsService.this,
//			// "正在获取更多数据...",Toast.LENGTH_SHORT).show();
//			pageNo++;
//			new MyTask().execute(projUrl + pageNo);
//		} else if (pageNo > 4) {
////			/**
////			 * 如果pageNo>4则表示，服务端没有更多的数据可供加载了。
////			 */
////			 Toast.makeText(ProjectsService.this, "没有更多数据啦...",
////			 Toast.LENGTH_SHORT).show();
//		}
//
//	}
//
//	/**
//	 * 当：第一个可见的item（firstVisibleItem）+可见的item的个数（visibleItemCount）=所有的item总数的时候，
//	 * is_divPage变为TRUE，这个时候才会加载数据。
//	 */
//	@Override
//	public void onScroll(AbsListView view, int firstVisibleItem,
//			int visibleItemCount, int totalItemCount) {
//		is_divPage = (firstVisibleItem + visibleItemCount == totalItemCount);
//	}
//}
