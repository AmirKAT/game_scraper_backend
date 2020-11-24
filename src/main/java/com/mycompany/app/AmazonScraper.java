package com.mycompany.app;

public class AmazonScraper extends WebScraper{

	
	public void run() {
		stopThread = false;
		while(!stopThread) {
			
			try {
				scrapeGameData();
				sleep(3000);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}	
	}
	
	public void scrapeGameData() {
		System.out.println("Scraping Amazon");
	}
}
