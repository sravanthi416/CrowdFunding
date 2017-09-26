package com.alacriti.crowdFunding.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.alacriti.crowdFunding.delegate.CampaignListDelegate;
import com.alacriti.crowdFunding.delegate.DonationDelegate;
import com.alacriti.crowdFunding.delegate.NewCampaignDelegate;
import com.alacriti.crowdFunding.delegate.SearchDelegate;
import com.alacriti.crowdFunding.model.vo.CampaignMultipartDataVO;
import com.alacriti.crowdFunding.model.vo.CategoriesModelVO;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.model.vo.PaginationValues;
import com.alacriti.crowdFunding.model.vo.SearchModelVO;


@Path("campaign")
public class CampaignResource {
	
public static final Logger log = Logger.getLogger(CampaignResource.class);

	@POST
	@Path("newCampaign")
	@Consumes("multipart/form-data")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean newCampaign(@MultipartForm CampaignMultipartDataVO campaignMultipartDataVO,@Context HttpServletRequest request)
	{
		boolean result=false;
		NewCampaignDelegate newCampaignDelegate=null;
		int id=0;
		String email=null;
		try{
			System.out.println("In camapign Resource");
			HttpSession session= request.getSession(false);
			System.out.println("*********ESSION IS "+session.getId());
			id=(Integer) session.getAttribute("id");
			log.debug("ID is "+id);
			email=(String) session.getAttribute("email");
			newCampaignDelegate=new NewCampaignDelegate();
			log.info("In camapign Resource const");
			result=newCampaignDelegate.newCampaign(campaignMultipartDataVO,id,email);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	@POST
	@Path("campaignsList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response campaignList(PaginationValues paginationValues,@Context HttpServletRequest request)
	{
		NewCampaignVO newCampaignVO=null;
		CampaignListDelegate campaignListDelegate =null;
		List<NewCampaignVO> campaigns = new ArrayList<NewCampaignVO>();
		int id=0;
		try{
			newCampaignVO=new NewCampaignVO();
			//HttpSession session= request.getSession(false);
			//log.debug("SESJIOSHF in Camaping list resource is"+session.getId());
			
			System.out.println("CAme to Resource classs");
			campaignListDelegate=new CampaignListDelegate();
			campaigns=campaignListDelegate.camapignList(paginationValues);
			//}
			for(NewCampaignVO list:campaigns)
			{
				log.debug("Resources is"+list);
			}
			return Response.ok().entity(campaigns).build() ;
		}
		catch(Exception e)
		{
			log.error("Error in campaigns list resource");
			return Response.status(204).entity("No Campaigns Found").build();
		}
		
	}
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public int getCampaignCount(@Context HttpServletRequest request)
	{
		CampaignListDelegate campaignListDelegate=null;
		int count=0;
		try{
			HttpSession session= request.getSession(false);
			log.debug("********SESsion  is*********** in count"+session);
			campaignListDelegate =new CampaignListDelegate();
			count=campaignListDelegate.getCampaignCount();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	@GET
	@Path("campaignDetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NewCampaignVO> campaignDetails(@PathParam("id") int campId,@Context HttpServletRequest request)
	{
		CampaignListDelegate campaignListDelegate=null;
		List<NewCampaignVO> campaignDetails = new ArrayList<NewCampaignVO>();
		System.out.println("campde");
		try{
			HttpSession session= request.getSession(false);
			log.debug("********SESsion  is*********** in count"+session);
			campaignListDelegate =new CampaignListDelegate();
			campaignDetails=campaignListDelegate.campaignDetails(campId);
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return campaignDetails;
	
	}
	
	@POST
	@Path("donation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String donation(DonationModelVO donModelVO,@Context HttpServletRequest request)
	{
		String result=null;
		boolean output=false;
		DonationDelegate donationDelegate=null;
		int id=0;
		try{
		HttpSession session= request.getSession(false);
		log.debug("********SESsion  is*********** in count"+session);	
		id=(Integer) session.getAttribute("id");
		log.debug("ID is "+id);
		donationDelegate = new DonationDelegate();
		output=donationDelegate.Donation(donModelVO, id);
		if(output==true)
		{
			result="payment is successfull";
		}
		else{
			result="payment is unsuccessfull";
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		log.info("Result in donation is  "+result);
		return result;
		
	}
	
	@GET
	@Path("/supporters/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DonationModelVO> supporters(@PathParam("id") int campaignId,@Context HttpServletRequest request){
		List<DonationModelVO> supporters=null;
		DonationDelegate donationDelegate=null;
		try
		{
			HttpSession session= request.getSession(false);
			log.debug("********SESsion  is*********** in count"+session.getId());
			donationDelegate =new DonationDelegate();
			supporters = donationDelegate.supporters(campaignId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return supporters;
	}
	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriesModelVO> getCategories()
	{
		List<CategoriesModelVO> categories =null;
		NewCampaignDelegate newCampaignDelegate = null;
		try{
			categories=new ArrayList<CategoriesModelVO>();
			newCampaignDelegate = new NewCampaignDelegate();
			categories=newCampaignDelegate.getCategories();
		}
		catch(Exception e)
		{
			log.error("Error in resource of get categories");
		}
		return categories;
	}
	
	@POST
	@Path("/campaigncategories")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<NewCampaignVO> getCampaignCategories(PaginationValues paginationVal,@Context HttpServletRequest request)
	{
		
		CampaignListDelegate campaignListDelegate =null;
		List<NewCampaignVO> campaigns = new ArrayList<NewCampaignVO>();
		int id=0;
		try{
			//HttpSession session= request.getSession(false);
			//log.debug("SESJIOSHF in Camaping list resource is"+session.getId());
			
			log.debug("CAme to Resource classs");
			campaignListDelegate=new CampaignListDelegate();
			campaigns=campaignListDelegate.getCampaignCategories(paginationVal);
			//}
			for(NewCampaignVO list:campaigns)
			{
				log.debug("Resources is"+list);
			}
		}
		catch(Exception e)
		{
			log.error("Error in campaigns list resource");
		}
		return campaigns;
	}
	@GET
	@Path("/countCatg/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCampaignCategoriesCount(@PathParam("id") int campaignId,@Context HttpServletRequest request)
	{
		CampaignListDelegate campaignListDelegate=null;
		int count=0;
		try{
			HttpSession session= request.getSession(false);
			log.debug("********SESsion  is*********** in count"+session);
			campaignListDelegate =new CampaignListDelegate();
			count=campaignListDelegate.getCampaignCategoriesCount(campaignId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	
	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchCampaigns(SearchModelVO searchMVO)
	{
		List<NewCampaignVO> results=null;
		SearchDelegate searchDelegate=null;
		try{
			results=new ArrayList<NewCampaignVO>();
			searchDelegate = new SearchDelegate();
			results=searchDelegate.searchCampaigns(searchMVO);
			if(results.size()>0)
				return Response.ok().entity(results).build() ;
			else
				return Response.status(204).entity("No results found").build() ;
			
		}
		catch(Exception e)
		{
			log.error("Error occured in resource class of search ");
			return Response.status(400).entity("Bad request ").build() ;
			
		}
		
	}
	
	
	
	
}
