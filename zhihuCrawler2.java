package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;


public class zhihuCrawler2 extends DeepCrawler{ 
	AtomicInteger id=new AtomicInteger(1);
	public static FileOutputStream fos = null;
	 public static  BufferedWriter bw = null;
//	RegexRule regexRule = new RegexRule();
	public zhihuCrawler2(String crawlPath) {
		
		super(crawlPath);
	//	regexRule.addRule("http://.*zhihu.com/question/[0-9]+");
		// TODO Auto-generated constructor stub
	}
	

	public Links visitAndGetNextLinks(Page page) {
		// TODO Auto-generated method stub
//		String question_regex="http://www.zhihu.com/question/[0-9]+"; 

		System.out.println("正在抽取"+page.getUrl()); /*抽取标题*/ 
		for(int i = 1;i <=20;i++){
			Elements elem = page.getDoc().select("#zh-topic-questions-list > div:nth-child("+i+") > h2 > a");
			String question = elem.text();
			String url = elem.attr("href");
			String clas = page.getDoc().select("#zh-topic-questions-list > div:nth-child("+i+") > div.subtopic > a").text();
			System.out.println(question + ",http://www.zhihu.com"+url+","+clas);
			 try {
					bw.write(question + ",http://www.zhihu.com"+url+","+clas+"\n");
					bw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	//		System.out.println(question); 
		}
		Links link = new Links();
		link.set(1, "http://www.zhihu.com/topic/19553298/questions?page="+id.incrementAndGet());
		return link;
	}

	
	public static void main(String[] args) throws Exception{ 
		zhihuCrawler2 crawler=new zhihuCrawler2("weiyixiang"); 
		crawler.setThreads(3);
	//	Proxys proxys = new Proxys();
	//	proxys.addAllFromFile(new File("IP.txt"));
	//	crawler.setProxys(proxys);
		 File file = new File("Url.txt");
		 fos = new FileOutputStream(file);
  		 bw = new BufferedWriter(new OutputStreamWriter(fos));

			crawler.addSeed("http://www.zhihu.com/topic/19553298/questions?page=1");

//		crawler.addRegex("http://www.zhihu.com/.*"); 

	     
		crawler.start(5);
	}
	}