package com.yahoo.akv.gridimagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.yahoo.akv.gridimagesearch.R;
import com.yahoo.akv.gridimagesearch.models.Settings;

public class SettingsActivity extends Activity {

	private Spinner size ;
	private Spinner color ;
	private Spinner type ;
	private EditText urlfilter;
	private Settings settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		size = (Spinner)findViewById(R.id.sizes);
		color = (Spinner)findViewById(R.id.colors);
		type = (Spinner)findViewById(R.id.types);
		urlfilter = (EditText)findViewById(R.id.sitefilter);
		ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.sizes_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		size.setAdapter(sizeAdapter);
		ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
		        R.array.colors_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		color.setAdapter(colorAdapter);
		ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.types_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		type.setAdapter(typeAdapter);
		settings =(Settings) getIntent().getParcelableExtra("settings");
	}

	public void saveSettings(View v) {
		// TODO Auto-generated method stub
		String size = String.valueOf(this.size.getSelectedItem());
		String color = String.valueOf(this.color.getSelectedItem());
		String type = String.valueOf(this.type.getSelectedItem());
		String urlFilter;
		if(this.urlfilter.getText().toString().isEmpty()){
			urlFilter = "";
		}
		else{
			urlFilter = this.urlfilter.getText().toString();
		}
		//Settings settings = new Settings(size,color,type,urlFilter);
		this.settings.imgSize = size;
		this.settings.imgColor=color;
		this.settings.imgType=type;
		this.settings.urlFilter= urlFilter;
		Intent i = new Intent();
		i.putExtra("settings",settings);
		setResult(RESULT_OK, i);
		finish();
	}
	
	
}
