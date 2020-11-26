package com.mycompany.app;



public class App {
	
    public static void main( String[] args ) {
    	try {
    		EnebaScraper enebaScraper = new EnebaScraper();
				enebaScraper.start();
//				AmazonScraper amazonScraper = new AmazonScraper();
//				amazonScraper.start();
			}
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}	
    }
	}
