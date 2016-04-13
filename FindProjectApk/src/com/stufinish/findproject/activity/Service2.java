package com.stufinish.findproject.activity;

import java.util.ArrayList;
import java.util.HashMap;
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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;
import com.stufinish.findproject.R;
import com.stufinish.findproject.adapter.ProjectAdapter;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.utils.Constant;
import com.stufinish.findproject.utils.InitPopWindow;
import com.stufinish.findproject.utils.NetUtils;

public class Service2 extends BaseActivity implements OnClickListener,
		SwipeRefreshLayout.OnRefreshListener, OnScrollListener {
    private static int service2_count = 0;
	public static final String TAG = "Service2";// 定义当前标签
	public static SlidingMenu menu;
	
	private ListView lv;
	private ProjectAdapter adapter;
	private List<ProjectBean> list;
	private List<HashMap<String, Object>> datalist = new ArrayList<HashMap<String, Object>>();
	private boolean is_divPage;// 是否进行分页操作
	private List<ProjectBean> oneTotal = new ArrayList<ProjectBean>();// 用来存放一页数据
	private ProjectBean pbean;
	private static int proj_d_count = -1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。

	// private PersonBean personBean;
	private String x;
	private SwipeRefreshLayout refreshableView;
	private Handler handler;
	private Button bt_home, bt_functiino;
	// private TextView tv_head_deploy;
	private TextView sp_head_deploy;
	private static String type = "项目";
	public Thread thread1;
	private String p; // 搜索得到的结果
	private String projUrl = Constant.projectUrl;
	private Button bt_app_head, bt_web_head, bt_level_head, bt_all_head;
	private View headView;
	private InitPopWindow headWindow;
	private String email = LoginActivity.loginBean.getE_mail();

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
	private Handler hd;
	private PopupWindow popHead;

	private SharedPreferences sp;
	private Editor editor;
	private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7;

	// private MainApplication mainApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);

		initView();
		// mainApp = MainApplication.getInstance();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(ProjectsService.this, "vdsf",
				// Toast.LENGTH_LONG).show();
				Intent detailIntent = new Intent(Service2.this,
						ProjectDetailActivity.class);
				detailIntent.putExtra("project", adapter.getItem(position));
				detailIntent.putExtra("my_change", "service");
				startActivity(detailIntent);
				getProj_d_count();
				Service2.this.overridePendingTransition(R.anim.ani_anima_comb,
						0);
			}
		});
		if (NetUtils.isConnected(Service2.this)) {
			refreshStart();
			/*
			 * new Thread(new Runnable() { public void run() { Log.i("run(",
			 * "线程开始"); x =
			 * MyHomeActivity.Serch(LoginActivity.loginBean.getE_mail(),
			 * projUrl); list = jxJSON(x); Message msg =
			 * handler.obtainMessage(); handler.sendMessage(msg); } }).start();
			 */
		}
		
//		new Thread(new Runnable() {
//			public void run() {
//				Log.i("run(", "线程开始");
//				String re = httpGet(index, sizes, email,type);
//				Message msg = handler.obtainMessage();
//				msg.obj = re;
//				handler.sendMessage(msg);
//			}
//		}).start();
		list = new ArrayList<ProjectBean>();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				++index;
				String tagMsg = msg.obj.toString();
				Log.i("msg", tagMsg);
				// Toast.makeText(Service2.this, index,
				// Toast.LENGTH_SHORT).show();
				if (tagMsg.equals("0")) {
					adapter.notifyDataSetChanged();
				} else if (tagMsg.equals("1")) {
					Toast.makeText(Service2.this, "加载数据失败", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(Service2.this, "网路异常2", Toast.LENGTH_SHORT)
							.show();
				}
				super.handleMessage(msg);
			}
		};
		//下拉刷新RefreshThread
		hd = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				
				String tagMsg = msg.obj.toString();
				Log.i("msg", tagMsg);
				// Toast.makeText(Service2.this, index,
				// Toast.LENGTH_SHORT).show();
				if (tagMsg.equals("0")) {
					adapter =new ProjectAdapter(Service2.this, list);
					lv.setAdapter(adapter);
					index = 2;
				} else if (tagMsg.equals("1")) {
					Toast.makeText(Service2.this, "加载数据失败", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(Service2.this, "网路异常2", Toast.LENGTH_SHORT)
							.show();
				}
				if(refreshableView.getVisibility()==View.VISIBLE)
					refreshableView.setRefreshing(false);
				super.handleMessage(msg);
			
			}
		};
		refreshableView = (SwipeRefreshLayout) findViewById(R.id.refresh);
		refreshableView.setColorSchemeResources(
				android.R.color.holo_blue_light,
				android.R.color.holo_red_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_green_light);
		refreshableView.setOnRefreshListener(this);
		// refreshableView.setOnRefreshListener(new PullToRefreshListener() {
		// @Override
		// public void onRefresh() {
		// is_divPage = true;
		// pageNo = 1;
		// // Thread.sleep(3000);
		// // new MyTask().execute(projUrl);
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// if (type.equals("")) {
		// p = MyHomeActivity.Serch(
		// LoginActivity.loginBean.getE_mail(),
		// projUrl);
		// } else {
		// p = search(type);
		// }
		// list = jxJSON(p);
		// Message msg = handler.obtainMessage();
		// handler.sendMessage(msg);
		// }
		// }).start();
		// // new MyTask().execute(projUrl + pageNo);
		// refreshableView.finishRefreshing();
		// }
		// }, 0);

		/**
		 * 用来获取数据...
		 */
		// new MyTask().execute(projUrl);
		// new MyTask().execute(projUrl + pageNo);
	}

	private void loadStart() {
		sp = getSharedPreferences("page", MODE_PRIVATE);
		sizes = sp.getInt("pageNum", 10) + "";
		new GoThread().start();
	}

	/**
	 * MyTask继承线程池AsyncTask用来网络数据请求、json解析、数据更新等操作。
	 */

	private void initView() {

		// if (mainApp.getPageNum() > 1) {
		// sizes = mainApp.getPageNum() + "";
		// }else{
		// }
		initMenu();
		lv = (ListView) findViewById(R.id.lv_lv);// 找到listview
		v_moreView = getLayoutInflater().inflate(R.layout.refresh_button, null);
		bt_load_more = (Button) v_moreView.findViewById(R.id.bt_load);
		pbBar = (ProgressBar) v_moreView.findViewById(R.id.pg);
		lv.addFooterView(v_moreView);
		bt_load_more.setOnClickListener(this);
		lv.setOnScrollListener(this);
		headView = Service2.this.getLayoutInflater().inflate(
				R.layout.layout_head_button, null, false);
		bt_app_head = (Button) headView.findViewById(R.id.bt_app_head);
		bt_web_head = (Button) headView.findViewById(R.id.bt_web_head);
		bt_level_head = (Button) headView.findViewById(R.id.bt_level_head);
		bt_all_head = (Button) headView.findViewById(R.id.bt_all_head);
		sp_head_deploy = (TextView) findViewById(R.id.sp_head_deploy);
		
		bt_all_head.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				type = bt_all_head.getText().toString();
				sp_head_deploy.setText(type);
				headWindow.closeWindow();
				refreshStart();
			}
		});

		bt_app_head.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				type = bt_app_head.getText().toString();
				sp_head_deploy.setText(type);
				headWindow.closeWindow();
				refreshStart();
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						p = search(type);
//						list = jxJSON(p);
//						Message msg = handler.obtainMessage();
//						handler.sendMessage(msg);
//					}
//				}).start();
			}
		});
		bt_web_head.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				type = bt_web_head.getText().toString();
				sp_head_deploy.setText(type);
				headWindow.closeWindow();
				refreshStart();
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						p = search(type);
//						list = jxJSON(p);
//						Message msg = handler.obtainMessage();
//						handler.sendMessage(msg);
//					}
//				}).start();
			}
		});
		bt_level_head.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				type = bt_level_head.getText().toString();
				sp_head_deploy.setText(type);
				headWindow.closeWindow();
				refreshStart();
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						p = search(type);
//						list = jxJSON(p);
//						Message msg = handler.obtainMessage();
//						handler.sendMessage(msg);
//					}
//				}).start();
			}
		});

		bt_home = (Button) findViewById(R.id.bt_my_space);
		sp_head_deploy.setOnClickListener(this);
		bt_functiino = (Button) findViewById(R.id.item_home);
		bt_home.setOnClickListener(this);
		bt_functiino.setOnClickListener(this);
	}
	

	private void initMenu() {
		menu = new SlidingMenu(Service2.this);
		menu.setMode(SlidingMenu.LEFT_OF);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		View view = LayoutInflater.from(this).inflate(R.layout.menu_item, null);
		menu.setMenu(view);
			bt1 = (Button) view.findViewById(R.id.item1_answer);
			bt2 = (Button) view.findViewById(R.id.item2_project);
			bt3 = (Button) view.findViewById(R.id.item3_trouble);
			bt4 = (Button) view.findViewById(R.id.item4_gets);
			bt5 = (Button) view.findViewById(R.id.item5_setting);
			bt6 = (Button) view.findViewById(R.id.item6_description);
			bt7 = (Button) view.findViewById(R.id.item7_theme);
			bt1.setOnClickListener(this);
			bt2.setOnClickListener(this);
			bt3.setOnClickListener(this);
			bt4.setOnClickListener(this);
			bt5.setOnClickListener(this);
			bt6.setOnClickListener(this);
			bt7.setOnClickListener(this);
	}
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (menu.isMenuShowing()) {
				menu.toggle();
			} else {
				doAlert();
				return false;
			}
			// if(menu.isMenuShowing() ||menu.isSecondaryMenuShowing()){
			// menu.showContent();
			// }else {
			// if ((System.currentTimeMillis() - mExitTime) > 2000) {
			// Toast.makeText(this, "�ٰ�һ���˳�",
			// Toast.LENGTH_SHORT).show();
			// mExitTime = System.currentTimeMillis();
			// } else {
			// // findApplication.setTab(tab);
			//
			// finish();
			// }
			// }
			// return true;
		}
		// ����MENU��ť����¼����������κβ���
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			menu.toggle();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	private void doAlert() {
		AlertDialog alert = new AlertDialog.Builder(Service2.this).create();
		alert.setTitle("你的访问记录如下");
		StringBuffer sb_count_activity = initStr();
		alert.setMessage(sb_count_activity.toString());
		alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(Service2.this, "取消",
								Toast.LENGTH_SHORT).show();
					}
				});
		alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
//						MainApplication application = MainApplication.getInstance();
//						MainApplication.setTag(R.id.message_radio);
						SharedPreferences sp = getSharedPreferences("tab", MODE_PRIVATE);
						Editor editor = sp.edit();
						editor.putInt("id", R.id.message_radio);
						editor.commit();
//						MainActivity.setId(R.id.message_radio);
						Service2.this.finish();
					}

				});
		alert.show();

	}

	private StringBuffer initStr() {
		StringBuffer sb = new StringBuffer();
		sb.append("项目列表信息您浏览了" + (getService2_count() - 1) + "  次");
		sb.append("\n");
		sb.append("您更改页数更改了" + (SelectActivity.getSelect_count() - 1) + "  次");
		sb.append("\n");

		sb.append("您想添加app项目的意图数为" + (DeployActivity.getDeploy_app_count())
				+ "  次");
		sb.append("\n");
		sb.append("您想添加web项目的意图数为" + (DeployActivity.getDeploy_web_count())
				+ "  次");
		sb.append("\n");
		sb.append("您想添加platform项目的意图数为"
				+ (DeployActivity.getDeploy_level_count()) + "  次");
		sb.append("\n");
		sb.append("您想添加项目的意图数为"
				+ (DeployActivity.getDeploy_level_count()
						+ DeployActivity.getDeploy_level_count() + DeployActivity
							.getDeploy_app_count()) + "  次");
		sb.append("\n");

		sb.append("您查看了 " + (Service2.getProj_d_count()) + " 个项目");
		sb.append("\n");

		sb.append("您关注了 " + (MyHomeActivity.getMy_home_count()) + " 次自己的项目");
		sb.append("\n");
		return sb;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_load:
			loadStart();
			// new MyTask().execute(projUrl + pageNo);
//			new Thread(new Runnable() {
//				public void run() {
//					index = String.valueOf(Integer.parseInt(index) + 1);
//					Log.i("run(", "线程开始");
//					String re = httpGet(index, sizes, email, type);
//					Message msg = handler.obtainMessage();
//					msg.obj = re;
//					handler.sendMessage(msg);
//				}
//			}).start();
//			adapter.notifyDataSetChanged();
			// pbBar.setVisibility(v.VISIBLE);
			// bt_load_more.setVisibility(v.GONE);
			//
			// hd.postDelayed(new Runnable() {
			//
			// @Override
			// public void run() {
			// // loadMoreData();
			// pbBar.setVisibility(View.VISIBLE);
			// bt_load_more.setVisibility(View.GONE);
			// bt_load_more.setVisibility(View.VISIBLE);
			// adapter.notifyDataSetChanged();
			// }
			// }, 2000);

			break;
		case R.id.item_home:
			super.showShortToast(bt_functiino.getText().toString());
			break;
		case R.id.bt_my_space:
			View rightView = Service2.this.getLayoutInflater().inflate(
					R.layout.layout_button, null, false);
			PopupWindow popRight = new PopupWindow(rightView, 180, 210);
			InitPopWindow rightWindow = new InitPopWindow(rightView,
					"rightView", popRight);
			rightWindow.getWindow();
			rightWindow.popwindow.showAsDropDown(v, 1, 5);
			break;
		case R.id.sp_head_deploy:
			popHead = new PopupWindow(headView, 180, 280);
			headWindow = new InitPopWindow(headView, "headView", popHead);
			headWindow.getWindow();
			// type = headWindow.getStr();
			headWindow.popwindow.showAsDropDown(v, -5, 5);

		case R.id.item1_answer:
			
			toastButtonText(bt1);
			// Intent renzhenIntent = new Intent(MainActivity.this,
			// OrderActivity.class);
			// renzhenIntent.putExtra("order",
			// bt1.getText().toString());
			// startActivity(renzhenIntent);
			// startActivity(new Intent(MainActivity.this,
			// OrderActivity.class));
			break;
		case R.id.item2_project:
			toastButtonText(bt2);
			// Intent renzhenIntent = new Intent(MainActivity.this,
			// OrderActivity.class);
			// renzhenIntent.putExtra("order",
			// bt1.getText().toString());
			// startActivity(renzhenIntent);
			break;
		case R.id.item3_trouble:
			menu.toggle();
			toastButtonText(bt3);
			startActivity(new Intent(Service2.this,
					AddTroubleActivity.class));

			break;
		case R.id.item4_gets:
			menu.toggle();
			toastButtonText(bt4);
			startActivity(new Intent(Service2.this, TouZiActivity.class));
			break;
		case R.id.item5_setting:
			menu.toggle();
			startActivity(new Intent(Service2.this,
					MainSettingActivity.class));
			// toastButtonText(bt5);
			break;
		case R.id.item6_description:
			toastButtonText(bt6);
			break;
		case R.id.item7_theme:
			toastButtonText(bt7);
			break;
		default:
			break;
		}
	}
	private void toastButtonText(Button bt) {
		String str_toast = bt.getText().toString();
		Toast.makeText(Service2.this, str_toast, Toast.LENGTH_SHORT).show();
	}
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
/*
 * 如果b为真，表示下拉刷新，
 * 所以在加载数据前应该先清空
 */
	private String httpGet(int index2, String sizes3, String pEmail, String gtype, int b) {
		String sss = null;
		String re = "0";
		String url = Constant.servletUrl;
		String isok = "0";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("index", index2+"")); // 当前页
			params.add(new BasicNameValuePair("sizes", sizes3)); // 页数
			params.add(new BasicNameValuePair("email", pEmail)); // 账号
			params.add(new BasicNameValuePair("type", gtype)); // 账号
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
			// Toast.makeText(Service2.this, isok, Toast.LENGTH_LONG).show();
			if ("0".equals(isok)) {
				JSONObject object = json.getJSONObject("data");
				JSONArray array = object.getJSONArray("projlist");
				if(b==1){
					list.clear();}
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = (JSONObject) array.opt(i);
					// HashMap<String, Object>map = new HashMap<String,
					// Object>();
					// map.put("proj_id", obj.getInt("project_id"));
					// map.put("proj_link",obj.getString("project_link"));
					// map.put("proj_type",obj.getString("project_type"));
					// map.put("proj_mind",obj.getString("project_mind"));
					// map.put("proj_theme",obj.getString("project_theme"));
					// map.put("proj_description",obj.getString("content_description"));
					// map.put("proj_time",obj.getString("project_time"));
					// map.put("proj_mail",obj.getString("person_email"));
					// datalist.add(map);

					int proj_id = obj.getInt("project_id");
					String proj_link = obj.getString("project_link");
					String proj_type = obj.getString("project_type");
					String proj_mind = obj.getString("project_mind");
					String proj_theme = obj.getString("project_theme");
					String proj_description = obj
							.getString("content_description");
					String proj_time = obj.getString("project_time");
					String proj_mail = obj.getString("person_email");
					// // String person_name = obj.getString("name");
					// // String person_resource =
					// obj.getString("project_resource");
					// // personBean = new PersonBean(proj_mail, person_name);
					// // pbean = new ProjectBean(proj_id, proj_link, proj_type,
					// // proj_mind, proj_theme, proj_description, proj_time,
					// // proj_mail, person_name);
					pbean = new ProjectBean(proj_id, proj_link, proj_type,
							proj_mind, proj_theme, proj_description, proj_time,
							proj_mail);
					list.add(pbean);
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

//	public List<ProjectBean> jxJSON(String result) {
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
//				pbean = new ProjectBean(proj_id, proj_link, proj_type,
//						proj_mind, proj_theme, proj_description, proj_time,
//						proj_mail);
//				list.add(pbean);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		/**
		 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=4（这里因为服务端有4页数据）时，加载更多数据。
		 */
		if (LastVisibleIndex == adapter.getCount()
				&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {

			if (LastVisibleIndex == num) {
				lv.removeFooterView(v_moreView);
				Toast.makeText(Service2.this, "全部数据加载完成", Toast.LENGTH_SHORT)
						.show();
			} else {
				loadStart();
				Log.i("msg", "index=" + index);
//				new Thread(new Runnable() {
//					public void run() {
//						Log.i("run(", "线程开始");
//						String re = httpGet(index, sizes, email,type);
//						Message msg = handler.obtainMessage();
//						msg.obj = re;
//						handler.sendMessage(msg);
//					}
//				}).start();
				// adapter.notifyDataSetChanged();
				Log.i("msg", "OnScrollStateChanged--lastVisibleIndex="
						+ LastVisibleIndex);
				Log.i("msg",
						"nScrollStateChanged--adapter.getCount="
								+ adapter.getCount());
				Log.i("msg", "num=" + num);
			}
			// Toast.makeText(ProjectsService.this,
			// "正在获取更多数据...",Toast.LENGTH_SHORT).show();
			// pageNo++;
			// new MyTask().execute(projUrl);
			// new MyTask().execute(projUrl + pageNo);

		}
	}
	class GoThread extends Thread{

		@Override
		public void run() {
			Log.i("run(", "线程开始");
			String re = httpGet(index, sizes, email,type,0);
			Message msg = handler.obtainMessage();
			msg.obj = re;
			handler.sendMessage(msg);
		}
		
	}
	class RefreshThread extends Thread{

		@Override
		public void run() {
			Log.i("run(", "线程开始");
			String re = httpGet(index, sizes, email,type,1);
			Message msg = hd.obtainMessage();
			msg.obj = re;
			hd.sendMessage(msg);
		}
		
	}
	
	/**
	 * 当：第一个可见的item（firstVisibleItem）+可见的item的个数（visibleItemCount）=所有的item总数的时候，
	 * is_divPage变为TRUE，这个时候才会加载数据。
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		LastVisibleIndex = firstVisibleItem + visibleItemCount - 1;
		Log.i("msg", "LastVisibleIndex=" + LastVisibleIndex);
	}

	@Override
	public void onRefresh() {
		refreshStart();
//		new Thread(new Runnable() {
//			public void run() {
//				Log.i("run(", "线程开始");
//				String re = httpGet(index, sizes, email,type);
//				Message msg = handler.obtainMessage();
//				msg.obj = re;
//				handler.sendMessage(msg);
//			}
//		}).start();
		// adapter.notifyDataSetChanged();
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// if (type.equals("")) {
		// p = MyHomeActivity.Serch(
		// LoginActivity.loginBean.getE_mail(), projUrl);
		// } else {
		// p = search(type);
		// }
		// list = jxJSON(p);
		// // refreshableView.setRefreshing(false);
		// Message msg = hd.obtainMessage();
		// hd.sendMessage(msg);
		// }
		// }).start();
	}

	private void refreshStart() {
		index = 1;
		sp = getSharedPreferences("page", MODE_PRIVATE);
		sizes = sp.getInt("pageNum", 10) + "";
		new RefreshThread().start();
	}

	public static int getService2_count() {
		return ++service2_count;
	}

	public static int getProj_d_count() {
		return ++proj_d_count;
	}
}
