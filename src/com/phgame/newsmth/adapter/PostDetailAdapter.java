package com.phgame.newsmth.adapter;

import java.util.Vector;

import com.phgame.newsmth.R;
import com.phgame.newsmth.data.PostDetailBean;
import com.phgame.newsmth.data.TieZiBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PostDetailAdapter extends BaseAdapter {

	private Vector<PostDetailBean> posts;
	private Context ctx;

	public PostDetailAdapter(Context ctx) {
		this.ctx = ctx;

	}

	public void setData(Vector<PostDetailBean> tiezis) {

		this.posts = tiezis;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(posts != null)
			return posts.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		
		return posts.get(position);
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
			view = inflater.inflate(R.layout.postdetail_listview, null);
			TextView level_view = (TextView) view.findViewById(R.id.level);
			TextView author_view = (TextView) view.findViewById(R.id.author);
			TextView time_view = (TextView) view.findViewById(R.id.time);
			TextView content_view = (TextView) view.findViewById(R.id.content);
			

			level_view.setText(posts.get(position).level);
			author_view.setText(posts.get(position).auther);
			time_view.setText(posts.get(position).time);
			content_view.setText(posts.get(position).detail);
			
			
		}else{
			// to do maybe need to refresh
			view = convertView;
			TextView level_view = (TextView) view.findViewById(R.id.level);
			TextView author_view = (TextView) view.findViewById(R.id.author);
			TextView time_view = (TextView) view.findViewById(R.id.time);
			TextView content_view = (TextView) view.findViewById(R.id.content);

			level_view.setText(posts.get(position).level);
			author_view.setText(posts.get(position).auther);
			time_view.setText(posts.get(position).time);
			content_view.setText(posts.get(position).detail);
		}
		
		return view;
	}

}
