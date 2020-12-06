package com.mycompany.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.app.config.AppBean;

public class App {

	public static void main(String[] args) {
		/**
		 * here it will configure bean class
		 */
		ApplicationContext context = new AnnotationConfigApplicationContext(AppBean.class);
		try {
			EnebaScraper enebaScraper = (EnebaScraper) context.getBean("enebaScraper");
			enebaScraper.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			MmogaScraper mmogaScraper = (MmogaScraper) context.getBean("mmogaScraper");
			mmogaScraper.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			GamingDragonScraper gamingDragonScraper = (GamingDragonScraper) context.getBean("gamingDragonScraper");
			gamingDragonScraper.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			AmazonGamesScraper amazonGamesScraper = (AmazonGamesScraper) context.getBean("amazonGamesScraper");
			amazonGamesScraper.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			ZatuGamesScraper zatuGamesScraper = new ZatuGamesScraper();
			zatuGamesScraper.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
