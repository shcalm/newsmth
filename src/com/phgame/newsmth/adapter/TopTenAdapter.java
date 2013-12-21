package com.phgame.newsmth.adapter;

import java.util.Vector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phgame.newsmth.R;
import com.phgame.newsmth.data.*;

public class TopTenAdapter extends BaseAdapter {

	private Vector<TieZiBean> tiezis;
	private Context ctx;

	public TopTenAdapter(Context ctx) {
		this.ctx = ctx;

	}

	public void setData(Vector<TieZiBean> tiezis) {

		this.tiezis = tiezis;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(tiezis != null)
			return tiezis.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		
		return tiezis.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//
			//view = inflater.inflate(R.layout.layout_content, parent);
			//
			view = inflater.inflate(R.layout.layout_content, null);
			TextView title_view = (TextView) view.findViewById(R.id.title);
			TextView reply_view = (TextView) view.findViewById(R.id.reply_count);

			title_view.setText(tiezis.get(position).getTitle());
			reply_view.setText(tiezis.get(position).getReplyCount());
		}else{
			// to do maybe need to refresh
			view = convertView;
			TextView title_view = (TextView) view.findViewById(R.id.title);
			TextView reply_view = (TextView) view.findViewById(R.id.reply_count);

			title_view.setText(tiezis.get(position).getTitle());
			reply_view.setText(tiezis.get(position).getReplyCount());
		}
		
		return view;
	}

}
