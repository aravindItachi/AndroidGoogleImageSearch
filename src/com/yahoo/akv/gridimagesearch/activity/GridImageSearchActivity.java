package com.yahoo.akv.gridimagesearch.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.akv.gridimagesearch.R;
import com.yahoo.akv.gridimagesearch.adapters.ImageResultsAdapter;
import com.yahoo.akv.gridimagesearch.listeners.EndlessScrollListener;
import com.yahoo.akv.gridimagesearch.models.ImageResult;
import com.yahoo.akv.gridimagesearch.models.Settings;

public class GridImageSearchActivity extends Activity {

	private ArrayList<ImageResult> imageResults;
	private EditText query;
	private GridView imagegrid;
	private ImageResultsAdapter imageResultsAdapter;
	private Settings settings;
	private String queryUrl;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image_search);
        query = (EditText)findViewById(R.id.query);
        imagegrid = (GridView)findViewById(R.id.imagegridview);
        imageResults = new ArrayList<ImageResult>();
        imageResultsAdapter = new ImageResultsAdapter(this, imageResults);
        imagegrid.setAdapter(imageResultsAdapter);
        imagegrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ImageResult imageResult = imageResults.get(position);
				Intent i = new Intent(GridImageSearchActivity.this, DisplayImageActivity.class);
				i.putExtra("imgurl", imageResult.tburl);
				startActivity(i);
			}
        });
        
        imagegrid.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// TODO Auto-generated method stub
				String url = GridImageSearchActivity.this.queryUrl;
				url +=  "&start=" + (page * totalItemsCount);
				GridImageSearchActivity.this.makeApiCall(url);
			}
		});
        this.settings = new Settings("Any", "Any", "Any", "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grid_image_search, menu);
        return true;
    }
    public void onAddItemClicked(MenuItem mi) {
        // handle click here
    	//Toast.makeText(this,"Add Item", Toast.LENGTH_SHORT).show();
    	Settings settings = this.settings;
    	Intent imageSettings = new Intent(this,SettingsActivity.class);
    	imageSettings.putExtra("settings", settings);
    	startActivityForResult(imageSettings, 1);
    	
    	
     }
    public void searchButtonClicked(View v) {
		// TODO Auto-generated method stub
    	
    	String q = query.getText().toString();
    	String imgSize = this.settings.imgSize;
    	String imgColor = this.settings.imgColor;
    	String imgType = this.settings.imgType;
    	String urlFilter = this.settings.urlFilter;
    	if(!q.isEmpty()){
    		String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="+ q +"&rsz=8";
    		if(!imgSize.equals("Any")) {
    			url += "&imgsz=" + imgSize.toLowerCase(); 
    		}
    		if(!imgColor.equals("Any")) {
    			url += "&imgcolor=" + imgColor.toLowerCase(); 
    		}
    		if(!imgType.equals("Any")) {
    			url += "&imgtype=" + imgType.toLowerCase(); 
    		}
    		if(!urlFilter.equals("")) {
    			url += "&as_sitesearch=" + urlFilter.toLowerCase(); 
    		}
    		this.queryUrl = url;
    		//makeApiCall(url);
    	}
		
	}
    
    public void makeApiCall(String url){
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get(url , new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				Log.d("Debug", response.toString());
				try {
					JSONArray imageresultsarray = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					imageResultsAdapter.clear();
					imageResultsAdapter.addAll(ImageResult.getImageResultsAray(imageresultsarray));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	if(requestCode == 1){
    		if(resultCode == RESULT_OK){
    			Settings settings = (Settings)data.getParcelableExtra("settings");
    			this.settings = settings;
    		}
    	}
    }
}
