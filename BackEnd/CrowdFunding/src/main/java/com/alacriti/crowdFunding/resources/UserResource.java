package com.alacriti.crowdFunding.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.bo.impl.MyCampaignsBO;
import com.alacriti.crowdFunding.delegate.MyCampaignsDelegate;
import com.alacriti.crowdFunding.delegate.UserLoginDelegate;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.model.vo.UserLoginVO;

@Path("user")
public class UserResource {
	public static final Logger log =Logger.getLogger(UserResource.class);
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean userLogin(UserLoginVO userLoginVO,@Context HttpServletRequest request)
	{
		boolean result=false;
		System.out.println("User Resource in before block");
		try{
			System.out.println("User Resource in try block");
			HttpSession session = request.getSession(true);
			System.out.println("session in user resourece"+session.getId());
			UserLoginDelegate userLoginDelegate=new UserLoginDelegate();
			result=userLoginDelegate.userLogin(userLoginVO);
			session.setAttribute("id", userLoginVO.getId());
			System.out.println("Id is "+session.getAttribute("id"));
			System.out.println("After getteing tthe resut ");
		}
		catch(Exception e)
		{
			System.out.println("In user resource exception");
			e.printStackTrace();
		}
		return result;
		
	}
	@GET
	@Path("myCampaigns")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NewCampaignVO> MyCampaigns(@Context HttpServletRequest request)
	{
		List<NewCampaignVO> myCampaigns = null;
		MyCampaignsDelegate myCampaignsDelegate =null;
		int userId=0;
		try{
			HttpSession session = request.getSession(false);
			userId=(Integer) session.getAttribute("id");
			myCampaignsDelegate =new MyCampaignsDelegate();
			myCampaigns =  new ArrayList<NewCampaignVO>();
			myCampaigns=myCampaignsDelegate.getMyCampaigns(userId);
		}
		catch(Exception e)
		{
			log.error("Error ocuured in Resource class of myCampaigns");
		}
		return myCampaigns;
	}
	
	
	@GET
	@Path("myPayments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DonationModelVO> MyPayments(@Context HttpServletRequest request)
	{
		List<DonationModelVO> myCampaigns = null;
		MyCampaignsDelegate myCampaignsDelegate =null;
		int userId=0;
		try{
			HttpSession session = request.getSession(false);
			userId=(Integer) session.getAttribute("id");
			myCampaignsDelegate =new MyCampaignsDelegate();
			myCampaigns =  new ArrayList<DonationModelVO>();
			myCampaigns=myCampaignsDelegate.getMyPayments(userId);
		}
		catch(Exception e)
		{
			log.error("Error ocuured in Resource class of myCampaigns");
		}
		return myCampaigns;
	}
	
	
	
	
	
	

}
