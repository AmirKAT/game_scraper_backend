package com.mycompany.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class EnebaScraper extends WebScraper {
	
	public void run() {
		stopThread = false;
		while(!stopThread) {			
			try {
				scrapeEnebaGameData();
				sleep(3000);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}			
		}
	}
	
	void scrapeEnebaGameData() throws IOException {
		for(int a=1; a<5; a++) {
  		String url = "https://www.eneba.com/store?page=" + a + "&platforms[]=XBOX&regions[]=emea&regions[]=europe&regions[]=global&regions[]=united_kingdom&types[]=game";
  				System.out.println("Scraping Eneba page " + a);
  	
  	// Downloading HTML document from the site
  	Document doc = Jsoup.connect(url).get();
  	
  	Elements prods = doc.select("._3M7T08");
  	
//testing >> System.out.println(prods.select("._1ZwRcm").text());
  	
  	// need: title, genre, age_rating, image, link, price, platform
  	
  	for(int i=0; i<prods.size(); ++i) {
  		Elements prodWrappers = prods.select("._2rxjGA");
  		for(int j=0; j<prodWrappers.size(); j++) {
  		
    		// Gets the title of the game 
    		Elements title = prodWrappers.get(j).select("._1ZwRcm");
    		
    		// Gets the image of the game
    		Elements image = prodWrappers.get(j).select("._2vZ2Ja");
    		
    		// Gets the image of the game
    		Elements link = prodWrappers.get(j).select("._2idjXd");

    		// Gets the price of the game
    		Elements price = prodWrappers.get(j).select("._3RZkEb");
    		
    		// testing output
    		System.out.println("Title: " + title.text() + "\nImage: " + image.size() + 
    				"\nLink: " + link.size() + "\nPrice: " + price.text());
  		}
    		
  	}
  	}
  }
	

}
