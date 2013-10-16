package com.boes.imagesearch;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;

public class ImageActivity extends Activity {

	public static final String URL = "url";
	private SmartImageView ivImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		ivImage = (SmartImageView) findViewById(R.id.ivPhoto);
		ivImage.setImageUrl(getIntent().getStringExtra(URL));
	}


}
