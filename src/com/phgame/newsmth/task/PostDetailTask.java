package com.phgame.newsmth.task;

import java.util.Vector;

import com.example.android.bitmapfun.util.OnCompleteListener;
import com.phgame.newsmth.util.SmthCrawler;
import com.phgame.newsmth.data.*;

import android.content.Context;
import android.os.AsyncTask;

import android.widget.ListView;
import com.phgame.newsmth.adapter.*;
import com.phgame.newsmth.ui.PostDetailActivity;

public class PostDetailTask extends AsyncTask<String, Integer, String>{

	
	private final static String BASE_URL = "http://m.newsmth.net";
	//ListView view;
	PostDetailAdapter adapter;
	ListView view;
	Context ctx;
	StringBuffer forumname= new StringBuffer("");
	StringBuffer subjectname = new StringBuffer("");
	private OnCompleteListener listener = null;
	
	public PostDetailTask(Context ctx){
		//this.adapter = adapter;
		this.ctx = ctx;
		this.view = ((PostDetailActivity)ctx).getListView();
		
		this.adapter = (PostDetailAdapter) view.getAdapter();
	}
	
	public void setOnCompleteListener(OnCompleteListener listener){
		this.listener = listener;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String url = BASE_URL + params[0];
		Vector<PostDetailBean> tiezi = new Vector<PostDetailBean>();
		
		//forumname.append("aa");
		
		SmthCrawler.getInstance().getUrlLists(url,tiezi,forumname,subjectname);
				
		adapter.setData(tiezi);
		//adapter.setData(tiezi);
		
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		adapter.notifyDataSetChanged();
		
		((PostDetailActivity) ctx).updateUI(forumname.toString(),subjectname.toString());
		//view.onRefreshComplete();
		if(listener != null){
			listener.onComplete();
		}
		super.onPostExecute(result);
	}
	
	
	
	
}
