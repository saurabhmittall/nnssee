package com.nse.thread;

import sm.nse.util.FileOperation;

public class SSPattern extends Thread {
	FileOperation file = new FileOperation();
	String url;
	String localPath;
   public SSPattern(String url,String localPath)
   {
	   this.url=url;
	   this.localPath=localPath;
   }
	public void run(){
		file.downloadHtml(url, localPath);
   }
}