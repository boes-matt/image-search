package com.boes.imagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.image.SmartImageView;

public class ImageActivity extends Activity {

	public static final String URL = "url";
	private SmartImageView ivImage;
	private String mUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		mUrl = getIntent().getStringExtra(URL);
		
		ivImage = (SmartImageView) findViewById(R.id.ivPhoto);
		ivImage.setImageUrl(mUrl);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_share) {
			
			// Fire implicit intent
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_STREAM, mUrl);
			shareIntent.setType("image/*");
			startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share)));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
