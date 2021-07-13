package com.app.developers.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component

public class AppProperties {

	@Autowired
	private Environment evn;
	
	public String getTokenSecret() {
		
		return evn.getProperty("tokenSecret");
	}
}
