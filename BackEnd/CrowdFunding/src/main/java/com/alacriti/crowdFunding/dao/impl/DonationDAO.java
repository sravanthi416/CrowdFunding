package com.alacriti.crowdFunding.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.crowdFunding.constants.DBColumnConstants;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;

public class DonationDAO extends BaseDAO{
public DonationDAO(){}
public DonationDAO(Connection conn)
{
	super(conn);
}
public int donation(DonationModelVO donModelVO,int userId)throws DAOException
{
	PreparedStatement preStatement=null;
	int result=0;
	try{
		preStatement=getPreparedStatement(getConnection(), addDonationToSQLDBCmd());
		preStatement.setInt(1,userId);
		preStatement.setInt(2,donModelVO.getCampaignId());
		preStatement.setInt(3,donModelVO.getAmountDonated());
		preStatement.setString(4, donModelVO.getComments());
		preStatement.setString(5, donModelVO.getDebitCardNo());
		preStatement.setInt(6,donModelVO.getMonth());
		preStatement.setInt(7, donModelVO.getYear());
		preStatement.setString(8,donModelVO.getCvv());
		preStatement.setString(9,donModelVO.getNameOnCard());
		preStatement.setString(10,donModelVO.getAccountNo());
		preStatement.setString(11,donModelVO.getIfscCode());
		preStatement.setString(12,donModelVO.getNameOnBook());
		preStatement.executeUpdate();
		result=donModelVO.getCampaignId();
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		throw new DAOException("Error occured in donation dao"+e.getMessage());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw new DAOException("Error occured in donation dao"+e.getMessage());
	}
	finally{
		close(preStatement);
	}
	return result;
		
	
}
public String addDonationToSQLDBCmd(){
	return "insert into sravanthir_crowdfunding_donations("+DBColumnConstants.DONATION_TBL_USERID+
			","+DBColumnConstants.DONATION_TBL_CAMPAIGNID+
			","+DBColumnConstants.DONATION_TBL_AMOUNT+
			","+DBColumnConstants.DONATION_TBL_COMMENTS+
			","+DBColumnConstants.DONATION_TBL_DEBITCARDNO+
			","+DBColumnConstants.DONATION_TBL_EXPIRYMONTH+
			","+DBColumnConstants.DONATION_TBL_EXPIRYYEAR+
			","+DBColumnConstants.DONATION_TBL_CVV+
			","+DBColumnConstants.DONATION_TBL_NAMEONCARD+
			","+DBColumnConstants.DONATION_TBL_ACCOUNTNO+
			","+DBColumnConstants.DONATION_TBL_IFSCCODE+
			","+DBColumnConstants.DONATION_TBL_NAMEONBOOK+") values(?,?,?,?,?,?,?,?,?,?,?,?)";
}

public boolean updateCampaignTable(int amountupdate,int campaignId)throws DAOException
{
	boolean result=false;
	
	PreparedStatement preStatement=null;
	try{
		preStatement=getPreparedStatement(getConnection(), updateCampaignSQLDBCmd(campaignId));
		preStatement.setInt(1,amountupdate);
		preStatement.executeUpdate();
		result=true;
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return result;
}

public int getAmountGotFromtbl(int campaignId)
{
	int amountGot=0;
	Statement statement=null;
	ResultSet rs=null;
	try{
		
		statement=getConnection().createStatement();
		rs=statement.executeQuery(getAmountGotSQLCmd(campaignId));
		if(rs.next())
		{
			amountGot=rs.getInt("amountGot");
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
	return amountGot;
}

public String getAmountGotSQLCmd(int campaignId)
{
	return "select "+DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+
			" from sravanthir_crowdfunding_campaigns where "+
			DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+" = "+campaignId;
}

public String updateCampaignSQLDBCmd(int campaignId)
{
	return "update sravanthir_crowdfunding_campaigns set "+
DBColumnConstants.CAMAPIGN_TBL_AMOUNTGOT+
"=? where "+DBColumnConstants.CAMPAIGN_TBL_CAMPAIGNID+" = "+campaignId;
}



public List<DonationModelVO> supporters(int campignId)throws DAOException
{
	List<DonationModelVO> supporters=null;
	Statement statement=null;
	ResultSet rs=null;
	String userName=null;
	DonationModelVO donationModelVO =null;
	try{
		
		supporters=new ArrayList<DonationModelVO>();
		statement=getConnection().createStatement();
		donationModelVO =new DonationModelVO();
		rs=statement.executeQuery(getSupportersSQLcmd(campignId));
		while(rs.next())
		{
			userName=getUserNameDB(rs.getInt(DBColumnConstants.DONATION_TBL_USERID));
			donationModelVO.setName(userName);
			supporters.add(new DonationModelVO(rs.getInt(DBColumnConstants.DONATION_TBL_CAMPAIGNID),
					rs.getInt(DBColumnConstants.DONATION_TBL_AMOUNT),
					rs.getString(DBColumnConstants.DONATION_TBL_COMMENTS),
					donationModelVO.getName()
					));		
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
public String getSupportersSQLcmd(int campaignId)
{
	return "select "+DBColumnConstants.DONATION_TBL_AMOUNT+
			","+DBColumnConstants.CAMPAIGN_TBL_USERID+","+
			DBColumnConstants.DONATION_TBL_CAMPAIGNID+","+
			DBColumnConstants.DONATION_TBL_COMMENTS+
			" from sravanthir_crowdfunding_donations where "+
			DBColumnConstants.DONATION_TBL_CAMPAIGNID+" = "+campaignId;
			

}
public String getUserNameDB(int id)
{
	Statement statement=null;
	ResultSet rs=null;
	String name=null;
	try
	{
		statement=getConnection().createStatement();
		rs=statement.executeQuery(getUser(id));
		if(rs.next())
		{
			name=rs.getString(DBColumnConstants.USERS_TBL_NAME);
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
	return name;
}
public String getUser(int id)
{
	return "select "+DBColumnConstants.USERS_TBL_NAME+
			" from sravanthir_crowdfunding_users where "+ DBColumnConstants.USERS_TBL_ID+" = "+id;
}


}
