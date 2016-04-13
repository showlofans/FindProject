package com.stufinish.findproject.service;

import android.app.Application;

public class FindApplication extends Application{
	private static int tab=0;

	
	public FindApplication() {
		super();
	}

	public int getTab() {
		return this.tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}
	
}
