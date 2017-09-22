package com.alacriti.crowdFunding.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.crowdFunding.bo.impl.BOException;
import com.alacriti.crowdFunding.bo.impl.DonationBO;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;

public class DonationDelegate extends BaseDelegate{
	public DonationDelegate(){
		
	}
	public boolean Donation(DonationModelVO donModelVO,int userId){
		boolean rollBack=false;
		DonationBO donationBO=null;
		Connection connection=null;
		boolean result=false;
		try{
			connection=startDBTransaction();
			System.out.println("COnnection established");
			setConnection(connection);
			donationBO=new DonationBO(getConnection());
			result=donationBO.donation(donModelVO, userId);			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			rollBack=true;
		}
		finally{
			endDBTransaction(connection,rollBack);
		}
		return result;
		
	}
	
	public List<DonationModelVO> supporters(int campaignId)
	{
		boolean rollBack=false;
		List<DonationModelVO> supporters=null;
		Connection connection=null;
		DonationBO donationBO=null;
		try{
			supporters=new ArrayList<DonationModelVO>();
			connection=startDBTransaction();
			setConnection(connection);
			donationBO = new DonationBO(getConnection());
			supporters=donationBO.supporters(campaignId);
		}
		catch(BOException e)
		{
			e.printStackTrace();
			rollBack=true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			endDBTransaction(connection,rollBack);
		}
		return supporters;
		
	}

}
