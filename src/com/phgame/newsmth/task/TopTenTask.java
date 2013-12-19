package com.phgame.newsmth.task;

import java.util.Vector;

import com.phgame.newsmth.util.SmthCrawler;
import com.phgame.newsmth.data.*;
import android.os.AsyncTask;

public class TopTenTask extends AsyncTask<String, Integer, String>{

	
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Vector<TieZiBean> tiezi = new Vector<TieZiBean>();
		SmthCrawler.getInstance().getTopTenLists(tiezi);
		
		return null;
	}
	
	
}
