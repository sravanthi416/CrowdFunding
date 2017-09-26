package com.alacriti.crowdFunding.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.crowdFunding.constants.DBColumnConstants;
import com.alacriti.crowdFunding.model.vo.CategoriesModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;

public class NewCampaignDAO extends BaseDAO{
	public NewCampaignDAO(){}
	public NewCampaignDAO(Connection conn)
	{
		super(conn);		
	}
	public boolean NewCampaign(NewCampaignVO newCampaignVO,int id) throws DAOException{
		boolean result=false;
		PreparedStatement preparedStatement=null;
		Statement statement=null;
		ResultSet resultSet=null;
		int catId=0;
		String categoryName=null;
		System.out.println("categoryName"+categoryName);
		try{
			
			statement=getConnection().createStatement();
			log.debug("In New Cmapaig after get connection");
			categoryName=newCampaignVO.getCategory();
			
			
			resultSet=statement.executeQuery(getCategoryIdSQLCmd(categoryName));
			log.debug("In New Cmapaig executing query");
			log.debug("Resultset is"+resultSet);
			if(resultSet.next())
			{
				catId=resultSet.getInt("categoryId");
				log.debug("In New Cmapaig result set"+catId);
			}
			preparedStatement=getPreparedStatement(getConnection(), addCampaignToSQLDBCmd());
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2,newCampaignVO.getTitle());
			preparedStatement.setInt(3,newCampaignVO.getAmountRaised());
			preparedStatement.setInt(4,0);
			preparedStatement.setInt(5,catId);
			preparedStatement.setString(6,newCampaignVO.getBeneficiaryName());
			preparedStatement.setString(7,newCampaignVO.getImagePath());
			preparedStatement.setString(8,newCampaignVO.getStory());
			preparedStatement.setInt(9,0);
			preparedStatement.setString(10, newCampaignVO.getExpiryDate());
			preparedStatement.executeUpdate();
			result=true;
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("SQLEXception occured in new campaign adding"+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Exception Occured in new campaign adding"+e.getMessage());
		}
		return result;
	}
	
	public String getCategoryIdSQLCmd(String catName)
	{
		return "select "+ DBColumnConstants.CATEGORIES_TBL_ID+
				" from sravanthir_crowdfunding_categories where "
				+ DBColumnConstants.CATEGORIES_TBL_NAME + " = '"+catName+"'";
	}
	public String addCampaignToSQLDBCmd()
	{
		return "insert into sravanthir_crowdfunding_campaigns("+DBColumnConstants.CAMPAIGN_TBL_USERID +
				","+DBColumnConstants.CAMPAIGN_TBL_TITLE+
				","+DBColumnConstants.CAMAPIGN_TBL_AMOUNTRAISED+
				","+DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+","+
				DBColumnConstants.CAMPAIGN_TBL_CATEGORYID+","+
				DBColumnConstants.CAMAPIGN_TBL_BENEFICIARYNAME+
				","+DBColumnConstants.CAMAPIGN_TBL_PHOTO+
				","+DBColumnConstants.CAMAPIGN_TBL_STORY+
				","+DBColumnConstants.CAMAPIGN_TBL_STATUS+
				","+DBColumnConstants.CAMAPIGN_TBL_EXPIRYDATE+") values (?,?,?,?,?,?,?,?,?,?);";
	}
	
	public List<CategoriesModelVO> getCategories()throws DAOException
	{
		Statement statement=null;
		ResultSet rs=null;
		List<CategoriesModelVO> categories=null;
		try{
			categories=new ArrayList<CategoriesModelVO>();
			statement=getConnection().createStatement();
			rs=statement.executeQuery(getCategoriesCmd());
			while(rs.next())
			{
				categories.add(new CategoriesModelVO(rs.getInt("categoryId"),
						rs.getString("categoryName")));
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("Error in getting categories");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("Error in getting categories");
		}
		return categories;
	}
	public String getCategoriesCmd()
	{
		return "select * from sravanthir_crowdfunding_categories";
	}
	
	
	
	
}
