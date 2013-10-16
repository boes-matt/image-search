package com.boes.imagesearch;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {

	private static final String TAG = "SearchActivity";
	
	private EditText etQuery;
	private GridView gvImages;
	
	private ArrayList<ImageResult> mImageResults;
	private ImageAdapter mAdapter;
	private Query mQuery;
	
	private static final int REQUEST_SETTINGS = 0;
	private static final int REQUEST_FULLSCREEN = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		setupAdapter();
		setupQuery();
	}
	
	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvImages = (GridView) findViewById(R.id.gvImages);
		gvImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageResult imageInfo = (ImageResult) parent.getItemAtPosition(position);
				Intent i = new Intent(getApplicationContext(), ImageActivity.class);
				i.putExtra(ImageActivity.URL, imageInfo.getFullUrl());
				startActivityForResult(i, REQUEST_FULLSCREEN);
			}
			
		});
	}

	private void setupAdapter() {
		mImageResults = new ArrayList<ImageResult>();
		mAdapter = new ImageAdapter(this, mImageResults);
		gvImages.setAdapter(mAdapter);		
	}
	
	private void setupQuery() {
		mQuery = new Query(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject response) {
				mAdapter.addAll(ImageResult.fromJSONArray(getResults(response)));
			}
			
		});
	}
	
	private JSONArray getResults(JSONObject response) {
		JSONArray results = new JSONArray();
		
		try {
			if (response.getInt(GoogleAPI.KEY_STATUS) == GoogleAPI.RESULT_OK) {
				results = response.getJSONObject(GoogleAPI.KEY_DATA).getJSONArray(GoogleAPI.KEY_RESULTS);				
			}
		} catch (JSONException e) {
			Log.e(TAG, "Error parsing response", e);
		}
		
		return results;
	}
	
	public void onSearch(View v) {
		mAdapter.clear();
		hideSoftKeyboard(v);
		
		String query = etQuery.getText().toString();
		mQuery.setQuery(query);
		mQuery.next();
		mQuery.next();
		
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
	}
	
	private void hideSoftKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_advanced) {
			Intent i = new Intent(this, AdvancedActivity.class);
			i.putExtra(AdvancedActivity.PARAMS, mQuery.getParams());
			startActivityForResult(i, REQUEST_SETTINGS);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_SETTINGS && resultCode == Activity.RESULT_OK) {
			mQuery.setParams((HashMap<String, String>) data.getSerializableExtra(AdvancedActivity.PARAMS));
		}
	}
	
	public void loadMore(View v) {
		mQuery.next();
	}

}
