package com.example.multhread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MultiThreadDownload implements Runnable{
	int id;  
    String path;
    int end,start;
    RandomAccessFile savedFile;
    int currentDownloadSize = 0;
    boolean finished; 
    DownloadService downloadService;  
    public MultiThreadDownload(int id, File savedFile, int block, String path, Integer downlength, DownloadService downloadService) throws Exception {  
    	        this.id = id;  
    	        this.path = path;  
    	        if (downlength != null) this.currentDownloadSize = downlength;  
    	        this.savedFile = new RandomAccessFile(savedFile, "rwd");  
    	        this.downloadService = downloadService;  
    	        start = id * block + currentDownloadSize;  
    	        end = (id + 1) * block;  
    	    }  

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	    try {
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		    conn.setConnectTimeout(5000);
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Range", "bytes="+ start+"-"+end);
		    InputStream in = conn.getInputStream();
		    byte[] buffer = new byte[1024];
		    int len = 0;
		    savedFile.seek(start);
		    while((len = in.read(buffer))!=-1){
		    	savedFile.write(buffer, 0, len);
		    	currentDownloadSize += len;
		    }
		    savedFile.close();
		    in.close();
		    conn.disconnect();
		    finished = true;
		    
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	
	}

}
