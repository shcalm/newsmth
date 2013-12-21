package com.phgame.newsmth.ui;
import com.phgame.newsmth.R;
import com.phgame.newsmth.adapter.TopTenAdapter;
import com.phgame.newsmth.task.TopTenTask;
import com.phgame.newsmth.ui.widget.PullToRefreshListView;
import com.phgame.newsmth.ui.widget.PullToRefreshListView.OnRefreshListener;
import com.phgame.newsmth.util.SmthCrawler;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import com.phgame.newsmth.data.*;

public class MainView extends BaseActivity implements OnTouchListener {

	PullToRefreshListView toptenView;
	
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		this.setContentView(R.layout.mainview);
		
		toptenView = (PullToRefreshListView) this.findViewById(R.id.frame_listview_topten);
		
		TopTenAdapter adapter = new TopTenAdapter(MainView.this);
		toptenView.setAdapter(adapter);
		toptenView.setOnTouchListener(this);
		
		
		toptenView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String url = ((TieZiBean)toptenView.getAdapter().getItem(position)).getContentUrl();
				Intent i = new Intent();
				i.setAction("com.phgame.openpostdetail");
				i.setClass(MainView.this, PostDetailActivity.class);
				i.putExtra("url", url);
				MainView.this.startActivity(i);							
		
			}
			
			
			
		});
		toptenView.setOnRefreshListener(new OnRefreshListener(){

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				RefreshTopTen();
			}
			
		});
		
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				SmthCrawler cra = SmthCrawler.getInstance();
//				cra.getTopTenLists(null);
//			}
//			
//			
//		}).start();

		
		
		
	}
	
	private void RefreshTopTen(){
		
		
		TopTenTask toptentask = new TopTenTask(toptenView);
		toptentask.execute();
		
		//TopTenTask.execute();
		
	}
	
	protected void onResume(){
		super.onResume();
		RefreshTopTen();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
