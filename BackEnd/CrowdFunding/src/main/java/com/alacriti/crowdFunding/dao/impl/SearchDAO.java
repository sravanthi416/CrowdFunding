package com.alacriti.crowdFunding.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.crowdFunding.constants.Constants;
import com.alacriti.crowdFunding.constants.DBColumnConstants;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;

public class SearchDAO extends BaseDAO{
	public SearchDAO(){}
	public SearchDAO(Connection conn){
		super(conn);
	}
	
	public List<NewCampaignVO> searchText(String text)throws DAOException{
		List<NewCampaignVO> campaigns=null;
			Statement statement=null;
			ResultSet resultSet=null;
			
			try{
				campaigns=new ArrayList<NewCampaignVO>();
				statement=getConnection().createStatement();
				resultSet=statement.executeQuery(campaignsSearch(text));	
				campaigns=getResultSet(resultSet);		
				
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
		
		
		public String campaignsSearch(String text)
		{
			return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED +","+
		DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","+
					DBColumnConstants.CAMPAIGN_TBL_TITLE+","+
					DBColumnConstants.CAMAPIGN_TBL_PHOTO+","+
					DBColumnConstants.CAMPAIGN_TBL_USERID+","+
					DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME+","+
					DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+","+
		DBColumnConstants.CAMAPIGN_TBL_STORY +
		" from sravanthir_crowdfunding_campaigns where "+
		DBColumnConstants.CAMPAIGN_TBL_TITLE+"like %"+text+
		"% or "+DBColumnConstants.CAMAPIGN_TBL_STORY+" like %"+text+"%" ;
			
			/*return "select amountRaised,amountGot,title,story from sravanthir_crowdfunding_campaigns";*/
		
			
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
			
		
		public List<NewCampaignVO> getResultSet(ResultSet resultSet)
		{
			List<NewCampaignVO> campaigns=null;
			String funderName=null;
			try{
				campaigns=new ArrayList<NewCampaignVO>();
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
			catch(Exception e)
			{
				log.error("error occured in Get Result DAO");
			}
			return campaigns;
		}
		
		public List<NewCampaignVO> searchByDate(String text)throws DAOException{
			List<NewCampaignVO> campaigns=null;
				Statement statement=null;
				ResultSet resultSet=null;
				
				try{
					campaigns=new ArrayList<NewCampaignVO>();
					statement=getConnection().createStatement();
					resultSet=statement.executeQuery(campaignsSearchDate(text));	
					campaigns=getResultSet(resultSet);		
					
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
		
		public String campaignsSearchDate(String date)
		{
			return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED +","+
					DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","+
								DBColumnConstants.CAMPAIGN_TBL_TITLE+","+
								DBColumnConstants.CAMAPIGN_TBL_PHOTO+","+
								DBColumnConstants.CAMPAIGN_TBL_USERID+","+
								DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME+","+
								DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+","+
					DBColumnConstants.CAMAPIGN_TBL_STORY +
					" from sravanthir_crowdfunding_campaigns where "+
					DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE+" <= "+date ;
		}
		
		public List<NewCampaignVO> searchBoth(String date,String text)throws DAOException
		{
			List<NewCampaignVO> campaigns=null;
			Statement statement=null;
			ResultSet resultSet=null;
			
			try{
				campaigns=new ArrayList<NewCampaignVO>();
				statement=getConnection().createStatement();
				resultSet=statement.executeQuery(searchBothCmd(date,text));	
				campaigns=getResultSet(resultSet);		
				
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
		
		public String searchBothCmd(String expiryDate,String text)
		{
			return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED +","+
					DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","+
								DBColumnConstants.CAMPAIGN_TBL_TITLE+","+
								DBColumnConstants.CAMAPIGN_TBL_PHOTO+","+
								DBColumnConstants.CAMPAIGN_TBL_USERID+","+
								DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME+","+
								DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+","+
					DBColumnConstants.CAMAPIGN_TBL_STORY +
					" from sravanthir_crowdfunding_campaigns where "+
					DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE+" <= "+
					expiryDate+"and ( +"+DBColumnConstants.CAMPAIGN_TBL_TITLE+" like '% "+text+"%' or "+
					DBColumnConstants.CAMAPIGN_TBL_STORY+"like '%"+text+"%')" ;
			
		}
		
		
		
}
