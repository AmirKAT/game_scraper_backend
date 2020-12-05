package com.mycompany.app;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZatuGamesScraper extends WebScraper{

	public void run() {
		stopThread = false;
		while(!stopThread) {			
			
			try {
				scrapeZatuGamesGameData();
				sleep(10000);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}		
		}
	}
	
//data required || variable name that scrapes the data
//--------------||------------------------------------
//title  			  ||	titles
//image  			  ||	images
//price 			  ||	prices
//link  			  ||  links
	
	void scrapeZatuGamesGameData() throws IOException {
		for(int a=1; a<24; a++) {
			
  		String url = "https://www.board-game.co.uk/category/video-games/?min=0&max=300&video-game-platform=xbox-one&page=" + a;
  				System.out.println("Scraping ZatuGames page " + a);

  		// Downloading HTML document from the site
	  	Document doc = Jsoup.connect(url).get();
	  	
	  	Elements prods = doc.select(".shop-main");
	  	
	  	for(int i=0; i<prods.size(); i++) {
	  		
	  		Elements prodWrappers = prods.select(".zg-product");
	  		
	  		for(int j=0; j<prodWrappers.size(); j++) {

	  			String titles = prodWrappers.get(j).select(".zg-product-title").text();
  				System.out.println("\nTitle: " + titles);
  				
	  			Elements imageContainer = prodWrappers.get(j).select(".zg-product-image-container");
	  			
	  			for(int k1=0; k1<imageContainer.size(); k1++) {
		  			
	  				Elements image = imageContainer.get(k1).select("img.lazy-loaded");
	  				
		  			for(Element gameImage : image) {
		  				
		  				String images = gameImage.attributes().get("src");
		  					System.out.println("Image: " + images);
		  			}
	  				
	  			}//end of getting image
	  			
	  			String prices = prodWrappers.get(j).select("span.woocommerce-Price-amount.amount").first().text();
	  			
	  			if (prices != null) {
	  				System.out.println("Price: " + prices);
	  			}//ignoring products where price is null
	  			
	  			Elements linkContainer = prodWrappers.get(j).select(".zg-product-image-container");
	  			
	  			for(int k2=0; k2<linkContainer.size(); k2++) {
		  			
	  				Elements link = linkContainer.get(k2).select("a.zg-product-image");
	  				
		  			for(Element gameLink : link) {
		  				
		  				String links = gameLink.attributes().get("href");
		  					System.out.println("Link: " + links);
		  			}
	  				
	  			}//end of getting game link
	  			
	  		}//end of 3rd for
	  		
	  	}//end of 2nd for
	  		
	  }//end of 1st for
		
	}//end of scrapeSimplyGamesGameData()

}//end of public class