package com.mycompany.app.config;

import java.util.ArrayList;
import com.mycompany.app.WebScraper;

public class WebScraperManager {

	private ArrayList<WebScraper> webScrapers;

	// starts the web scrapers
	public void startWebScraperManager() {
		for (WebScraper scraper : webScrapers) {
			scraper.start();
		}
	}

	// below gets the list of web scrapers and also sets them
	public ArrayList<WebScraper> getScrapers() {
		return webScrapers;
	}


	public void setScrapers(ArrayList<WebScraper> webScrapers) {
		this.webScrapers = webScrapers;
	}

}
