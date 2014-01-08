package com.phgame.newsmth.ui;

import com.example.android.bitmapfun.util.OnCompleteListener;
import com.phgame.newsmth.R;
import com.phgame.newsmth.adapter.TopTenAdapter;
import com.phgame.newsmth.task.TopTenTask;
import com.phgame.newsmth.ui.widget.PhProgressBarDialog;
import com.phgame.newsmth.ui.widget.PullToRefreshListView;
import com.phgame.newsmth.ui.widget.PullToRefreshListView.OnRefreshListener;
import com.phgame.newsmth.ui.widget.ScrollLayout;
import com.phgame.newsmth.ui.widget.ScrollLayout.OnViewChangeListener;
import com.phgame.newsmth.util.SmthCrawler;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.phgame.newsmth.data.*;

public class MainView extends BaseActivity implements OnTouchListener {

	PullToRefreshListView toptenView;
	private RadioButton settingbutton;
	private ScrollLayout scrolllayout;
	private RadioButton toptenbutton;
	private RadioButton picturebutton;
	private RadioButton allforumbutton;
	private TextView titletextview;
	private ProgressDialog bar;

	private void setTitle(String title) {
		titletextview.setText(title);
	}
	
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		this.setContentView(R.layout.mainview);
		
		scrolllayout = (ScrollLayout) this.findViewById(R.id.main_scrolllayout);
		scrolllayout.SetOnViewChangeListener(new OnViewChangeListener() {
		
			@Override
			public void OnViewChange(int view) {
				// TODO Auto-generated method stub
				switch (view) {
				case 0:
					setTitle("ﾽ￱￈ￕￊﾮﾴ￳");
					break;
				case 1:
					setTitle("ͼƬ");
					break;
				case 2:
					setTitle("ￋ￹ￓ￐ﾰ￦ￃ￦");
					break;
				case 3:
					setTitle("ￎￒﾵￄ");
					break;
				}
			}
		
		});
		
		toptenView = (PullToRefreshListView) this
				.findViewById(R.id.frame_listview_topten);
		
		TopTenAdapter adapter = new TopTenAdapter(MainView.this);
		toptenView.setAdapter(adapter);
		toptenView.setOnTouchListener(this);
		
		titletextview = (TextView) this.findViewById(R.id.main_head_title);
		
		toptenView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String url = ((TieZiBean) toptenView.getAdapter().getItem(
						position)).getContentUrl();
				Intent i = new Intent();
				i.setAction("com.phgame.openpostdetail");
				i.setClass(MainView.this, PostDetailActivity.class);
				i.putExtra("url", url);
				MainView.this.startActivity(i);							
		
			}
			
		});
		toptenView.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				RefreshTopTen(false);
			}

		});

		toptenbutton = (RadioButton) this
				.findViewById(R.id.main_footbar_topten);
		toptenbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (scrolllayout.getCurScreen() == 0)
					return;
				else {

					scrolllayout.snapToScreen(0);
				}
			}

		});
		picturebutton = (RadioButton) this
				.findViewById(R.id.main_footbar_pciture);
		picturebutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (scrolllayout.getCurScreen() == 1)
					return;
				else
					scrolllayout.snapToScreen(1);
			}
			
		});
		allforumbutton = (RadioButton) this
				.findViewById(R.id.main_footbar_allforum);
		allforumbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (scrolllayout.getCurScreen() == 2)
					return;
				else
					scrolllayout.snapToScreen(2);
			}
			
		});
		
		settingbutton = (RadioButton) this
				.findViewById(R.id.main_footbar_setting);
		settingbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (scrolllayout.getCurScreen() == 3)
					return;
				else {
		
					scrolllayout.snapToScreen(3);
				}
			}
		
		});
		
	}
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		bar = new PhProgressBarDialog(this);
		bar.setIndeterminate(true);
		bar.setCancelable(false);
		bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		
		// bar.di

		return bar;
	}

	private void RefreshTopTen(boolean showui) {
		
		TopTenTask toptentask = new TopTenTask(toptenView);
		if (showui) {
			this.showDialog(0);
			toptentask.setOnCompleteListener(new OnCompleteListener() {

				@Override
				public void onComplete() {
					if (bar != null)
						bar.dismiss();

				}

			});
		}
		toptentask.execute();
		

		//TopTenTask.execute();
		
	}
	
	protected void onResume(){
		super.onResume();
		RefreshTopTen(true);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
