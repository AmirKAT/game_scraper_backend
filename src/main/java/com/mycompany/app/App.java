package com.mycompany.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.app.config.AppBean;
import com.mycompany.app.config.WebScraperManager;

public class App {

	public static void main(String[] args) {

		// below configures the bean class
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppBean.class);

		// injecting the dependencies to the scraping manager
		WebScraperManager webScraperManager = (WebScraperManager) applicationContext.getBean("webScraperManager");

		// starts all scrapers
		webScraperManager.initializeScraperManager();

	}
}
