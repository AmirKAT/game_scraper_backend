package com.mycompany.app.config;

import java.util.ArrayList;
import com.mycompany.app.WebScraper;

public class WebScraperManager {

	private ArrayList<WebScraper> webScrapers;

	public void initializeScraperManager() {
		for (WebScraper scraper : webScrapers) {
			scraper.start();
		}
	}

	public ArrayList<WebScraper> getScrapers() {
		return webScrapers;
	}

	public void setScrapers(ArrayList<WebScraper> webScrapers) {
		this.webScrapers = webScrapers;
	}

}
