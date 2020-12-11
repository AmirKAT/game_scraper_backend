package com.mycompany.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.app.config.AppBean;
import com.mycompany.app.config.WebScraperManager;

public class App {

	public static void main(String[] args) {

		// spring annotations configured from AppBean file
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppBean.class);

		// injecting the dependencies to webScraperManager
		WebScraperManager webScraperManager = (WebScraperManager) applicationContext.getBean("webScraperManager");

		// starts all scrapers
		webScraperManager.startWebScraperManager();

	}
}
