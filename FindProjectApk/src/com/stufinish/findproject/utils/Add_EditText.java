package com.stufinish.findproject.utils;

import java.util.ArrayList;

import com.stufinish.findproject.activity.DeployDetail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Add_EditText {
	private Button btn;
	private int count_type;
	private int count_text; // 编辑框内容的长度
	private EditText et;
	private LinearLayout linear_trouble;
	private Context context;
	private int flag_add = 0;	//flag_add=0标示已经添加到列表当中，直接读取list即可//初始化flag_add

	public ArrayList<String> list_edit1;
	public ArrayList<String> getList_edit1() {
		return list_edit1 ;
	}
	public void init_list(){
		list_edit1 = new ArrayList<String>();
	}
	private int edit_length = 4;
	private int flag = 1;
	private int change_trouble = 0;
	
	private int str_toast_msg;

	public Add_EditText(Context context, LinearLayout layout, Button button) {
		super();
		this.btn = button;
		this.linear_trouble = layout;
		this.context = context;
	}

	public int add_trouble() {
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		if (flag == 1) {
			EditText et_add_trouble = new EditText(context);
			count_type++;
			et = et_add_trouble;
			et_add_trouble.setText(count_type + ".");
			getfocuse(et_add_trouble);
			linear_trouble.addView(et_add_trouble, 2, params);
			flag = 0;
		}
		count_text = et.getText().toString().length();
		if (count_text > 2) {
			EditText et_add_trouble = new EditText(context);
			et = et_add_trouble;
			count_type++;// 1
			et.setText(count_type + ".");
			getfocuse(et_add_trouble);
			linear_trouble.addView(et_add_trouble, 2, params);
		} else {
			Toast.makeText(context, "已添加了编辑框", 200).show();
		}
		// t_count_trouble=3
		if (btn.getVisibility() == View.GONE) {
			btn.setVisibility(View.VISIBLE);
		}
		return count_type;
	}

	private void getfocuse(EditText et) {
		et.setSingleLine(true);
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		et.requestFocus();
		et.findFocus();
	}

	public int del_trouble() {
		if (linear_trouble.getChildCount() == 3) {
			btn.setVisibility(View.GONE);
		}
		if (btn.getVisibility() != View.GONE)
			et = (EditText) linear_trouble.getChildAt(2);
		count_text = et.getText().toString().length();// 获得要删除的编辑框内的
		if (count_text > 2) {// 编辑框内有内容
			newdialog();
		} else {
			flag = 1;// 不用获取编辑框的值
			linear_trouble.removeViewAt(2);
			dele_from_list();
			flag = 1;
			count_type--;// 对应上面flag==1时的count_type++
		}
		if (linear_trouble.getChildCount() == 3) {
			btn.setVisibility(View.GONE);
		}
		return count_type;
	}

	private void newdialog() {
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		EditText et_alerText;
		et_alerText = (EditText) linear_trouble.getChildAt(2);
		final String leln = et_alerText.getText().toString();
		dialog.setTitle("确定删除吗？");
		dialog.setMessage(leln.substring(2));
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, "取消", Toast.LENGTH_SHORT)
								.show();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						linear_trouble.removeViewAt(2);
						dele_from_list();
						flag = 1;
						count_type--;
						if (linear_trouble.getChildCount() == 3) {
							btn.setVisibility(View.GONE);
						}
						Toast.makeText(context, "您已删除"+"\n"+leln.substring(2), Toast.LENGTH_SHORT)
								.show();
					}

				});
		dialog.show();
	}
	public ArrayList<String> edit_to_list() {// StringBuffer sb1,StringBuffer
		// sb2,StringBuffer sb3
		// 保证某一条记录只发表一次(加第二个编辑框的内容)
		EditText edt1;
		String str;// 第一个编辑框的值
		if (linear_trouble.getChildCount() > 3) {// 判断是否有编辑框
		// 获得第一个编辑框的值
			edt1 = (EditText) linear_trouble.getChildAt(2);
			int child_count = linear_trouble.getChildCount();
			str = edt1.getText().toString();
			// 预备动作
			if (str.length() < 3) {
				linear_trouble.removeViewAt(1);// 编辑框为空
				if (linear_trouble.getChildAt(child_count - 2).getVisibility() == View.VISIBLE) {
					linear_trouble.getChildAt(child_count - 2).setVisibility(
							View.GONE);
				}
			} else {
				edit_length = child_count - 1 ;
			}

			if (linear_trouble.getChildCount() > 3) {
				// 解决没有编辑框的问题
				// 有内容的编辑框
				// 通过edit_length来标记是否要添加到集合当中（否则不添加额外的数到集合当中）
				// 初始化edit_length为4
				int length_trouble = linear_trouble.getChildCount();// 记录现在有的控件个数

				change_trouble = length_trouble - edit_length;
				// 直接读取列表内容
				if (flag_add == 1) {
					list_edit1.clear();
					int len_edit = length_trouble - 2;
					for (int i = 1; i < len_edit; i++) {// 通过for循环找到linear所有的编辑框，并将编辑框内的值加入到集合当中
						edt1 = (EditText) linear_trouble.getChildAt(i + 1);
						String str_e1 = edt1.getText().toString();
						list_edit1.add(str_e1);
					}
					flag_add = 0;
				}

				if (change_trouble > 0) {
					list_edit1.clear();
					int len_edit = length_trouble - 2;
					for (int i = 1; i < len_edit; i++) {// 通过for循环找到linear所有的编辑框，并将编辑框内的值加入到集合当中
						edt1 = (EditText) linear_trouble.getChildAt(i + 1);
						String str_e1 = edt1.getText().toString();
						list_edit1.add(str_e1);
					}
					flag_add = 0;
				} 
				edit_length = length_trouble;// 记录原先的控件数量
				return list_edit1;
			}
		} else {
			str_toast_msg = 1 ;
		}
		return null;
	}

	public void dele_from_list() {
		if (linear_trouble.getChildCount() > 3) {
			int len_edit = linear_trouble.getChildCount() - 2;
			if (flag_add == 0) {// 如果没有删除过，就在删除这里把数加到数组里
				list_edit1.clear();
			}
			for (int i = 1; i < len_edit; i++) {// 通过for循环找到linear所有的编辑框，并将编辑框内的值加入到集合当中
				EditText edt1 = (EditText) linear_trouble.getChildAt(i + 1);
				String str_e1 = edt1.getText().toString();
				list_edit1.add(str_e1);
			}
			flag_add = 0;
			edit_length--;
		}
	}
	
	//得到编辑框的个数
		public void setCount_type(boolean som) {
			int count_type = getCount_type();// 调用之前先初始化编辑框数量
			if (som) {
				count_type = add_trouble();
			} else {
				count_type = del_trouble();
			}
			this.count_type = count_type;
		}
	public int getEdit_length() {
		return edit_length;
	}

	public void setEdit_length(int edit_length) {
		this.edit_length = edit_length;//
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getCount_type() {
		return count_type;
	}
	public int getFlag_add() {
		return flag_add;
	}
	public void setFlag_add(int flag_add) {
		this.flag_add = flag_add;
	}
	
	public int getStr_toast_msg() {
		return str_toast_msg;
	}

}
