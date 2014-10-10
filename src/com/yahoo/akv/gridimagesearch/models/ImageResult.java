package com.yahoo.akv.gridimagesearch.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult {

	public String url;
	public String tburl;
	public String title;
	
	public ImageResult(JSONObject json) {
		// TODO Auto-generated constructor stub
		try {
			this.url = json.getString("url");
			this.tburl = json.getString("tbUrl");
			this.title = json.getString("title");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ImageResult> getImageResultsAray(JSONArray results){
		ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
		for(int i =0; i < results.length() ; i++){
			try {
				JSONObject img = results.getJSONObject(i);
				imageResults.add(new ImageResult(img));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return imageResults;
	}
}
