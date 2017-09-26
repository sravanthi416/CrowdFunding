package com.alacriti.crowdFunding.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.bo.impl.BOException;
import com.alacriti.crowdFunding.bo.impl.NewCampaignBO;
import com.alacriti.crowdFunding.model.vo.CampaignMultipartDataVO;
import com.alacriti.crowdFunding.model.vo.CategoriesModelVO;

public class NewCampaignDelegate extends BaseDelegate{
	public static final Logger log=Logger.getLogger(NewCampaignDelegate.class);
	public NewCampaignDelegate(){
	}
	public boolean newCampaign(CampaignMultipartDataVO campaignMultipartDataVO,int id,String email)
	{
		boolean result=false;
		NewCampaignBO newCampaignBO=null;
		Connection connection=null;
		boolean rollBack=false;
		try{
			log.debug("Bfor connection");
			connection = startDBTransaction();
			log.debug("after connection"+connection);
			setConnection(connection);
			
			newCampaignBO=new NewCampaignBO(getConnection());
			result=newCampaignBO.newCampaign(campaignMultipartDataVO,id,email);
		}
		catch(BOException e)
		{
			e.printStackTrace();
			rollBack=true;
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
		}
		finally{
		endDBTransaction(connection, rollBack);
		}
		return result;
	}
	
	public List<CategoriesModelVO> getCategories()
	{
		boolean rollBack=false;
		Connection connection=null;
		NewCampaignBO newCampaignBO=null;
		List<CategoriesModelVO> categories=null;
		try{
			connection=startDBTransaction();
			setConnection(connection);
			categories=new ArrayList<CategoriesModelVO>();
			newCampaignBO =new NewCampaignBO(getConnection());
			categories=newCampaignBO.categories();
			
		}
		catch(Exception e)
		{
			log.error("Error in getCategories delegte");
			e.printStackTrace();
		}
		finally{
			endDBTransaction(connection,rollBack);
		}
		return categories;
		
	}

}
