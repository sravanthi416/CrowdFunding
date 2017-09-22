package com.alacriti.crowdFunding.bo.impl;



import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.dao.impl.DAOException;
import com.alacriti.crowdFunding.dao.impl.MyCampaignsDAO;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;

public class MyCampaignsBO extends BaseBO{
	public static final Logger log=Logger.getLogger(MyCampaignsBO.class);
	public MyCampaignsBO(){}
	public MyCampaignsBO(Connection conn)
	{
		super(conn);
	}
	
	public List<NewCampaignVO> getMyCampaigns(int userId)throws BOException
	{

		List<NewCampaignVO> campaigns=new ArrayList<NewCampaignVO>();
		MyCampaignsDAO myCampaignsDAO=null;
		
		try{
			
			myCampaignsDAO =new MyCampaignsDAO(getConnection());
			campaigns=myCampaignsDAO.getMyCampaignList(userId);
				
			
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
		
		return campaigns;
	}
	
	
	public List<DonationModelVO> getMyPayments(int userId)throws BOException
	{

		List<DonationModelVO> payments=null;
		MyCampaignsDAO myCampaignsDAO=null;
		
		try{
			payments=new ArrayList<DonationModelVO>();
			myCampaignsDAO =new MyCampaignsDAO(getConnection());
			payments=myCampaignsDAO.getMyPayments(userId);
				
			
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
		
		return payments;
	}
	

}
