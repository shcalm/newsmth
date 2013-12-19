package com.phgame.newsmth.ui;
import com.phgame.newsmth.R;
import com.phgame.newsmth.adapter.TopTenAdapter;
import com.phgame.newsmth.ui.widget.PullToRefreshListView;
import com.phgame.newsmth.util.SmthCrawler;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainView extends BaseActivity implements OnTouchListener {

	PullToRefreshListView toptenView;
	
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		this.setContentView(R.layout.mainview);
		
		toptenView = (PullToRefreshListView) this.findViewById(R.id.frame_listview_topten);
		
		TopTenAdapter adapter = new TopTenAdapter();
		toptenView.setAdapter(adapter);
		toptenView.setOnTouchListener(this);
		
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SmthCrawler cra = SmthCrawler.getInstance();
				cra.getTopTenLists(null);
			}
			
			
		}).start();

		
		
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
