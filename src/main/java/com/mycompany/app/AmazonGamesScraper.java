package com.mycompany.app;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mycompany.app.dao.ProductDao;
import com.mycompany.app.entity.Product;

public class AmazonGamesScraper extends WebScraper {
	private ProductDao productDao = null;

	public void run() {
		stopThread = false;
		while (!stopThread) {

			try {
				scrapeAmazonGameData();
				sleep(10000);
			} catch (Exception ex) {
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

	void scrapeAmazonGameData() throws IOException {
		for (int a = 1; a < 333; a++) {

			String url = "https://www.amazon.co.uk/s?i=videogames&rh=n%3A13978691031&page=" + a
			    + "&qid=1607156844&rd=1&ref=sr_pg_" + a;
			System.out.println(url);
			System.out.println("Scraping AmazonGames page " + a);

			// Downloading HTML document from the site
			Document doc = Jsoup.connect(url).get();

			Elements prods = doc.select(".s-desktop-width-max.s-desktop-content.sg-row");

			for (int i = 0; i < prods.size(); i++) {

				Elements prodWrappers = prods.select(".s-include-content-margin.s-border-bottom.s-latency-cf-section");

				for (int j = 0; j < prodWrappers.size(); j++) {
					Product product = new Product();
					String titles = prodWrappers.get(j).select(".a-size-medium.a-color-base.a-text-normal").text();
					System.out.println("\nTitle: " + titles);
					product.setTitle(titles);
					Elements image = prodWrappers.get(j).select("img");

					for (Element gameImage : image) {

						String images = gameImage.attributes().get("src");
						System.out.println("Image: " + images);
						product.setImage(images);
					} // end of getting image link

					Element price = prodWrappers.get(j).select(".a-offscreen").first();

					if (price != null) {
						String prices = price.text();
						System.out.println("Price: " + prices);
						product.setPrice(prices);
					} // ignoring products where price is null

					Elements link = prodWrappers.get(j).select("a.a-link-normal.s-no-outline");

					for (Element gameLink : link) {

						String linkGame = gameLink.attributes().get("href");
						String links = "https://www.amazon.co.uk" + linkGame;
						System.out.println("Link: " + links);
						product.setLink(links);
					} // end of getting image link

					productDao.save(product);

				} // end of 3rd for

			} // end of 2nd for

		} // end of 1st for

	}// end of scrapeSimplyGamesGameData()

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}// end of public class