package com.phgame.newsmth.adapter;

import java.util.Vector;

import net.tsz.afinal.FinalBitmap;

import org.xml.sax.XMLReader;

import com.example.android.bitmapfun.util.ImageFetcher;
import com.example.android.bitmapfun.util.ImageWorker;
import com.example.android.bitmapfun.util.ImageCache.ImageCacheParams;
import com.example.android.bitmapfun.util.OnCompleteListener;

import com.phgame.newsmth.R;
import com.phgame.newsmth.data.PostDetailBean;
import com.phgame.newsmth.ui.BaseActivity;
import com.squareup.picasso.Picasso;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.phgame.newsmth.ui.*;

public class PostDetailAdapter extends BaseAdapter {

	private Vector<PostDetailBean> posts;
	private Context ctx;
	public static final String IMAGE_CACHE_DIR = "thumbs";
	myImageFetcher imagefetcher;
	private ImageWorker worker;
	//TextView 

	public PostDetailAdapter(Context ctx) {
		this.ctx = ctx;
		worker = ((MyApplication)ctx.getApplicationContext()).getImageWorker();
		worker.addImageCache(((BaseActivity) ctx).getSupportFragmentManager(), 
				((MyApplication)ctx.getApplicationContext()).getImageCacheParams());
		
		
	}

	public void setData(Vector<PostDetailBean> tiezis) {

		this.posts = tiezis;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (posts != null)
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
		
		TagHandler tagHandler = new TagHandler() {

			@Override
			public void handleTag(boolean opening, String tag,
					Editable output, XMLReader xmlReader) {
				// TODO Auto-generated method stub
				if (opening) {
					if (tag.equalsIgnoreCase("br")) {
						output.append("\n");
					}
				}

			}

		};

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//
			// view = inflater.inflate(R.layout.layout_content, parent);
			//
			view = inflater.inflate(R.layout.postdetail_listview, null);

		} else {
			// to do maybe need to refresh
			view = convertView;
		}
		TextView level_view = (TextView) view.findViewById(R.id.level);
		TextView author_view = (TextView) view.findViewById(R.id.author);
		TextView time_view = (TextView) view.findViewById(R.id.time);
		final TextView content_view = (TextView) view.findViewById(R.id.content);

		level_view.setText(posts.get(position).level);
		author_view.setText(posts.get(position).auther);
		time_view.setText(posts.get(position).time);

		Spanned str = Html.fromHtml(posts.get(position).detail,new ImageGetter(){

			@Override
			public Drawable getDrawable(String source) {
				
				Drawable drawable = worker.loadDrawable(source, new OnCompleteListener(){

					@Override
					public void onComplete() {
						// TODO Auto-generated method stub
						content_view.postInvalidate();
					}
					
				});
				Rect rect = drawable.getBounds();
				drawable.setBounds(0, 0, ctx.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size),
						ctx.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size));
				return drawable;
			}
			
		}, tagHandler);
		
		content_view.setText(str);

		return view;
	}
	
	
	class myImageFetcher implements ImageGetter{
		private TextView view;
		private ImageWorker worker;
		private int mImageThumbSize;
		private OnCompleteListener listener;
		public myImageFetcher(){
			//this.view = view;
			init();

		}
		private void init(){
			ImageCacheParams cacheParams = new ImageCacheParams(ctx, IMAGE_CACHE_DIR);

	        cacheParams.setMemCacheSizePercent(0.1f); // Set memory cache to 25% of app memory
	        
	        mImageThumbSize = ctx.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
		    //int  mImageThumbSpacing = ctx.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
			worker = new ImageFetcher(ctx,mImageThumbSize);
			worker.addImageCache(((BaseActivity) ctx).getSupportFragmentManager(), cacheParams);
			worker.setLoadingImage(R.drawable.smileface);	
		}
		public void setonCompleteListener(OnCompleteListener listener){
			this.listener = listener;
			
		}
		@Override
		public Drawable getDrawable(String source) {

			Drawable drawable = worker.loadDrawable(source, listener);
			Rect rect = drawable.getBounds();
			drawable.setBounds(0, 0, mImageThumbSize, mImageThumbSize);

			
			return drawable;
		}
		
	}
}
