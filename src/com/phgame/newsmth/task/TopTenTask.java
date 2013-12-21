package com.phgame.newsmth.task;

import java.util.Vector;

import com.phgame.newsmth.ui.widget.PullToRefreshListView;
import com.phgame.newsmth.util.SmthCrawler;
import com.phgame.newsmth.data.*;
import android.os.AsyncTask;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import com.phgame.newsmth.adapter.*;
public class TopTenTask extends AsyncTask<String, Integer, String>{

	
	//ListView view;
	TopTenAdapter adapter;
	PullToRefreshListView view;
	public TopTenTask(ListView view){
		//this.adapter = adapter;
		this.view = (PullToRefreshListView) view;
		
		this.adapter = (TopTenAdapter) ((HeaderViewListAdapter)view.getAdapter()).getWrappedAdapter();
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Vector<TieZiBean> tiezi = new Vector<TieZiBean>();
		SmthCrawler.getInstance().getTopTenLists(tiezi);
				
		adapter.setData(tiezi);
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		adapter.notifyDataSetChanged();
		view.onRefreshComplete();
		super.onPostExecute(result);
	}
	
	
	
	
}
