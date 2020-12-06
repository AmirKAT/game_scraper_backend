package com.mycompany.app;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mycompany.app.dao.ProductDao;
import com.mycompany.app.entity.Product;

public class GamingDragonScraper extends WebScraper {
	ProductDao productDao = null;
	public void run() {
		stopThread = false;
		while(!stopThread) {			
			
			try {
				scrapeGamingDragonGameData();
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
//genre  			  ||	genres
	
	void scrapeGamingDragonGameData() throws IOException {
		for(int a=1; a<7; a++) {
			
  		String url = "https://www.gamingdragons.com/en/xbox-live?page=" + a;
  				System.out.println("Scraping GamingDragons page " + a);
  		System.out.println("url:"+url);		
  		// Downloading HTML document from the site
	  	Document doc = Jsoup.connect(url).get();
	  	
	  	Elements prods = doc.select("#category");
	  	
	  	for(int i=0; i<prods.size(); i++) {
	  		
	  		Elements prodWrappers = prods.select(".game-info-box");
	  		
	  		for(int j=0; j<prodWrappers.size(); j++) {
	  			Product product = new Product(); 
	  			
	  			Elements title = prodWrappers.get(j).select(".game-title");
	  			
	  			for(int k=0; k<title.size(); k++) {
	  				
	  				Elements getTitle = title.get(k).select("a");
	  				
	  				for(Element gameTitle : getTitle) {
	  					
	  					String titles = gameTitle.attributes().get("title");
	  						System.out.println("\nTitle: " + titles);
	  						product.setTitle(titles);
	  				}//getting game title
	  				
	  			}//end of k for
	  			
	  			Elements image = prodWrappers.get(j).select(".game-img");
	  			
	  			for(int k2=0; k2<image.size(); k2++) {
	  				
	  				Elements getImage = image.get(k2).select("img");
	  				
	  				for(Element gameImage : getImage) {
	  					
	  					String imageGame = gameImage.attributes().get("src");
	  						String images = "http:" + imageGame;
	  						System.out.println("Image: " + images);
	  						product.setImage(images);
	  				}//getting game image
	  				
	  			}//end of k2 for
	  			
	  			String prices = prodWrappers.get(j).select(".game-price").text();
  				System.out.println("Price: " + prices);
	  			product.setPrice(prices);
	  			Elements link = prodWrappers.get(j).select(".game-img");
	  			
	  			for(int k3=0; k3<link.size(); k3++) {
	  				
	  				Elements getLink = link.get(k3).select("a");
	  				
	  				for(Element gameLink : getLink) {
	  					
	  					String linkGame = gameLink.attributes().get("href");
	  						String links = "https://www.gamingdragons.com" + linkGame;
	  						System.out.println("Link: " + links);
	  						product.setLink(links);
	  						for(int b=0; b<link.size(); b++) {
	  	    				
	  	    				String url2 = "https://www.gamingdragons.com" + linkGame;
	  	    				
	  	    				Document doc2 = Jsoup.connect(url2).get();
	  	    				
	  	    				Elements prods2 = doc2.select("#product");
	  	    				
	  	    				for(int k4=0; k4<prods2.size(); k4++) {
	  	    					
	  	    					Elements genre = prods2.select(".miniInfo");

	  	    					for(int k5=0; k5<genre.size(); k5++) {
	  	    						
	  	    						String genres = genre.get(k5).select("a").text();
	  	    							System.out.println("Genre: " + genres);
	  	    						
	  	    					}//end of getGenre
	  	    					
	  	    				}//end of for k4
	  	    				
	  						}//end of game link info
	  						
	  				}//getting game link
	  				
	  			}//end of k3 for
	  			productDao.save(product);
	  		}//end of 3rd for
	  		
	  	}//end of 2nd for
	  	
		}//end of 1st for
		
	}//end of scrapeGamingDragonGameData
	 public void setProductDao(ProductDao productDao) {
		 this.productDao = productDao;
	 }
}//end of public class
