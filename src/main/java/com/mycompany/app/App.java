package com.mycompany.app;



public class App {
	
    public static void main( String[] args ) {
    	try {
//    		EnebaScraper enebaScraper = new EnebaScraper();
//				enebaScraper.start();
				MmogaScraper mmogaScraper = new MmogaScraper();
				mmogaScraper.start();
			}
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}	
    }
	}
