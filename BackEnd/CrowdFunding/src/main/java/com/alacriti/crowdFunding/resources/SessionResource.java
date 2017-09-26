package com.alacriti.crowdFunding.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.util.SessionUtil;
@Path("session")
public class SessionResource {
	
	
		public static final Logger log= Logger.getLogger(SessionResource.class);
		@GET
		@Path("/checkSession")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean checkSessoin(@Context HttpServletRequest request)
		{
		SessionUtil sessionUtil=new SessionUtil();
		HttpSession session= request.getSession(false);
		log.debug("session in checkSession :"+session.getId());
		boolean flag = sessionUtil.checkForSession(request);
				log.info("session here >>>>>>>" + flag );
				return flag;
		}
		

		@GET
		@Path("/sessionDestroy")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean sessoinDestroy(@Context HttpServletRequest request)
		{
			boolean result=false;
			SessionUtil sessionUtil=null;
			HttpSession session=null;
			try{
			sessionUtil=new SessionUtil();
			session= request.getSession(false);
			log.debug("session in checkSession :"+session.getId());
			session.invalidate();
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
			log.error("Exception occured in destroying session");
		}
		return result;
		}
		

}
