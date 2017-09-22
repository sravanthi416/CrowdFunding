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
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
public class CampaignListDAO extends BaseDAO {
	public static final Logger log= Logger.getLogger(CampaignListDAO.class);
	public CampaignListDAO(){}
	public CampaignListDAO(Connection conn)
	{
		super(conn);
	}
	public List<NewCampaignVO> campaignList() throws DAOException{
		List<NewCampaignVO> campaigns=null;
		Statement statement=null;
		ResultSet resultSet=null;
		String funderName=null;
		try{
			campaigns=new ArrayList<NewCampaignVO>();
			statement=getConnection().createStatement();
			resultSet=statement.executeQuery(listOfCampaigns());	
			while(resultSet.next())
			{		
				System.out.println("lis is before"+campaigns);
				resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_USERID);
				funderName=getFundRaiserName(resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_USERID));
				System.out.println("In campigns add funder name is "+funderName);
				campaigns.add(new NewCampaignVO(resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED),
						resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT),
						resultSet.getString(DBColumnConstants.CAMPAIGN_TBL_TITLE),
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_STORY),
						funderName,		
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME),
						resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID),
						Constants.IMAGE_PATH+resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_PHOTO)));
				System.out.println("lis is "+campaigns);
				System.out.println("values are"+resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED)+resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT));
			}
			System.out.println("List size"+campaigns.size());		
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("Exception occured in DAOException "+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Exception occured in DAOException "+e.getMessage());
		}
		finally{
			close(statement);
		}
		return campaigns;
	}
	
	
	public String listOfCampaigns()
	{
		return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED +","+
	DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","+
				DBColumnConstants.CAMPAIGN_TBL_TITLE+","+
				DBColumnConstants.CAMAPIGN_TBL_PHOTO+","+
				DBColumnConstants.CAMPAIGN_TBL_USERID+","+
				DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME+","+
				DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+","+
	DBColumnConstants.CAMAPIGN_TBL_STORY +
	" from sravanthir_crowdfunding_campaigns order by"+DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE;
		
		/*return "select amountRaised,amountGot,title,story from sravanthir_crowdfunding_campaigns";*/
		
		
	}
		
	public int getCampaignCount() throws DAOException
	{
		Statement statement=null;
		ResultSet rs=null;
		int count=0;
		try{
		statement=getConnection().createStatement();
		rs=statement.executeQuery(getCampaignCountCmd());
		if(rs.next())
		{
			count=rs.getInt(1);
		}
		//count=rs.getFetchSize();
		System.out.println("Count is "+count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("error occured in DAO Exception of getting the count");
		}
		
		return count;
	}
	public String getCampaignCountCmd()
	{
		return "select count("+DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+
				") from sravanthir_crowdfunding_campaigns";
	}
	public List<NewCampaignVO> campaignDetails(int campId)throws DAOException
	{
		List<NewCampaignVO> campaignDetails =null;
		Statement statement=null;
		ResultSet rs=null;
		try{
			campaignDetails=new ArrayList<NewCampaignVO>();
			statement=getConnection().createStatement();
			rs=statement.executeQuery(getCampaignDetailSQlmd(campId));
			while(rs.next())
					{
				campaignDetails.add(new NewCampaignVO(rs.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED),
						rs.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT),
						rs.getInt(DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID),
						Constants.IMAGE_PATH+rs.getString(DBColumnConstants.CAMAPIGN_TBL_PHOTO),
						rs.getString(DBColumnConstants.CAMAPIGN_TBL_STORY),
						rs.getString(DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE))
						);
				System.out.println("lis is "+campaignDetails);
					}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("ERROr occured in getting Campaign Details"+e.getMessage());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Error occured");
		}
		return campaignDetails;
	}
	
	public String getCampaignDetailSQlmd(int campId)
	{
		return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED+","
	+DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","
				+DBColumnConstants.CAMAPIGN_TBL_PHOTO+","+
	DBColumnConstants.CAMAPIGN_TBL_STORY+","
				+DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+","+
	DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE+
				" from sravanthir_crowdfunding_campaigns where "+ 
	DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+" = "+campId;
	}
	
	public String getFundRaiserName(int userId)
	{
		Statement statement=null;
		ResultSet rs=null;
		String fundRaiserName=null;
		try{
			statement=getConnection().createStatement();
			rs=statement.executeQuery(getFundRaiserSQLCmd(userId));
			if(rs.next())
			{
				fundRaiserName=rs.getString(DBColumnConstants.USERS_TBL_NAME);
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
		return fundRaiserName;
	}
	
	
	public String getFundRaiserSQLCmd(int userId)
	{
		return "select "+DBColumnConstants.USERS_TBL_NAME+" from sravanthir_crowdfunding_users where "
	+DBColumnConstants.USERS_TBL_ID+ " = " + userId;	}
		
	public List<NewCampaignVO> getCampaignCategories(int categoryId) throws DAOException{
		List<NewCampaignVO> campaigns=null;
		Statement statement=null;
		ResultSet resultSet=null;
		String funderName=null;
		try{
			campaigns=new ArrayList<NewCampaignVO>();
			statement=getConnection().createStatement();
			resultSet=statement.executeQuery(getCampaigns(categoryId));	
			while(resultSet.next())
			{		
				System.out.println("lis is before"+campaigns);
				resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_USERID);
				funderName=getFundRaiserName(resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_USERID));
				System.out.println("In campigns add funder name is "+funderName);
				campaigns.add(new NewCampaignVO(resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED),
						resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT),
						resultSet.getString(DBColumnConstants.CAMPAIGN_TBL_TITLE),
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_STORY),
						funderName,		
						resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME),
						resultSet.getInt(DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID),
						Constants.IMAGE_PATH+resultSet.getString(DBColumnConstants.CAMAPIGN_TBL_PHOTO)));
				System.out.println("lis is "+campaigns);
				System.out.println("values are"+resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED)+resultSet.getInt(DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT));
			}
			System.out.println("List size"+campaigns.size());		
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("Exception occured in DAOException "+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Exception occured in DAOException "+e.getMessage());
		}
		finally{
			close(statement);
		}
		return campaigns;
	}
	public String getCampaigns(int categoryId)
	{
		return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED +","+
				DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","+
							DBColumnConstants.CAMPAIGN_TBL_TITLE+","+
							DBColumnConstants.CAMAPIGN_TBL_PHOTO+","+
							DBColumnConstants.CAMPAIGN_TBL_USERID+","+
							DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME+","+
							DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+","+
				DBColumnConstants.CAMAPIGN_TBL_STORY +" from sravanthir_crowdfunding_campaigns where "
							+DBColumnConstants.CAMPAIGN_TBL_CATEGORYID+" = "+categoryId;
					
	}
	public int getCampaignCountCategories(int categoryId)throws DAOException {
		Statement statement=null;
		ResultSet rs=null;
		int count=0;
		try{
		statement=getConnection().createStatement();
		rs=statement.executeQuery(getCampaignCatCountCmd(categoryId));
		if(rs.next())
		{
			count=rs.getInt(1);
		}
		//count=rs.getFetchSize();
		System.out.println("Count is "+count);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("error occured in DAO Exception of getting the count");
		}
		
		return count;
	}
	public String getCampaignCatCountCmd(int categoryId)
	{
		return "select count("+DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+
				") from sravanthir_crowdfunding_campaigns where "+
				DBColumnConstants.CAMPAIGN_TBL_CATEGORYID+" = "+categoryId;
	}
	
	



	
	

}
