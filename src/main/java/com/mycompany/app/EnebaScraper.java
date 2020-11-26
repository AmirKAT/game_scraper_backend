package com.mycompany.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
		for(int a=1; a<100; a++) {
			
  		String url = "https://www.eneba.com/store?page=" + a + "&platforms[]=XBOX&regions[]=emea&regions[]=europe&regions[]=global&regions[]=united_kingdom&types[]=game";
  				System.out.println("Scraping Eneba page " + a);
  	
  		// Downloading HTML document from the site
	  	Document doc = Jsoup.connect(url).get();
	  	
	  	Elements prods = doc.select("._3M7T08");
  	
	  	// need: title, genre, age_rating, image, link, price, platform
  	
  	for(int i=0; i<prods.size(); ++i) {
  		
  		Elements prodWrappers = prods.select("._2rxjGA");
  		
  		for(int j=0; j<prodWrappers.size(); j++) {
  		
    		// Gets the title of the game 
    		Elements title = prodWrappers.get(j).select("._1ZwRcm");
    		
    		// Gets the image of the game
    		Elements image = prodWrappers.get(j).select("img");
    		
    		for(Element imageUrl : image) {
    			String linkImage = imageUrl.attributes().get("src");
    			
    			System.out.println("Image: " + linkImage );
    		}// end of image link for loop
    		
    		// Gets the link of the game
    		Elements link = prodWrappers.get(j).select("._2idjXd");
    		
    		for(Element linkUrl : link) {
    			String linkGame = linkUrl.attributes().get("href");
    			
    			System.out.println("Link: " + linkGame );
    		}// end of game link for loop

    		// Gets the price of the game
    		Elements price = prodWrappers.get(j).select("._3RZkEb");
    		
    		// testing output
    		System.out.println("Title: " + title.text() + "\nPrice: " + price.text() + "\n");
    		
  		}//end of 3rd for loop
  		
  	}//end of 2nd for loop
  	
    }//end of 1st for loop
		
  }//end of scrapeEnebaGameData
	
}//end of public class

