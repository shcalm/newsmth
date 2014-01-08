package com.phgame.newsmth.ui;


import android.os.Bundle;
import android.support.v4.app.*;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class BaseFragment extends Fragment {
	


	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
				
	}
	
	//
	protected void setFullScreen(){
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
	}

	private Window getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	//protected void 


}
