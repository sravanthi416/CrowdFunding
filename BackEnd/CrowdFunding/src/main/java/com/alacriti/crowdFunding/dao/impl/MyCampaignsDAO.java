package com.alacriti.crowdFunding.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.constants.Constants;
import com.alacriti.crowdFunding.constants.DBColumnConstants;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;

public class MyCampaignsDAO extends BaseDAO{
	public static final Logger log=Logger.getLogger(MyCampaignsDAO.class);
	public MyCampaignsDAO(){
		
	}
	public MyCampaignsDAO(Connection conn)
	{
		super(conn);
	}
	public List<NewCampaignVO>getMyCampaignList(int userId) throws DAOException
	{
		List<NewCampaignVO> campaigns=null;
		Statement statement=null;
		ResultSet resultSet=null;
		log.info("before try in my campaigns");
		try{
			log.info("after try in my campaigns");
			campaigns=new ArrayList<NewCampaignVO>();
			statement=getConnection().createStatement();
			resultSet=statement.executeQuery(getMyCampSQLCmd(userId));
			log.info("before while.in my campaigns");
			while(resultSet.next())
			{
				log.debug("lis is before"+campaigns);
				log.info("in while of my Campaign");
				resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_USERID);
				campaigns.add(new NewCampaignVO(resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED),
						resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT),
						resultSet.getString(DBColumnConstants.CAMPAIGN_TBL_TITLE),
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_STORY),
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE),		
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME),
						resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID),
						Constants.IMAGE_PATH+resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_PHOTO)));
				log.info("lis is "+campaigns);
				log.info("values are"+resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED)+resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT));
			}
			log.info("List size"+campaigns.size());
			System.out.println("List size no log"+campaigns.size());
			
				
			}
		catch(SQLException e)
		{
			log.error("Error occured in my campaigns list");
			throw new DAOException("error occured in DAO MYCampaigns");
		}
		catch(Exception e)
		{
			log.error("Error occured in my campaigs lis");
			throw new DAOException("error occured in DAO MYCampaigns");
		}
		return campaigns;
		
	}
	public String getMyCampSQLCmd(int userId)
	{
		return "select * from sravanthir_crowdfunding_campaigns where "+
	DBColumnConstants.CAMPAIGN_TBL_USERID+" = "+userId;
	}
	
	
	public List<DonationModelVO> getMyPayments(int userId)throws DAOException
	{
		List<DonationModelVO> supporters=null;
		Statement statement=null;
		ResultSet rs=null;
		String campaignName=null;
		DonationModelVO doModelVO=null;
		try{
			
			supporters=new ArrayList<DonationModelVO>();
			statement=getConnection().createStatement();
			doModelVO = new DonationModelVO();
			rs=statement.executeQuery(getPaymentsSQLcmd(userId));
			log.info("Befor while in get my payments");
			while(rs.next())
			{
				campaignName=getCampaignNameDB(rs.getInt(DBColumnConstants.DONATION_TBL_CAMPAIGNID));
				doModelVO.setName(campaignName);
				log.info("title og campaign in my payments"+campaignName);
				supporters.add(new DonationModelVO(rs.getInt(DBColumnConstants.DONATION_TBL_USERID),
						rs.getInt(DBColumnConstants.DONATION_TBL_AMOUNT),
						rs.getString(DBColumnConstants.DONATION_TBL_COMMENTS),
						doModelVO.getName()
						));		
			}
			for(DonationModelVO list:supporters)
			{
				log.info("usporjb"+list);
				System.out.println("usporjb"+list);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("ERROR OCCURED in get donators DAO"+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("ERROR OCCURED in get donators DAO"+e.getMessage());
		}
		finally{
			close(statement);
		}
		return supporters;
	}
	
	public String getCampaignNameDB(int id)
	{
		Statement statement=null;
		ResultSet rs=null;
		String name=null;
		try
		{
			statement=getConnection().createStatement();
			rs=statement.executeQuery(getCampaign(id));
			if(rs.next())
			{
				name=rs.getString(DBColumnConstants.CAMPAIGN_TBL_TITLE);
				log.info("Name in getCampignName"+name);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			close(rs);
		}
		return name;
	}
	
	
	public String getCampaign(int campaignId)
	{
		/*return "select "+DBColumnConstants.CAMPAIGN_TBL_TITLE+","
	+DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+
				"from sravanthir_crowdfunding_campaigns where "+
				DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+" = "+campaignId;
*/		return "select * from sravanthir_crowdfunding_campaigns where campaignId = "+campaignId;
		}
	
	
	public String getPaymentsSQLcmd(int userId)
	{
		return "select "+DBColumnConstants.DONATION_TBL_AMOUNT+
				","+DBColumnConstants.CAMPAIGN_TBL_USERID+","+
				DBColumnConstants.DONATION_TBL_CAMPAIGNID+","+
				DBColumnConstants.DONATION_TBL_COMMENTS+
				" from sravanthir_crowdfunding_donations where "+
				DBColumnConstants.DONATION_TBL_USERID+" = "+userId;
	}
	

}
