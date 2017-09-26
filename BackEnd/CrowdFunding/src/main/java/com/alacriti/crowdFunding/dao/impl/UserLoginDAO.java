package com.alacriti.crowdFunding.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alacriti.crowdFunding.constants.DBColumnConstants;
import com.alacriti.crowdFunding.model.vo.UserLoginVO;

public class UserLoginDAO extends BaseDAO{
	public UserLoginDAO() {
		
	}
	public UserLoginDAO(Connection conn)
	{
		super(conn);
	}
	
	public boolean userLogin(UserLoginVO userLoginVO) throws DAOException
	{
		boolean result=false;
		String email=null;
		boolean doesEmailExists=false;
		PreparedStatement preparedStatement=null;
		int userId=0;
		try{
			email=userLoginVO.getUserEmail();
			doesEmailExists=isEmailExists(email);
			if(!doesEmailExists)
			{
				preparedStatement=getPreparedStatement(getConnection(), addUserToDBSQLCmd());
				preparedStatement.setString(1,userLoginVO.getUserName());
				preparedStatement.setString(2,userLoginVO.getUserEmail());
				preparedStatement.executeUpdate();
				result=true;
			}
			
			else
			{
				result=false;
			}
			userId=gettingUserId(email);
			userLoginVO.setId(userId);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DAOException("error occured in isEmailExists"+e.getMessage());
		}
		return result;
	}
		
	public boolean isEmailExists(String email) throws DAOException
	{
		boolean result=false;
		Statement statement=null;
		ResultSet rs=null;
		try{	
			statement=getConnection().createStatement();
			rs=statement.executeQuery(isUserEmailExistsSQLCmd(email));
			System.out.println("After selectb sql command");
			if(rs.next()){
				result=true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("ERROR has occured in userLogin method of userloginDAO"+e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DAOException();
		}
		finally{
			close(statement);
		}
		return result;
	}
	public String isUserEmailExistsSQLCmd(String email)
	{
		/*return "select "+DBColumnConstants.USERS_TBL_EMAIL+"from sravanthir_crowdfunding_users where "+DBColumnConstants.USERS_TBL_EMAIL+"=\""+ email +"\";";*/
		System.out.println("In sql mmd select");
		return "select * from sravanthir_crowdfunding_users where "+DBColumnConstants.USERS_TBL_EMAIL+" = '"+email+"'";
	}
	private String addUserToDBSQLCmd(){
		return "insert into sravanthir_crowdfunding_users("
	+DBColumnConstants.USERS_TBL_NAME +","
				+DBColumnConstants.USERS_TBL_EMAIL+") values(?,?);";
	}
	public int gettingUserId(String email) throws DAOException
	{
		int result=0;
		Statement statement=null;
		ResultSet rs=null;
		try{	
			statement=getConnection().createStatement();
			rs=statement.executeQuery(userIdSQLCmd(email));
			log.debug("After selectb sql command");
			if(rs.next()){
				result=rs.getInt(DBColumnConstants.USERS_TBL_ID);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new DAOException("ERROR has occured in userLogin method of userloginDAO"+e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw new DAOException();
		}
		finally{
			close(statement);
		}
		return result;
	}
	public String userIdSQLCmd(String email)
	{
		return "select "+DBColumnConstants.USERS_TBL_ID+" "
				+ "from sravanthir_crowdfunding_users where "+
				DBColumnConstants.USERS_TBL_EMAIL+" = '"+email +"'";
	}

}
