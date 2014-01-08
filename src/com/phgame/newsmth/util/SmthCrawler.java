package com.phgame.newsmth.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
	
	private static DefaultHttpClient httpClient;
	public static SmthCrawler getInstance(){
			if(crawlerInstance == null){
				return new SmthCrawler();			
			}
			return crawlerInstance;
		
	}
	public SmthCrawler(){
		httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true);
	
		
	}
	
	public  int login(String username,String password){
		String url ="http://m.newsmth.net/user/login";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("id", username));
		formparams.add(new BasicNameValuePair("passwd", password));
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formparams, "GBK");
		} catch (UnsupportedEncodingException e1) {
			return -1;
		}
		httpPost.setEntity(entity);
		httpPost.setHeader("User-Agent", userAgent);
		try {
			
			HttpResponse response =  httpClient.execute(httpPost);
			System.out.println(response.getStatusLine());
			
			HttpEntity e = response.getEntity();
			String content = EntityUtils.toString(e, smthEncoding);
			System.out.println(content);
			
			if (content.contains("ￄ￣ﾵￇￂﾼﾵￄﾴﾰ﾿ￚﾹ�ﾶ￠")) {
				formparams.add(new BasicNameValuePair("kick_multi", "1"));
				UrlEncodedFormEntity entity2;
				entity2 = new UrlEncodedFormEntity(formparams, "GBK");
				httpPost = new HttpPost(
						"http://www.newsmth.net/bbslogin.php?mainurl=");
				httpPost.setHeader("User-Agent", userAgent);
				httpPost.setEntity(entity2);
				httpClient.execute(httpPost);
			} else if (content.contains("ￄ￺ﾵￄￓￃﾻﾧￃ￻ﾲﾢﾲﾻﾴ￦ￔￚﾣﾬﾻ￲ￕ￟ￄ￺ﾵￄￃￜￂ￫ﾴ￭ￎ￳")) {
				return 0;
			} else if (content.contains("ￓￃﾻﾧￃￜￂ￫ﾴ￭ￎ￳")) {
				return 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
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
		//ems = emain.select("a[href]");
				
		
		Elements emsmain = emain.getElementsByClass("list");
		
		
		ems = emsmain.get(0).getElementsByTag("li");
		
		

		
		//Elements emsparse = emsmain.tagName("li");
		//emain = emsmain.get(index)
		
		
		ListIterator<Element> mainlist = ems.listIterator();
		if(mainlist.hasNext()){
			
			em = mainlist.next();
			subjectname.append(em.text());
		}
		
		
		while(mainlist.hasNext()){
			em = mainlist.next();
			ems = em.getElementsByClass("nav").get(0).getElementsByTag("a");
			
			PostDetailBean bean = new PostDetailBean();
			bean.level = ems.get(0).text();
			bean.auther = ems.get(1).text();
			bean.time = ems.get(2).text();
			
			bean.detail = em.getElementsByClass("sp").get(0).html();//.text();
			
		//	em.getElementsByClass("sp").get(0).
			
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
			if(tmp.contains("ﾰ￦ￃ￦"))
			forumname.append(tmp);
		}
		
		ems = doc.getElementsByClass("f");
		
		itor = ems.listIterator();
		while(itor.hasNext()){
			Element e = itor.next();
			System.out.println(e.text());
			String tmp = e.text();
			if(tmp.contains("ￖ￷ￌ￢"))
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
