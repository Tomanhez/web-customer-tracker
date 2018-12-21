package pl.tomek.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Generator {

	public String BodyHtml(String title,String head,String body) {
		String html = ("<html>"+
							"<head>"+
								"<title>"+title+"</title>"+
								head+
							"</head>"+
							"<body>"+
								body+
							"</body>"+
						"</html> ");
		return html;
	}
	
	public String Gen() throws IOException {
		//StringBuilder mysite = new StringBuilder();
		URL yahoo = new URL("https://yahoo.com/");
		BufferedReader in = new BufferedReader(
		            new InputStreamReader(
		            yahoo.openStream()));

		//String inputLine;

		//while ((inputLine = in.readLine()) != null)
		//    mysite.append(inputLine);

		String site = in.toString();
		in.close();

		return site;
	}

}
