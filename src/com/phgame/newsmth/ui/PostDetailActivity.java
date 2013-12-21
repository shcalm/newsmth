package com.phgame.newsmth.ui;

import com.phgame.newsmth.R;
import com.phgame.newsmth.adapter.PostDetailAdapter;
import com.phgame.newsmth.adapter.PostDetailAdapter;
import com.phgame.newsmth.task.PostDetailTask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PostDetailActivity extends BaseActivity {

	
	ListView listview;
	TextView forum_view;
	TextView subject_view;
	
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
		String url = i.getStringExtra("url");
	
		forum_view = (TextView) this.findViewById(R.id.forum_name);
		subject_view = (TextView) this.findViewById(R.id.subject_name);
		
		listview = (ListView) this.findViewById(R.id.listview);
		PostDetailAdapter adapter = new PostDetailAdapter(PostDetailActivity.this);
		listview.setAdapter(adapter);
		
		
		PostDetailTask task = new PostDetailTask(PostDetailActivity.this);
		
		task.execute(url);
	}
	
}
