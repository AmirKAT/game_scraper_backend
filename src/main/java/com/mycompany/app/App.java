package com.mycompany.app;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Document doc = Jsoup.connect("https://www.imdb.com/chart/top/")
        		.timeout(6000).get();
        Elements body = doc.select("tbody.lister-list");
        System.out.println(body.select("tr").size());
    }
}
