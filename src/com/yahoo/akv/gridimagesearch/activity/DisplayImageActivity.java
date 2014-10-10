package com.yahoo.akv.gridimagesearch.activity;

import com.squareup.picasso.Picasso;
import com.yahoo.akv.gridimagesearch.R;
import com.yahoo.akv.gridimagesearch.R.id;
import com.yahoo.akv.gridimagesearch.R.layout;
import com.yahoo.akv.gridimagesearch.R.menu;
import com.yahoo.akv.gridimagesearch.models.ImageResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class DisplayImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_image);
		String imgurl = getIntent().getStringExtra("imgurl");
		ImageView fullImage = (ImageView) findViewById(R.id.imgFullSize);
		Picasso.with(this).load(imgurl).into(fullImage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
