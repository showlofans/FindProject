package com.stufinish.findproject.activity;

import com.stufinish.findproject.R;
import com.stufinish.findproject.model2.ProjectBean;
import com.stufinish.findproject.utils.InitPopWindow;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 功能点：展示listView中的项目 主界面：project_detail
 * 
 * @author jefferson
 * 
 */
public class ProjectDetailActivity extends BaseActivity implements OnClickListener {

	public static final String TAG = "ProjectDetailActivity";// 定义当前标签
	private TextView tv_title, tv_content; // 控件：项目标题和内容
	private ImageView img_back; // 控件:返回按钮
	private ProjectBean proj; // 实体对象:项目
	// bt_resource bt_link_ bt_mind bt_trouble bt_description bt_theme
	private Button bt_renzhen, bt_resource, bt_link_;
	private Button bt_mind, bt_trouble, bt_description, bt_theme;
	private PopupWindow popwindow; // 工具类对象：PopupWindow（下拉列表）
	private ImageButton imgbt_function; // 控件：下拉列表唤醒按钮
	private String proj_info; // Toast的string信息
	private OnClickListener functionListener,backListener;
	private String str;
	private View customView;
	private InitPopWindow initpop;
	private PopupWindow popDetail;
	private TextView tv_link,tv_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.project_detail);

		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			proj = (ProjectBean) bundle.get("project");
			str = bundle.getString("my_change");
		}
		backListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProjectDetailActivity.this.finish();
			}
		};
		functionListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				// else if(my_change==1){
				// initmPopuWindowView(false);
				// popwindow.showAsDropDown(v, 1, 5);
				// }
				popDetail = new PopupWindow(customView, 250, 300);
				    initpop = new InitPopWindow(customView, "customView", popDetail);
					initpop.getWindow();
					initpop.popwindow.showAsDropDown(v, 1, 5);
//					initmPopuWindowView(true);
			}
		};
		initView();

	}

	/**
	 * 功能点：初始化控件 为控件绑定监听器
	 */
	
	private void initView() {
		customView = getLayoutInflater().inflate(R.layout.layout_project,
				null, false);
		bt_resource = (Button) customView.findViewById(R.id.bt_add_resource);
		bt_link_ = (Button) customView.findViewById(R.id.bt_add_link);
		bt_trouble = (Button) customView.findViewById(R.id.bt_add_trouble);
		bt_renzhen = (Button) customView.findViewById(R.id.bt_add_renzhen);
		bt_mind = (Button) customView.findViewById(R.id.bt_update_mind);
		bt_description = (Button) customView
				.findViewById(R.id.bt_add_description);
		bt_theme = (Button) customView.findViewById(R.id.bt_update_theme);
		if(str.equals("service")){
			bt_trouble.setText(getResources().getString(R.string.deal_trouble));
		}
		bt_description.setOnClickListener(this);
		bt_resource.setOnClickListener(this);
		bt_link_.setOnClickListener(this);
		bt_trouble.setOnClickListener(this);
		bt_renzhen.setOnClickListener(this);
		bt_mind.setOnClickListener(this);
		bt_theme.setOnClickListener(this);
		
		tv_title = (TextView) findViewById(R.id.title_project_detail);
		tv_link = (TextView)findViewById(R.id.detail_project_link);
		tv_time = (TextView)findViewById(R.id.detail_project_time);
		tv_title.setText(proj.getProject_theme().toString());
		img_back = (ImageView) findViewById(R.id.back_detail_project);
		img_back.setOnClickListener(backListener);
		imgbt_function = (ImageButton) findViewById(R.id.imgbt_menu_function);
		imgbt_function.setOnClickListener(functionListener);
		tv_content = (TextView) findViewById(R.id.detail_project_content);
		tv_content.setText(proj.getContent_description().toString() + "\n");
		tv_link.setText(proj.getProject_link().toString() + "\n");
		tv_time.setText( proj.getProject_time().toString());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_add_link:
			showToast(bt_link_);
			break;
		case R.id.bt_update_mind:
			showToast(bt_mind);
			break;
		case R.id.bt_add_description:
			showToast(bt_description);
			break;
		case R.id.bt_update_theme:
			showToast(bt_theme);
			break;
		case R.id.bt_add_resource:
			showToast(bt_resource);
			break;
		case R.id.bt_add_renzhen:
			showToast(bt_renzhen);
			break;
		// if(popwindow != null && popwindow.isShowing()){
		// popwindow.dismiss();
		// popwindow = null;
		// }
		// Intent renzhenIntent = new
		// Intent(ProjectDetailActivity.this,AddTroubleActivity.class);
		// // if(proj != null){
		// // if (my_change==1) {
		// // renzhenIntent.putExtra("edit", 1);
		// // }else{
		// // renzhenIntent.putExtra("edit", 0);
		// // }
		// // renzhenIntent.putExtra("proj_id", proj.getProject_id());
		// // }
		// startActivity(renzhenIntent);
		case R.id.bt_add_trouble:
//			showToast(bt_trouble);
			Intent troubleIntent = new Intent(ProjectDetailActivity.this, AddTroubleActivity.class);
			troubleIntent.putExtra("trouble_proj", proj);
			startActivity(troubleIntent);
			AddTroubleActivity.getAdd_trouble_count();
			initpop.closeWindow();
			break;
		// Intent troubleIntent = new Intent(ProjectDetailActivity.this,
		// AddTroubleActivity.class);
		// // troubleIntent.putExtra("proj_id", proj.getProject_id());
		// startActivity(troubleIntent);

		}
	}

//	/**
//	 * 功能点1：开启下拉列表
//	 * <p>
//	 * 由点击事件引起来的（ImageButton）
//	 * <p>
//	 * 功能点2: 初始化下拉的控件，并为这些控件添加监听
//	 * 
//	 * @param Boolean
//	 *            my_change
//	 */
//	private void initmPopuWindowView(Boolean my_change) {
//		View customView = getLayoutInflater().inflate(R.layout.layout_project,
//				null, false);
//		popwindow = new PopupWindow(customView, 250, 280);
//		popwindow.setBackgroundDrawable(new BitmapDrawable());
//		popwindow.setOutsideTouchable(true);
//		popwindow.setFocusable(true);
//		customView.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				if (popwindow != null && popwindow.isShowing()) {
//					popwindow.dismiss();
//					popwindow = null;
//				}
//				return false;
//			}
//		});
//		
//		// if(my_change){//projectresource
//		// bt_resource.setText("项目资源");
//		// bt_link_.setText("项目链接");
//		// bt_trouble.setText("项目问题");
//		// bt_renzhen.setText("认证信息");
//		// bt_mind.setText("项目发布意图");
//		// bt_description.setText("其他项目描述");
//		// bt_theme.setText("描述的主题");
//		// }
//
//	}


	/**
	 * 功能点：让popwindow消失
	 */
	private void dismissWindow() {
		if (popwindow != null && popwindow.isShowing()) {
			popwindow.dismiss();
			return;
		}
	}

	/**
	 * 功能点：Toast方法
	 * 
	 * @param str
	 */
	private void showToast(Button btn) {
		proj_info = btn.getText().toString();
		dismissWindow();
		Toast.makeText(ProjectDetailActivity.this, proj_info, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * 注释内容：用StringBuffer来装载项目的所有描述
	 */
	// Bundle bundle = ProjectDetailActivity.this.getIntent().getExtras();
	// if (bundle != null) {
	// proj = (ProjectBean) bundle.get("project");
	// }
	// if(proj != null){
	// StringBuffer sb = new StringBuffer();
	// sb.append(proj.getContent_description());
	// sb.append("/n");
	// sb.append(proj.getContent().getProject_trouble());
	// sb.append("/n");
	// sb.append(proj.getContent().getProject_resource());
	// sb.append("/n");
	// sb.append(proj.getContent().getProject_link());
	// sb.append("/n");
	// sb.append(proj.getContent().getProject_mind());
	// sb.append("/n");
	// sb.append(proj.getProject_renzhen());
	// sb.append("/n");
	// sb.append("发布时间："+proj.getProject_time());
	//
	// tv_title.setText(proj.getProject_theme());
	// tv_content.setText(sb.toString());
	//
	// }
	// }

}
