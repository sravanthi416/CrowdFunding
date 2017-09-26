package com.alacriti.crowdFunding.bo.impl;

import java.sql.Connection;

import com.alacriti.crowdFunding.dao.impl.DAOException;
import com.alacriti.crowdFunding.dao.impl.UserLoginDAO;
import com.alacriti.crowdFunding.model.vo.UserLoginVO;

public class UserLoginBO extends BaseBO {
	public UserLoginBO() {
		// TODO Auto-generated constructor stub
	}
	public UserLoginBO(Connection conn)
	{
		super(conn);
	}
public boolean UserLogin(UserLoginVO userLoginVO) throws BOException
{
	boolean result=false;
	UserLoginDAO userLoginDAO=null;
	try{
		userLoginDAO=new UserLoginDAO(getConnection());
		result=userLoginDAO.userLogin(userLoginVO);
	}
	catch(DAOException e)
	{
		log.debug("DAOException in userLogin useroginDAO"+e.getMessage());
		e.printStackTrace();
		throw new BOException("BOEXcpeition in userLoginBO"+e.getMessage());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw new BOException("Excpetion occured in user BO"+e.getMessage());
	}
	return result;
}
}
