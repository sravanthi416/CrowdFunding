package com.alacriti.crowdFunding.delegate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.bo.impl.SearchBO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.model.vo.SearchModelVO;


public class SearchDelegate extends BaseDelegate{
	public static final Logger log=Logger.getLogger(SearchDelegate.class);
	public SearchDelegate(){}
	
	public List<NewCampaignVO> searchCampaigns(SearchModelVO searchMVO)
	{
		List<NewCampaignVO> results=null;
		Connection connection=null;
		SearchBO searchBO=null;
		boolean rollBack=false;
		try{
			results=new ArrayList<NewCampaignVO>();
			connection = startDBTransaction();
			log.debug("connectoin is "+connection);
			setConnection(connection);
			searchBO =new SearchBO(getConnection());
			results=searchBO.searchCampaigns(searchMVO);
		}
		catch(Exception e)
		{
			log.error("ERROR OCCURED IN search delegate");
			rollBack=true;
		}
		finally{
			endDBTransaction(connection, rollBack);		
		}
		return results;
	}

}
