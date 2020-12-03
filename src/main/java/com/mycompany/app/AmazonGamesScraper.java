package com.mycompany.app;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonGamesScraper extends WebScraper{

	public void run() {
		stopThread = false;
		while(!stopThread) {			
			
			try {
				scrapeAmazonGameData();
				sleep(10000);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}		
		}
	}
	
	void scrapeAmazonGameData() throws IOException {
		for(int a=1; a<333; a++) {
			
  		String url = "https://www.amazon.co.uk/s?i=videogames&rh=n%3A13978691031&page=" + a + "&qid=1606997309&rd=1&ref=sr_pg_" + a;
  				System.out.println("Scraping AmazonGames page " + a);

  		// Downloading HTML document from the site
	  	Document doc = Jsoup.connect(url).get();
	  	
	  	Elements prods = doc.select(".s-desktop-width-max.s-desktop-content.sg-row");
	  	
	  	for(int i=0; i<prods.size(); i++) {
	  		
	  		Elements prodWrappers = prods.select(".s-include-content-margin.s-border-bottom.s-latency-cf-section");
	  		
	  		for(int j=0; j<prodWrappers.size(); j++) {
	  			
	  			Elements title = prodWrappers.get(j).select(".a-size-medium.a-color-base.a-text-normal");
	  				System.out.println("\nTitle: " + title.text());
	  				
	  			Elements image = prodWrappers.get(j).select("img");
	  			
	  			for(Element gameImage : image) {
	  				
	  				String imageGame = gameImage.attributes().get("src");
	  					System.out.println("Image: " + imageGame);
	  				
	  			}//end of getting image link
	  			
	  			Element price = prodWrappers.get(j).select(".a-offscreen").first();
	  				
	  			if (price != null) {
	  				System.out.println("Price: " + price.text());
	  			}//ignoring products where price is null
	  			
	  			Elements link = prodWrappers.get(j).select("a.a-link-normal.s-no-outline");
	  			
	  			for(Element gameLink : link) {
	  				
	  				String linkGame = gameLink.attributes().get("href");
	  					System.out.println("Link: " + "https://www.amazon.co.uk" + linkGame);
	  				
	  			}//end of getting image link
	  			
	  		}//end of 3rd for
	  		
	  	}//end of 2nd for
	  		
	  }//end of 1st for
		
	}//end of scrapeSimplyGamesGameData()

}//end of public class