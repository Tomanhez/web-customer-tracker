package pl.tomek.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainDemo {
	
	@RequestMapping("/")
	@ResponseBody
	public String indexSite() {
		Generator html = new Generator();
		return html.BodyHtml("testowa", "", "Welcome to my site");
	}
	
}
