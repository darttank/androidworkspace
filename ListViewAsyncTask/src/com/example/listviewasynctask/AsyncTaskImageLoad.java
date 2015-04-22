package com.example.listviewasynctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class AsyncTaskImageLoad  extends AsyncTask<String, Integer, Bitmap>{
	private ImageView image=null;
public AsyncTaskImageLoad(ImageView img){
	   image=img;
}
	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO Auto-generated method stub
		    URL url;
			try {
				url = new URL(params[0]);
				 HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		         conn.setRequestMethod("POST");
	             conn.setConnectTimeout(5000);
		    if(conn.getResponseCode() == 200){
		    	InputStream input=conn.getInputStream();
		    	Bitmap map = BitmapFactory.decodeStream(input);
		    	return map;
		    }		
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		
		return null;
	}
	
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(image!=null && result!=null){
			image.setImageBitmap(result);
		}
		 super.onPostExecute(result);

		
	}

}
