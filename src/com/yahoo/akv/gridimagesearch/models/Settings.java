package com.yahoo.akv.gridimagesearch.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Settings implements Parcelable{

	public String imgSize;
	public String imgColor;
	public String imgType;
	public String urlFilter;
	
	
	public Settings() {
		// TODO Auto-generated constructor stub
	}


	public Settings(String imgSize, String imgColor, String imgType,
			String urlFilter) {
		this.imgSize = imgSize;
		this.imgColor = imgColor;
		this.imgType = imgType;
		this.urlFilter = urlFilter;
	}

	public Settings(Parcel source){
		this.imgSize = source.readString();
		this.imgColor = source.readString();
		this.imgType = source.readString();
		this.urlFilter = source.readString();
	}
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(imgSize);
		dest.writeString(imgColor);
		dest.writeString(imgType);
		dest.writeString(urlFilter);
	}
	
	public static final Parcelable.Creator<Settings> CREATOR = new Parcelable.Creator<Settings>() {
		 
        @Override
        public Settings createFromParcel(Parcel source) {
            return new Settings(source);
        }
 
        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };
	
}
