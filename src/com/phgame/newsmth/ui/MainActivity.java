package com.phgame.newsmth.ui;

import com.phgame.newsmth.R;
import com.phgame.newsmth.R.layout;
import com.phgame.newsmth.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.setNoTitle();
//		this.setFullScreen();
		//setContentView(R.layout.activity_main);
		
		View view = View.inflate(this, R.layout.activity_main, null);
		setContentView(view);
		AlphaAnimation aa = new AlphaAnimation(0.1f,1.0f);
		view.setAnimation(aa);
		aa.setDuration(2000);
		aa.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				DirectToNextActivity();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		view.startAnimation(aa);
		
		
		
	}
	private void DirectToNextActivity(){
		MainActivity.this.startActivity(new Intent(this,MainView.class));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
