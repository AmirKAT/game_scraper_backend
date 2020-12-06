package com.mycompany.app;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mycompany.app.dao.ProductDao;
import com.mycompany.app.entity.Product;

public class MmogaScraper extends WebScraper {
	ProductDao productDao = null;
	public void run() {
		stopThread = false;
		while(!stopThread) {			
			
			try {
				scrapeMmogaGameData();
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
	
	void scrapeMmogaGameData() throws IOException {
		for(int a=1; a<10; a++) {
			
  		String url = "https://www.mmoga.co.uk/Game-Keys/page-" + a + "/?Platform=Xbox-LIVE&Type=Full-Game";
  				System.out.println("Scraping MMOGA page " + a);

  		// Downloading HTML document from the site
	  	Document doc = Jsoup.connect(url).get();
	  	
	  	Elements prods = doc.select("ul");
	  	
	  	for(int i=0; i<prods.size(); i++) {
	  		
	  		Product product = new Product();
	  		
	  		Elements prodWrappers = prods.select("li");
	  		
	  		for(int j=0; j<prodWrappers.size(); j++) {
	  			
	  			Elements title = prodWrappers.get(j).select(".keyImg");
	  			
	  			for(int k=0; k<title.size(); k++) {
	  				
	  				Elements getTitle = title.get(k).select("a");
	  				
	  				for(Element titleText : getTitle) {
		    			
		    			String titles = titleText.attributes().get("title");
		    				System.out.println("\nTitle: " + titles);
		    				product.setTitle(titles);
		    		}//end of for getting titleGame
	  				
	  			}//end of for k
	  			
	  			Elements image = prodWrappers.get(j).select(".keyImg");
	    		
	  			for(int k1=0; k1<image.size(); k1++) {
	  				
	  				Elements getImage = image.get(k1).select("a");
	  				
	  				for(Element imageUrl : getImage) {
		    			
		    			String imageGame = imageUrl.attributes().get("data-background");
		    				String images = "https://www.mmoga.co.uk" + imageGame;
		    				System.out.println("Image: " + images);
		    				product.setImage(images);
		    		}//end of for getting imageUrl
	  				
	  			}//end of for k1
	  			
	  			Elements price = prodWrappers.get(j).select(".keyPrice");
	  			
	  			for(int k2=0; k2<price.size(); k2++) {
	  				
	  				String prices = price.get(k2).select("del").text();
		    			System.out.println("Price: " + prices);
		    		product.setPrice(prices);	
	  				
	  			}//end of for k2
	  			
	  			Elements link = prodWrappers.get(j).select(".keyImg");
	  			
	  			for(int k3=0; k3<link.size(); k3++) {
	  				
	  				Elements getLink = link.get(k3).select("a");
	  				
	  				for(Element linkUrl : getLink) {
	  					
	  					String linkGame = linkUrl.attributes().get("href");
	  						String links = "https://www.mmoga.co.uk" + linkGame;
	  						System.out.println("Link: " + links);
	  					product.setLink(links);
	  				}//end of for getLink
	  				
	  			}//end of for k3
	  			
	  		}//end of for j
	  		
	  		productDao.save(product);
	  		
	  	}//end of for i
	  	
		}//end of 1st for loop
		
	}//end of scrapeMmogaGameData
	 public void setProductDao(ProductDao productDao) {
		 this.productDao = productDao;
	 }
}//end of public class
