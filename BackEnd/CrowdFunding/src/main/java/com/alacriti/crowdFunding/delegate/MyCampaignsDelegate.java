package com.alacriti.crowdFunding.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.bo.impl.BOException;
import com.alacriti.crowdFunding.bo.impl.MyCampaignsBO;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;

public class MyCampaignsDelegate extends BaseDelegate{
	public static final Logger log=Logger.getLogger(MyCampaignsDelegate.class);
	public MyCampaignsDelegate(){}
	
	public List<NewCampaignVO> getMyCampaigns(int userId)
	{
		boolean isRollback=false;
		List<NewCampaignVO> myCampaigns =null;
		Connection connection =null;
		MyCampaignsBO myCampaignsBO=null;
		try{
			myCampaigns=new ArrayList<NewCampaignVO>();
			connection=startDBTransaction();
			setConnection(connection);
			myCampaignsBO =new MyCampaignsBO(getConnection());
			myCampaigns=myCampaignsBO.getMyCampaigns(userId);
					
		}
		catch(BOException e)
		{
			log.error("error occured in Delegate ");
			e.printStackTrace();
			isRollback=true;
		}
		catch(Exception e)
		{
			log.error("Error occuredi n delegate");
			e.printStackTrace();
			isRollback=true;
			
		}
		finally{
			endDBTransaction(connection, isRollback);
		}
		return myCampaigns;
	}
	
	public List<DonationModelVO> getMyPayments(int userId)
	{
		boolean isRollback=false;
		List<DonationModelVO> myCampaigns =null;
		Connection connection =null;
		MyCampaignsBO myCampaignsBO=null;
		try{
			myCampaigns=new ArrayList<DonationModelVO>();
			connection=startDBTransaction();
			setConnection(connection);
			myCampaignsBO =new MyCampaignsBO(getConnection());
			myCampaigns=myCampaignsBO.getMyPayments(userId);
					
		}
		catch(BOException e)
		{
			log.error("error occured in Delegate ");
			e.printStackTrace();
			isRollback=true;
		}
		catch(Exception e)
		{
			log.error("Error occuredi n delegate");
			e.printStackTrace();
			isRollback=true;
			
		}
		finally{
			endDBTransaction(connection, isRollback);
		}
		return myCampaigns;
	}
	
		

}
