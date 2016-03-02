package test1;

import java.util.concurrent.atomic.AtomicInteger;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;


public class zhihuweiCrawer extends DeepCrawler{ 
  //  AtomicInteger id=new AtomicInteger(0);

//	RegexRule regexRule = new RegexRule();
	public zhihuweiCrawer(String crawlPath) {
		
		super(crawlPath);
	//	regexRule.addRule("http://.*zhihu.com/question/[0-9]+");
		// TODO Auto-generated constructor stub
	}
	

	public Links visitAndGetNextLinks(Page page) {
		// TODO Auto-generated method stub
//		String question_regex="http://www.zhihu.com/question/[0-9]+"; 

		System.out.println("正在抽取"+page.getUrl()); /*抽取标题*/ 
		for(int i = 1;i <=10;i++){
			String action = page.getDoc().select("#zh-profile-activity-page-list > div:nth-child("+i+") > "
					+ "div.zm-profile-section-main.zm-profile-section-activity-main."
					+ "zm-profile-activity-page-item-main").text();
			System.out.println(action+"\n"); 
			String question = page.getDoc().select("#zh-profile-activity-page-list > div:nth-child("+i+") > "
					+ "div.zm-profile-section-main.zm-profile-section-activity-main."
					+ "zm-profile-activity-page-item-main > a.question_link").text();
	//		System.out.println(question); 
		}
	//		Links link = new Links();
	//		link.addAllFromDocument(page.getDoc(), regexRule);
			
	//		return link;
		return null;
	}

	
	public static void main(String[] args) throws Exception{ 
		zhihuweiCrawer crawler=new zhihuweiCrawer("weiyixiang"); 
		crawler.addSeed("http://www.zhihu.com/people/wei-yi-xiang-64");
//		crawler.addRegex("http://www.zhihu.com/.*"); 

	     
		crawler.start(1);
	}
	}