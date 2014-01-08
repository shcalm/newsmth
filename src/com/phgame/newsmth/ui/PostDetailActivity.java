package com.phgame.newsmth.ui;

import com.example.android.bitmapfun.util.OnCompleteListener;
import com.phgame.newsmth.R;
import com.phgame.newsmth.adapter.PostDetailAdapter;
import com.phgame.newsmth.adapter.PostDetailAdapter;
import com.phgame.newsmth.task.PostDetailTask;
import com.phgame.newsmth.ui.widget.PhProgressBarDialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

public class PostDetailActivity extends BaseActivity {

	
	ListView listview;
	TextView forum_view;
	TextView subject_view;
	String url;
	PostDetailTask task = null ;
	ProgressDialog bar = null;
	public ListView getListView(){
		return listview;
	}
	
	public void updateUI(String forunname,String subjectname){
		forum_view.setText(forunname);
		subject_view.setText(subjectname);
		
	}
		
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		this.setContentView(R.layout.postdetailactivity);
		this.setFullScreen();
		
		Intent i = this.getIntent();
		url = i.getStringExtra("url");
	
		forum_view = (TextView) this.findViewById(R.id.forum_name);
		subject_view = (TextView) this.findViewById(R.id.subject_name);
		
		listview = (ListView) this.findViewById(R.id.listview);
		PostDetailAdapter adapter = new PostDetailAdapter(PostDetailActivity.this);
		listview.setAdapter(adapter);
		
		task = new PostDetailTask(PostDetailActivity.this);
		task.setOnCompleteListener(new OnCompleteListener(){
		
			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				bar.dismiss();
			}
			
		});
		
		listview.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
				System.out.println("first" + firstVisibleItem + "visiable count" + visibleItemCount
						+"totoalcount" + totalItemCount);
					if(firstVisibleItem + visibleItemCount == totalItemCount){//to bottom
						System.out.println("at bottom");
						//task.execute(url+"?/");
					}
			}
			
		});
				
		
		task.execute(url);
		
		
	}
    @Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
    	bar = new PhProgressBarDialog(this);
    	bar.setIndeterminate(true);
    	bar.setCancelable(false);
    	bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	//bar.setFeatureDrawable(featureId, drawable)
    	
    	
		return bar;
	}
	
}
