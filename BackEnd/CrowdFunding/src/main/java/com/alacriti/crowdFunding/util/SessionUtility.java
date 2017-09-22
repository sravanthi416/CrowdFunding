package com.alacriti.crowdFunding.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtility {
	public boolean checkForSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		System.out.println("printing the status of session");
		
		if (session == null)
			return false;
		else
			return true;
	}
}
