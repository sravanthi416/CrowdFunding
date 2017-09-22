package com.alacriti.crowdFunding.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.util.SessionUtility;
@Path("session")
public class SessionResource {
	
	
		public static final Logger log= Logger.getLogger(SessionResource.class);
		@GET
		@Path("/checkSession")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean checkSessoin(@Context HttpServletRequest request)
		{
		SessionUtility sessionUtility=new SessionUtility();
		HttpSession session= request.getSession(false);
		System.out.println("session in checkSession :"+session.getId());
		return sessionUtility.checkForSession(request);
		}
		

		@GET
		@Path("/sessionDestroy")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean sessoinDestroy(@Context HttpServletRequest request)
		{
			boolean result=false;
			SessionUtility sessionUtility=null;
			HttpSession session=null;
			try{
			sessionUtility=new SessionUtility();
			session= request.getSession(false);
			System.out.println("session in checkSession :"+session.getId());
			session.invalidate();
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
			System.out.println("Exception occured in destroying session");
		}
		return result;
		}
		

}
