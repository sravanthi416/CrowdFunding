package com.alacriti.crowdFunding.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.bo.impl.BOException;
import com.alacriti.crowdFunding.bo.impl.CampaignListBO;
import com.alacriti.crowdFunding.bo.impl.NewCampaignBO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.model.vo.PaginationValues;

public class CampaignListDelegate extends BaseDelegate{
	public static final Logger log= Logger.getLogger(CampaignListDelegate.class);
	
	public CampaignListDelegate(){
		
	}
	public List<NewCampaignVO> camapignList(PaginationValues paginationValues) 
	{
		List<NewCampaignVO> campaigns=new ArrayList<NewCampaignVO>();
		CampaignListBO campaignListBO=null;
		boolean rollBack = false;
		Connection connection=null;
		try{
			System.out.println("Bfor connection");
			connection = startDBTransaction();
			System.out.println("after connection"+connection);
			setConnection(connection);
			campaignListBO=new CampaignListBO(getConnection());
			campaigns=campaignListBO.campaignList(paginationValues);
		}
		catch(BOException e)
		{
			log.error("Error in BOEXception");
			rollBack=true;
		}
		catch(Exception e)
		{
			log.error("Exception in camapignlist delegate");
		}
		finally{
			endDBTransaction(connection, rollBack);
		}
		return campaigns;
	}
	public int getCampaignCount()
	{
		int count=0;
		boolean rollBack=false;
		Connection connection=null;
		CampaignListBO campaignListBO=null;
		try{
			System.out.println("Bfor connection");
			connection = startDBTransaction();
			System.out.println("after connection"+connection);
			setConnection(connection);
			campaignListBO=new CampaignListBO(getConnection());
			count=campaignListBO.getCampaignCount();	
		}
		catch(BOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			endDBTransaction(connection, rollBack);
		}
		return count;
	}
	
	public List<NewCampaignVO> campaignDetails(int campId) 
	{
		List<NewCampaignVO> campaigns=new ArrayList<NewCampaignVO>();
		CampaignListBO campaignListBO=null;
		boolean rollBack = false;
		Connection connection=null;
		try{
			System.out.println("Bfor connection");
			connection = startDBTransaction();
			System.out.println("after connection"+connection);
			setConnection(connection);
			campaignListBO=new CampaignListBO(getConnection());
			campaigns=campaignListBO.campaignDetails(campId);
		}
		catch(BOException e)
		{
			log.error("Error in BOEXception");
			rollBack=true;
		}
		catch(Exception e)
		{
			log.error("Exception in camapignlist delegate");
		}
		finally{
			endDBTransaction(connection, rollBack);
		}
		return campaigns;
	}
	
	
	public List<NewCampaignVO> getCampaignCategories(PaginationValues pageValues) 
	{
		List<NewCampaignVO> campaigns=new ArrayList<NewCampaignVO>();
		CampaignListBO campaignListBO=null;
		boolean rollBack = false;
		Connection connection=null;
		try{
			System.out.println("Bfor connection");
			connection = startDBTransaction();
			System.out.println("after connection"+connection);
			setConnection(connection);
			campaignListBO=new CampaignListBO(getConnection());
			campaigns=campaignListBO.getCampaignCategories(pageValues);
		}
		catch(BOException e)
		{
			log.error("Error in BOEXception");
			rollBack=true;
		}
		catch(Exception e)
		{
			log.error("Exception in camapignlist delegate");
		}
		finally{
			endDBTransaction(connection, rollBack);
		}
		return campaigns;
	}
	public int getCampaignCategoriesCount(int categoryId) {
		int count=0;
		boolean rollBack=false;
		Connection connection=null;
		CampaignListBO campaignListBO=null;
		try{
			System.out.println("Bfor connection");
			connection = startDBTransaction();
			System.out.println("after connection"+connection);
			setConnection(connection);
			campaignListBO=new CampaignListBO(getConnection());
			count=campaignListBO.getCampaignCategoriesCount(categoryId);	
		}
		catch(BOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			endDBTransaction(connection, rollBack);
		}
		return count;
	}
	
	

}
