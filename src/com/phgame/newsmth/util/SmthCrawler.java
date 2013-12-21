package com.phgame.newsmth.util;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

import com.phgame.newsmth.data.PostDetailBean;
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
	
	public boolean getUrlLists(String url,Vector<PostDetailBean> lists,StringBuffer forumname,StringBuffer subjectname){
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(userAgent).timeout(5000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		String title = doc.title();
		
		System.out.println(doc.body().text());
		
		Elements titles = doc.select("a");
		

		
		Element em = doc.getElementById("wraper");
		Elements ems = em.children();
		
		Element eforum = ems.get(1);
		forumname.append(eforum.text());
		
		Element emain = ems.get(2);
		Elements emsmain = emain.getElementsByClass("list");
		
		subjectname.append(emsmain.get(0).text());
		
		Elements emsparse = emsmain.tagName("li");
		
		ListIterator<Element> mainlist = emsparse.listIterator();
		while(mainlist.hasNext()){
			em = mainlist.next();
//			em.traverse(new NodeVisitor(){
//
//				@Override
//				public void head(Node node, int depth) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void tail(Node node, int depth) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//			});
			ems = em.getElementsByClass("nav").get(0).getElementsByTag("a");
			PostDetailBean bean = new PostDetailBean();
			bean.level = ems.get(0).text();
			bean.auther = ems.get(1).text();
			bean.time = ems.get(2).text();
			
			bean.detail = em.getElementsByClass("sp").get(0).text();
			lists.add(bean);
		}
		
		
		
		
	/*	
		Elements ems = doc.getElementsByClass("menu");
		//ems.get
		ListIterator<Element> itor = ems.listIterator();
		while(itor.hasNext()){
			Element e = itor.next();
			System.out.println(e.text());
			String tmp = e.text();
			if(tmp.contains("°æÃæ"))
			forumname.append(tmp);
		}
		
		ems = doc.getElementsByClass("f");
		
		itor = ems.listIterator();
		while(itor.hasNext()){
			Element e = itor.next();
			System.out.println(e.text());
			String tmp = e.text();
			if(tmp.contains("Ö÷Ìâ"))
				subjectname.append(tmp);
		}
		
		
		ems = doc.getElementsByClass("nav");
		
		itor = ems.listIterator();
		while(itor.hasNext()){
			Element e = itor.next();
			//Elements h1_ems = e.getElementsByClass("h1");
			
			
			System.out.println(e.text());
			//subjectname.append(e.text());
		}
		
		
		
		for(Element elm : titles ){
			System.out.println(elm.text());
			System.out.println(elm.attr("href"));
			TieZiBean tiezi = new TieZiBean();
			tiezi.setTitle(elm.text());
			tiezi.setContentUrl(elm.attr("href"));
			
			lists.add(bean);		
		}
		
		*/
		return true;
			
		
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
