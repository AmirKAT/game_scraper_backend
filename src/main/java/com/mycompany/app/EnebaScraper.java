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
				sleep(10000);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}		
		}
	}
	
//  data required || variable name that scrapes the data
//	--------------||------------------------------------
//	title  			  ||	titles
//	image  			  ||	images
//	price 			  ||	prices
//	link  			  ||  links
//	genre  			  ||	genres
	
	void scrapeEnebaGameData() throws IOException {
		for(int a=1; a<62; a++) {
			
  		String url = "https://www.eneba.com/store?page=" + a + "&platforms[]=XBOX&regions[]=emea&regions[]=europe&regions[]=global&regions[]=united_kingdom&types[]=game";
  				System.out.println("Scraping Eneba page " + a);

  		// Downloading HTML document from the site
	  	Document doc = Jsoup.connect(url).get();
	  	
	  	Elements prods = doc.select("._3M7T08");
  	
	  	// need: title, genre, image, link, price, platform
  	
	  	for(int i=0; i<prods.size(); ++i) {
  		
	  		Elements prodWrappers = prods.select("._2rxjGA");
	  		
	  		for(int j=0; j<prodWrappers.size(); j++) {
	  		
	    		// Gets the title of the game 
	    		String titles = prodWrappers.get(j).select("._1ZwRcm").text();
	    			System.out.println("\nTitle: " + titles);
	    		
	    		// Gets the image of the game
	    		Elements image = prodWrappers.get(j).select("img");
	    		
	    		for(Element imageUrl : image) {
	    			
	    			String images = imageUrl.attributes().get("src");
	    				System.out.println("Image: " + images);
	    			
	    		}// end of image link for loop
	    		
	    		// Gets the link of the game
	    		Elements link = prodWrappers.get(j).select("._2idjXd");
	    		
	    		// Gets the price of the game
	    		String prices = prodWrappers.get(j).select("._3RZkEb").text();
	    			System.out.println("Price: " + prices);
	    		
	    		for(Element linkUrl : link) {
	    			
	    			String linkGame = linkUrl.attributes().get("href");
	    				String links = "https://www.eneba.com" + linkGame;
	    					System.out.println("Link: " + links);
	    			
	    			//scraping info from each linkGame
	    			for(int b=0; b<link.size(); b++) {
	    				
	    				String url2 = "https://www.eneba.com" + linkGame;
	    				
	    				Document doc2 = Jsoup.connect(url2).get();
	    				
	    				Elements prods2 = doc2.select("._3MoLlD");
	    				
	    				for(int c=0; c<prods2.size(); c++) {
	    					
	    					String genres = prods2.get(c).select("._3w9_g5").text();
	    						System.out.println("Genre: " + genres);
	    					
	    				}//end of for loop to connect to link game
	    				
	    			}//end of url2 for loop
	    			
	    		}// end of game link for loop
	    		
	  		}//end of 3rd for loop
  		
	  	}//end of 2nd for loop
  	
    }//end of 1st for loop
		
  }//end of scrapeEnebaGameData
	
}//end of public class

