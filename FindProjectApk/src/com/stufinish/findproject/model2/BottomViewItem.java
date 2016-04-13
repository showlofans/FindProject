package com.stufinish.findproject.model2;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stufinish.findproject.R;

public class BottomViewItem {

	public static BottomViewItem instance;

	public static BottomViewItem getInstance() {
		if (instance == null) {
			instance = new BottomViewItem();
		}
		return instance;
	}

	public int viewNum = 3;
	public ImageView[] images = new ImageView[viewNum];
	public TextView[] texts = new TextView[viewNum];
	public TextView[] toptex=new TextView[viewNum-1];
	public ImageButton[]topimgButtons=new ImageButton[viewNum-1];
	
	public LinearLayout[] linears = new LinearLayout[viewNum];
	/*public int[] top_visia=new int[]{0,0,0,0};
	public int[] top_imageb=new int[]{R.id.top_gonext_ibtn,R.id.top_goback_ibtn};
	public int[] toptex_id=new int[]{R.id.top_goback_text,R.id.top_gonext_text};
	public int[] topbut=new int[]{R.id.top_goback_text,R.id.top_gonext_text};*/
	
	public int[] images_id = new int[] {R.id.message_image, R.id.contacts_image,R.id.news_image};
	public int[] texts_id = new int[] { R.id.message_text, R.id.contacts_text, R.id.news_text};
	public int[] linears_id = new int[] { R.id.message_layout, R.id.contacts_layout, R.id.news_layout};
	public int[] images_selected = new int[] { R.drawable.message_selected, R.drawable.contacts_selected, R.drawable.news_selected };
	public int[] images_unselected = new int[] { R.drawable.message_unselected, R.drawable.contacts_unselected, R.drawable.news_unselected};
	public int[] layouts_id = new int[] { R.layout.message_view, R.layout.contacts_view, R.layout.hmy_home };

}
