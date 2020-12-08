package com.mycompany.app.config;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycompany.app.AmazonGamesScraper;
import com.mycompany.app.EnebaScraper;
import com.mycompany.app.GamingDragonScraper;
import com.mycompany.app.MmogaScraper;
import com.mycompany.app.WebScraper;
import com.mycompany.app.ZatuGamesScraper;
import com.mycompany.app.dao.ProductDao;

@Configuration
public class AppBean {
	SessionFactory sessionFactory = null;

//below creates a session factory instance, if one has already been created it will return same object
	@Bean
	public SessionFactory sessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
			standardServiceRegistryBuilder.configure("hibernate.cfg.xml");
			StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

//below returns product Dao
	@Bean
	public ProductDao productDao() {
		ProductDao productDao = new ProductDao();
		productDao.setSessionFactory(this.sessionFactory());
		return productDao;
	}

	@Bean
	public WebScraperManager webScraperManager() {
		WebScraperManager webScraperManager = new WebScraperManager();

		ArrayList<WebScraper> webScrapers = new ArrayList<>();
		
		webScrapers.add(enebaScraper());
		webScrapers.add(gamingDragonScraper());
		webScrapers.add(mmogaScraper());
		webScrapers.add(amazonGamesScraper());
		webScrapers.add(zatuGamesScraper());

		webScraperManager.setScrapers(webScrapers);

		return webScraperManager;
	}

	@Bean
	public EnebaScraper enebaScraper() {
		EnebaScraper enebaScraper = new EnebaScraper();
		enebaScraper.setProductDao(this.productDao());
		return enebaScraper;
	}

	@Bean
	public MmogaScraper mmogaScraper() {
		MmogaScraper mmogaScraper = new MmogaScraper();
		mmogaScraper.setProductDao(this.productDao());
		return mmogaScraper;
	}

	@Bean
	public GamingDragonScraper gamingDragonScraper() {
		GamingDragonScraper gamingDragonScraper = new GamingDragonScraper();
		gamingDragonScraper.setProductDao(this.productDao());
		return gamingDragonScraper;
	}

	@Bean
	public AmazonGamesScraper amazonGamesScraper() {
		AmazonGamesScraper amazonGamesScraper = new AmazonGamesScraper();
		amazonGamesScraper.setProductDao(this.productDao());
		return amazonGamesScraper;
	}

	@Bean
	public ZatuGamesScraper zatuGamesScraper() {
		ZatuGamesScraper zatuGamesScraper = new ZatuGamesScraper();
		zatuGamesScraper.setProductDao(this.productDao());
		return zatuGamesScraper;
	}
}
