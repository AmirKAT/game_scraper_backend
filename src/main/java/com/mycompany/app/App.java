package com.mycompany.app;

public class App {
	
    public static void main( String[] args ) {
//    	try {
//    		EnebaScraper enebaScraper = new EnebaScraper();
//				enebaScraper.start();
//			}
//    	catch(Exception ex) {
//    		ex.printStackTrace();
//    	}
//    	
//    	try {
//				MmogaScraper mmogaScraper = new MmogaScraper();
//				mmogaScraper.start();
//			}
//    	catch(Exception ex) {
//    		ex.printStackTrace();
//    	}
//
    	try {
				GamingDragonScraper gamingDragonScraper = new GamingDragonScraper();
				gamingDragonScraper.start();
			}
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}
//    	
//    	try {
//				AmazonGamesScraper AmazonGamesScraper = new AmazonGamesScraper();
//				AmazonGamesScraper.start();
//			}
//    	catch(Exception ex) {
//    		ex.printStackTrace();
//    	}
    }
	}
