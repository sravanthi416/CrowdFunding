package com.alacriti.crowdFunding.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionUtil {
	public static final Logger log = Logger.getLogger(SessionUtil.class);
	public boolean checkForSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		log.debug("printing the status of session");
		System.out.println("session looks like : ###########" + session);
		if (session == null)
			return false;
		else
			return true;
	}
}
