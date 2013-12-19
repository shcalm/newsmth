package com.phgame.newsmth.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity {

	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
				
	}
	
	//
	protected void setFullScreen(){
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	
	protected void setNoTitle(){
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}
	
	
	
	
	//protected void 
}
