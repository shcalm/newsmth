package com.phgame.newsmth.ui;

import com.example.android.bitmapfun.util.ImageFetcher;
import com.example.android.bitmapfun.util.ImageWorker;
import com.example.android.bitmapfun.util.ImageCache.ImageCacheParams;
import com.phgame.newsmth.R;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks;

public class MyApplication extends Application {

	private ImageWorker worker;
	private int mImageThumbSize;
	public static final String IMAGE_CACHE_DIR = "thumbs";
	private ImageCacheParams cacheParams;
	
	public ImageWorker getImageWorker(){
		return worker;
	}
	public ImageCacheParams getImageCacheParams(){
		return cacheParams;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initImageCache();
	}



	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}




	private void initImageCache() {
		cacheParams = new ImageCacheParams(this.getApplicationContext(), IMAGE_CACHE_DIR);

		cacheParams.setMemCacheSizePercent(0.1f); // Set memory cache to 25% of app memory

		mImageThumbSize = this.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
		//int  mImageThumbSpacing = ctx.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
		worker = new ImageFetcher(this,mImageThumbSize);
		//worker.addImageCache(((BaseActivity) ctx).getSupportFragmentManager(), cacheParams);
		worker.setLoadingImage(R.drawable.smileface);	
	}

}
