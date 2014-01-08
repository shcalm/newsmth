package com.phgame.newsmth.task;

import java.util.Vector;

import com.phgame.newsmth.ui.widget.PullToRefreshListView;
import com.phgame.newsmth.util.SmthCrawler;
import com.phgame.newsmth.data.*;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import com.phgame.newsmth.adapter.*;
public class LoginTask extends AsyncTask<String, Integer, Integer>{

	
	Handler handler ;
	public LoginTask(Handler handler){
		//this.adapter = adapter;
		this.handler = handler;
	}
	
	@Override
	protected Integer doInBackground(String... params) {
		String username = params[0];
		String password = params[1];
		int ret  = SmthCrawler.getInstance().login(username, password);

		
		return ret;
	}

	@Override
	protected void onPostExecute(Integer result) {
		Message msg = Message.obtain();
		msg.arg1 = result;
		
		handler.sendMessage(msg);
		super.onPostExecute(result);
	}
	
	
	
	
}
