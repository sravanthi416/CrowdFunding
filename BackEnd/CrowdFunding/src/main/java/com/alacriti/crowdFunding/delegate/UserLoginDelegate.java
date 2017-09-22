package com.alacriti.crowdFunding.delegate;

import java.sql.Connection;

import com.alacriti.crowdFunding.bo.impl.BOException;
import com.alacriti.crowdFunding.bo.impl.UserLoginBO;
import com.alacriti.crowdFunding.model.vo.UserLoginVO;

public class UserLoginDelegate extends BaseDelegate{
	public UserLoginDelegate(){}
	public boolean userLogin(UserLoginVO userLoginVO){
		boolean result=false;
		UserLoginBO userLoginBO=null;
		boolean rollBack = false;
		Connection connection = null;
		System.out.println("Befor try block in userDelegate");
		try{
			System.out.println("Bfor connection");
			connection = startDBTransaction();
			System.out.println("after connection");
			setConnection(connection);
			userLoginBO=new UserLoginBO(getConnection());
			System.out.println("got connection");
			result=userLoginBO.UserLogin(userLoginVO);
		}
		catch(BOException e)
		{
			e.printStackTrace();
			System.out.println("Exceptin occured in BOLogin"+e.getMessage());
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return result;
	}
}
