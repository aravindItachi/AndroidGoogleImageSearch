package com.yahoo.akv.gridimagesearch.adapters;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yahoo.akv.gridimagesearch.R;
import com.yahoo.akv.gridimagesearch.models.ImageResult;

public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultsAdapter(Context context, List<ImageResult> objects) {
		super(context,R.layout.image_view, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageResult img = getItem(position);
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.image_view, parent, false);
		}
			TextView imgTitle = (TextView) convertView.findViewById(R.id.imagetitletxt);
			ImageView imgView = (ImageView) convertView.findViewById(R.id.imageResults);
			imgView.setImageResource(0);
			imgTitle.setText(Html.fromHtml(img.title));
			Picasso.with(getContext()).load(img.tburl).into(imgView);	
		return convertView;
	}
	
	

}
