package com.nse.thread;

import sm.nse.util.FileOperation;

public class WorkerThread implements Runnable {
	  
	FileOperation file = new FileOperation();
	String url;
	String localPath;
    
    public WorkerThread(String url,String localPath){
    	 this.url=url;
  	   this.localPath=localPath;
    }

   
    public void run() {
        
    	file.downloadHtml(url, localPath);
        System.out.println(Thread.currentThread().getName()+" End.");
    }

   
}
