package com.stufinish.findproject.activity;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.conn.ConnectTimeoutException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.stufinish.findproject.R;
import com.stufinish.findproject.dao.PicDao;
import com.stufinish.findproject.dao.RegisterDao;
import com.stufinish.findproject.model2.PersonBean;
import com.stufinish.findproject.service.ServiceRulesException;
import com.stufinish.findproject.service.UserService;
import com.stufinish.findproject.service.UserServiceImpl;
import com.stufinish.findproject.utils.Constant;
import com.stufinish.findproject.utils.HttpUploadUtil;
import com.stufinish.findproject.view.FadeImageView;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText et_password, et_username_;
	private Button bt_login, bt_register;
	private EditText et_register;
	private FadeImageView img_register;
	private LinearLayout linear_register;
	private ProgressDialog dialog;
	private String str_res;
	private RegisterDao registerDao;
	private ProgressDialog dialogllDialog = null;
	private Handler hd;
	private static String loginName, loginPassword;
	private static final int FLAG_LOGIN_SUCCESS = 356;
	private static final String MSG_LOGIN_ERROR = "登录出错";
	private static final String MSG_LOGIN_SUCCESS = "登录成功";
	public static final String MSG_LOGIN_FAILED = "登录名错误";
	public static final String MSG_LOGINPASS_FAILED = "昵称（密码）错误";
	public static final String MSG_SERVER_ERROR = "请求服务端出错";
	public static final String MSG_REQUEST_ERROR = "请求服务端超时";
	public static final String MSG_RESPONSE_ERROR = "服务端响应超时";
	public static PersonBean loginBean;//
	public static final int resultGetCode = 1;
	// 注册时resource从Edittext中读取，登录时resource从bean中读取
	private String str_pass, str_name;
	private UserService userService = new UserServiceImpl();
	private CheckBox cbrp, cbal;
	private Editor editor;
	private SharedPreferences sp_login, sp_register;
	private PicDao dao = new PicDao(LoginActivity.this);

	// private PersonBean loginBean;

	private void init() {
		sp_login = getSharedPreferences("loginconfig", MODE_PRIVATE);
		editor = sp_login.edit();
		et_username_ = (EditText) findViewById(R.id.et_User_Name);
		et_password = (EditText) findViewById(R.id.et_password);
		et_username_.setText(sp_login.getString("loginName", ""));
		et_password.setText(sp_login.getString("loginPassword", ""));
		et_username_.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// tv.setText(et_password.getText());
				// 判断输入的是URL还是EMAIL还是PHONENUMBER，并自动与系统连接
				Linkify.addLinks(et_username_, Linkify.WEB_URLS
						| Linkify.EMAIL_ADDRESSES | Linkify.PHONE_NUMBERS);
				return false;
			}
		});
		// String sp1 = sp_login.getString("loginName", "");
		// String sp2 = sp_login.getString("loginPassword", "");
		// et_username_.setText(sp1);
		// et_password.setText(sp2);
		bt_login = (Button) findViewById(R.id.btn_login);
		bt_register = (Button) findViewById(R.id.btn_register);
		if (!bt_register.isEnabled()) {
			bt_register.setEnabled(true);
		}
		if (!bt_login.isEnabled()) {
			bt_login.setEnabled(true);
		}
		img_register = (FadeImageView) findViewById(R.id.regist_img);
		// SharedPreferences sp_picPreferences = getSharedPreferences("picfile",
		// Activity.MODE_PRIVATE);
		// picturePath = sp_picPreferences.getString("pic", "");
		String tt = sp_login.getString("loginName", "");
		picturePath = dao.getPic(tt);
		if (picturePath != null) {
			img_register.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		}
		cbrp = (CheckBox) findViewById(R.id.cbrp);
		cbal = (CheckBox) findViewById(R.id.cbal);
		if (cbrp.isChecked()) {
			loginName = et_username_.getText().toString();
			loginPassword = et_password.getText().toString();
			editor.putString("loginName", loginName);
			editor.putString("loginPassword", loginPassword);
			editor.commit();
		} else {
			editor.clear();
		}
		if (cbal.isChecked()) {
			doLogin(sp_login.getString("loginName", ""),sp_login.getString("loginPassword", ""));
			editor.putBoolean("yorn", true);
		} else {
			editor.putBoolean("yorn", false);
		}

		bt_login.setOnClickListener(this);
		bt_register.setOnClickListener(this);
		img_register.setOnClickListener(this);
		et_register = (EditText) findViewById(R.id.et_register_resources);
		linear_register = (LinearLayout) findViewById(R.id.linear_register);
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_fragment);
		// 初始化控件
		init();
		hd = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				int Flag = msg.what;
				switch (Flag) {
				case 0:
					String errorMsg = (String) msg.getData().getSerializable(
							"ErrorMsg");
					showTip(errorMsg);
					Toast.makeText(LoginActivity.this, "没有输入绑定的用户名", 2000)
							.show();
					if (dialog != null) {
						dialog.dismiss();
					}
					if (dialogllDialog != null) {
						dialogllDialog.dismiss();
					}
					break;
				case FLAG_LOGIN_SUCCESS:
					Intent loginIntent = new Intent();
					loginIntent
							.setClass(LoginActivity.this, MainActivity.class);
					startActivity(loginIntent);
					loginBean.setE_mail(et_password.getText().toString());
					loginBean.setPerson_name(et_username_.getText().toString());
					loginBean.setPic_file(picturePath);
					dao.addPic(loginBean);
					if (cbrp.isChecked()) {
						editor.putString("loginName", et_username_.getText()
								.toString());
						editor.putString("loginPassword", et_password.getText()
								.toString());
						editor.commit();
					} else {
						editor.clear();
					}
					showTip(MSG_LOGIN_SUCCESS);
					if (dialog != null) {
						dialog.dismiss();
					}
					finish();
				case Constant.loginForegister:
					goRegister(msg);
					if (dialog != null) {
						dialog.dismiss();
					}
				default:

					break;
				}
			}
		};// //handler
	}

	private void goRegister(Message msg) {
		Bundle b;
		b = msg.getData();// 获取消息中的数据
		String msgStr = b.getString("msg");// 获取内容字符串
		if (msgStr != null) {
			if (msgStr.equals("exist")) {
				Toast.makeText(LoginActivity.this, "用户已存在", Toast.LENGTH_SHORT)
						.show();
				if (dialogllDialog != null) {
					dialogllDialog.dismiss();
				}
			} else if (msgStr.equals("success")) {
				if (dialogllDialog != null) {
					dialogllDialog.dismiss();
				}
				// loginBean = new PersonBean(str_name, str_res, str_pass);
				// registerDao.register(loginBean);// 往数据库里添加注册用户信息
				AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
						.create();
				dialog.setTitle("你选择的注册信息");
				dialog.setMessage(str_name + "绑定用户名" + "\n" + str_pass + "\n"
						+ "请记住现在登陆的用户名");

				dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "了解了",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog1,
									int which) {
								/**
								 * loading...
								 */
								Intent registerIntent = new Intent();
								registerIntent.setClass(LoginActivity.this,
										MainActivity.class);
								Editor editor1 = sp_login.edit();

								editor1.putString("loginName", str_name);
								editor1.putString("loginPassword", str_pass);
								editor1.putString("resource", str_res);
								editor1.commit();
								startActivity(registerIntent);
								Toast.makeText(LoginActivity.this, "注册成功!!",
										Toast.LENGTH_SHORT).show();
								finish();
							}
						});
				dialog.show();

			} else
				Toast.makeText(LoginActivity.this, msgStr, Toast.LENGTH_SHORT)
						.show();
		}
	}

	private void register(String name, String pass, String res) {
		if (dialogllDialog == null) {
			dialogllDialog = new ProgressDialog(LoginActivity.this);
		}
		dialogllDialog.setTitle("请等待");
		dialogllDialog.setMessage("验证中...");
		dialogllDialog.setCancelable(true);
		dialogllDialog.show();

		final Map<String, String> params = new HashMap<String, String>();

		// params.put("fromtype", name_edit);
		// 传递标题
		params.put("str_name", name);
		// 传递详情内容
		params.put("str_pass", pass);
		// 传递图片
		params.put("str_res", res);
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(
						Constant.addPersonJ, params);
				Bundle b = new Bundle();
				// 将内容字符串放进数据Bundle中
				b.putString("msg", msgStr);
				// 创建消息对象
				Message msg = hd.obtainMessage();
				// 设置数据Bundle到消息中
				msg.setData(b);
				// 设置消息标识
				msg.what = Constant.loginForegister;
				// 发送消息
				hd.sendMessage(msg);
			} // run
		}.start();// thread
	}

	private void showTip(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			loginName = et_username_.getText().toString();
			loginPassword = et_password.getText().toString();
			if (loginName.equals("") && loginPassword.equals("")) {
				Toast.makeText(LoginActivity.this, "请先输入用户名和注册邮箱！",
						Toast.LENGTH_SHORT).show();
			} else {

				/**
				 * loading...
				 */
//				Pattern pattern = Pattern
//						.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
//				Matcher matcher = pattern.matcher(loginPassword);
//				if (matcher.matches()){
					doLogin(loginName, loginPassword);
//				}
					
			}
			break;
		case R.id.btn_register:
			if (!bt_register.isEnabled()) {
				bt_register.setEnabled(true);
			}
			str_pass = et_password.getText().toString();
			str_name = et_username_.getText().toString();
			if (!(str_pass.equals(sp_login.getString("loginPassword", "")) && str_name
					.equals(sp_login.getString("loginName", "")))) {
				if (str_name.equals("") && str_pass.equals("")) {
					Toast.makeText(LoginActivity.this, "请先输入用户名和注册邮箱！",
							Toast.LENGTH_SHORT).show();
				}
				if (linear_register.getVisibility() == View.GONE) {
					linear_register.setVisibility(View.VISIBLE);
					et_register.setFocusable(true);
					Toast.makeText(LoginActivity.this,
							"请先完善资源地址信息\n或者点击头像进行登录", Toast.LENGTH_LONG).show();
					bt_login.setEnabled(false);
				}
				str_res = et_register.getText().toString();
				if (str_res.length() > 0) {
					Pattern pattern = Pattern
							.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
					Matcher matcher = pattern.matcher(loginPassword);
					if (matcher.matches()){
					register(str_name, str_pass, str_res);
					}else{
						Toast.makeText(LoginActivity.this,
								"输入邮箱格式不正确！", Toast.LENGTH_LONG).show();
					}
					
				}
			} else {
				bt_register.setEnabled(false);
				Toast.makeText(LoginActivity.this, "请直接登录", Toast.LENGTH_LONG)
						.show();
			}
			break;
		case R.id.regist_img:
			// Intent i = new Intent(
			// Intent.ACTION_PICK,
			// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			// startActivityForResult(i, resultGetCode);
			if (linear_register.getVisibility() == View.GONE)
				linear_register.setVisibility(View.VISIBLE);
			else if (et_register.getText().toString().length() > 0) {
				bt_login.setEnabled(true);
				AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
						.create();
				dialog.setTitle("提示信息");
				dialog.setMessage("您要忽略已经填写的资源地址信息吗？");
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(LoginActivity.this, "取消",
										Toast.LENGTH_SHORT).show();
								// 用sharedpreference把resource存起来
							}
						});
				dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								linear_register.setVisibility(View.GONE);
								// et_register.setText("");
								Toast.makeText(LoginActivity.this,
										"您已忽略了填写的资源地址信息" + "\n",
										Toast.LENGTH_SHORT).show();
							}

						});
				dialog.show();
			} else {
				linear_register.setVisibility(View.GONE);
				bt_login.setEnabled(true);
			}

			break;
		default:
			break;
		}
	}

	private void doLogin(final String name, final String pass) {
		if (dialog == null) {
			dialog = new ProgressDialog(LoginActivity.this);
		}
		dialog.setTitle("请等待");
		dialog.setMessage("登录中...");
		dialog.setCancelable(false);
		dialog.show();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					userService.userLogin(name, pass);
					hd.sendEmptyMessage(FLAG_LOGIN_SUCCESS);
				} catch (ConnectTimeoutException e) {
					e.printStackTrace();
					Message msg = hd.obtainMessage();
					Bundle data = new Bundle();
					data.putSerializable("ErrorMsg", MSG_REQUEST_ERROR);
					msg.setData(data);
					hd.sendMessage(msg);
				} catch (SocketTimeoutException e) {
					e.printStackTrace();
					Message msg = hd.obtainMessage();
					Bundle data = new Bundle();
					data.putSerializable("ErrorMsg", MSG_RESPONSE_ERROR);
					msg.setData(data);
					hd.sendMessage(msg);
				} catch (ServiceRulesException se) {
					se.printStackTrace();
					Message msg = hd.obtainMessage();
					Bundle data = new Bundle();
					data.putSerializable("ErrorMsg", se.getMessage());
					msg.setData(data);
					hd.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = hd.obtainMessage();
					Bundle data = new Bundle();
					data.putSerializable("ErrorMsg", MSG_LOGIN_ERROR);
					msg.setData(data);
					hd.sendMessage(msg);
				}
			}
		});
		thread.start();

	}

	private String picturePath;
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// // TODO Auto-generated method stub
	// super.onActivityResult(requestCode, resultCode, data);
	// if (requestCode == resultGetCode && resultCode == RESULT_OK && null !=
	// data){
	// Uri selectedImage = data.getData();
	// String[] filePathColumn = { MediaStore.Images.Media.DATA };
	// Cursor cursor = getContentResolver().query(selectedImage,
	// filePathColumn, null, null, null);
	// cursor.moveToFirst();
	// int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	// picturePath = cursor.getString(columnIndex);
	// cursor.close();
	// img_register.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	// }
	// }

	// private static class mHandler extends Handler {
	//
	// private final WeakReference<Activity> mActivity;
	//
	// public mHandler(LoginActivity activity) {
	//
	// this.mActivity = new WeakReference<Activity>(activity);
	// }
	//
	// @Override
	// public void handleMessage(Message msg) {
	// if (dialog != null) {
	// dialog.dismiss();
	// }
	// LoginActivity loactivity = (LoginActivity) mActivity.get();
	// int Flag = msg.what;
	// switch (Flag) {
	// case 0:
	// String errorMsg = (String) msg.getData().getSerializable(
	// "ErrorMsg");
	// ((LoginActivity) mActivity.get()).showTip(errorMsg);
	// Toast.makeText(loactivity, "没有输入绑定的用户名", 2000).show();
	//
	// break;
	// case FLAG_LOGIN_SUCCESS:
	// Intent loginIntent = new Intent();
	// loginIntent.setClass(loactivity, MainActivity.class);
	// loactivity.startActivity(loginIntent);
	// loactivity.showTip(MSG_LOGIN_SUCCESS);
	// loactivity.finish();
	//
	// default:
	//
	// break;
	// }
	//
	// }
	//
	// }

	// private mHandler handler = new mHandler(this);
}
