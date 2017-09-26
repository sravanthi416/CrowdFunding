package com.alacriti.crowdFunding.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.dao.impl.CampaignListDAO;
import com.alacriti.crowdFunding.dao.impl.DAOException;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.model.vo.PaginationValues;

public class CampaignListBO extends BaseBO {
	public static final Logger log= Logger.getLogger(CampaignListBO.class);

	public CampaignListBO(){
		
	}
	public CampaignListBO(Connection conn)
	{
		super(conn);
	}
	public List<NewCampaignVO> campaignList(PaginationValues paginationValues) throws BOException
	{
		List<NewCampaignVO> campaigns=new ArrayList<NewCampaignVO>();
		List<NewCampaignVO> result=new ArrayList<NewCampaignVO>();
		CampaignListDAO campaignListDAO =null;
		int start=0;
		int end=0;

		try{
			start=paginationValues.getStart();
			end=paginationValues.getEnd();
			campaignListDAO =new CampaignListDAO(getConnection());
			campaigns=campaignListDAO.campaignList();
			if(start<campaigns.size()){
				if(end > campaigns.size()){
						result=campaigns.subList(start, campaigns.size());
				}
				else{
					result=campaigns.subList(start, end);
				}
			}			
			System.out.println("Result set in BO "+result);
			
			
		}
		catch(DAOException e)
		{
			log.error("error in Campaign List DAO");
			throw new BOException("Error in BO class"+e.getMessage());
		}
		catch(Exception e)
		{
			log.error("error in exception");
		}
		
		return result;
		}
	
	
	public int getCampaignCount() throws BOException
	{
		int count=0;
		CampaignListDAO campaignListDAO=null;
		try{
			campaignListDAO = new CampaignListDAO(getConnection());
			count=campaignListDAO.getCampaignCount();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException("Error occured in getcampaig count");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	public List<NewCampaignVO> campaignDetails(int campId)throws BOException
	{
		List<NewCampaignVO> campaignDetails=null;
		CampaignListDAO campaignListDAO=null;
		try{
			campaignDetails=new ArrayList<NewCampaignVO>();
			campaignListDAO = new CampaignListDAO(getConnection());
			campaignDetails=campaignListDAO.campaignDetails(campId);
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException("error occured in campaign details");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return campaignDetails;
	}
	
	public List<NewCampaignVO> getCampaignCategories(PaginationValues pageValues) throws BOException
	{
		List<NewCampaignVO> campaigns=new ArrayList<NewCampaignVO>();
		CampaignListDAO campaignListDAO =null;
		int categoryId=0;
		int start=0;
		int end=0;
		List<NewCampaignVO> result=new ArrayList<NewCampaignVO>();
		try{
			
			campaignListDAO =new CampaignListDAO(getConnection());
			categoryId=pageValues.getCategoryId();
			campaigns=campaignListDAO.getCampaignCategories(categoryId);
			log.debug("eNd is "+pageValues.getEnd());
			log.debug("eNd is "+pageValues.getStart());
			start=pageValues.getStart();
			end=pageValues.getEnd();
			
			for(NewCampaignVO list:campaigns)
			{
				log.debug("List is values******"+list);
			}
			
			
			if(start<campaigns.size()){
				log.debug("List in if");
				if(end > campaigns.size()){
						result=campaigns.subList(start, campaigns.size());
				}
				else{
					result=campaigns.subList(start, end);
				}
			}			
	
						
		}
		catch(DAOException e)
		{
			log.error("error in Campaign List DAO");
			throw new BOException("Error in BO class"+e.getMessage());
		}
		catch(Exception e)
		{
			log.error("error in exception");
		}
		
		return result;
		}
	public int getCampaignCategoriesCount(int categoryId) throws BOException{
		int count=0;
		CampaignListDAO campaignListDAO=null;
		try{
			campaignListDAO = new CampaignListDAO(getConnection());
			count=campaignListDAO.getCampaignCountCategories(categoryId);
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException("Error occured in getcampaig count");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	
	

}
