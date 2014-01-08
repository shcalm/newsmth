package com.phgame.newsmth.ui.widget;

import com.phgame.newsmth.R;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;


public class PhProgressBarDialog extends ProgressDialog {

	public PhProgressBarDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.loading);
		
	}
	

}
