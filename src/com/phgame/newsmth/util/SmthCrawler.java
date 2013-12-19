package com.phgame.newsmth.util;

import java.io.IOException;
import java.util.Vector;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.phgame.newsmth.data.TieZiBean;

public class SmthCrawler {

    public static String smthEncoding = "GBK";
    public static String mobileSMTHEncoding = "UTF-8";
    public static String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.4) Gecko/20091016 Firefox/3.5.4";
    
    
	private static SmthCrawler crawlerInstance;
	public static SmthCrawler getInstance(){
			if(crawlerInstance == null){
				return new SmthCrawler();			
			}
			return crawlerInstance;
		
	}
	
	public boolean getTopTenLists(Vector<TieZiBean> vector){
		
		Document doc = null;
		try {
			doc = Jsoup.connect("http://m.newsmth.net/").userAgent(userAgent).timeout(5000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		String title = doc.title();
		
		System.out.println(doc.body().text());
		Elements titles = doc.select("a");
		
		for(Element elm : titles ){
			System.out.println(elm.text());
			System.out.println(elm.attr("href"));
			TieZiBean tiezi = new TieZiBean();
			tiezi.setTitle(elm.text());
			tiezi.setContentUrl(elm.attr("href"));
			
			vector.add(tiezi);		
		}
		
		
		return true;
	}
	
}
