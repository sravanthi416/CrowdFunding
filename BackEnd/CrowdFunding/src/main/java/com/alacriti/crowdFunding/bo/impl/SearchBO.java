package com.alacriti.crowdFunding.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.crowdFunding.dao.impl.DAOException;
import com.alacriti.crowdFunding.dao.impl.SearchDAO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.model.vo.SearchModelVO;

public class SearchBO extends BaseBO {
	public static final Logger log = Logger.getLogger(SearchBO.class);
	public SearchBO(){
		
	}
	public SearchBO(Connection conn)
	{
		super(conn);
	}
	
	
	public List<NewCampaignVO> searchCampaigns(SearchModelVO searchMVO)throws BOException
	{
		List<NewCampaignVO> results=null;
		SearchDAO searchDAO = null;
		String date=null;
		String text=null;
		try
		{
			results=new ArrayList<NewCampaignVO>();
			date=searchMVO.getDate();
			text=searchMVO.getText();
			searchDAO = new SearchDAO(getConnection());
			log.debug("date in BO clss of search	"+date);
			log.debug("text in Bo class of search   "+text);
			
			
			
			if(date == null && text != null)
			{
				results=searchDAO.searchText(text);
			}
			else if(date != null && text == null)
			{
				results=searchDAO.searchByDate(date);
			}
			else if(date != null && text!= null)
			{
				results=searchDAO.searchBoth(date, text);
			}
			else{ results=null; 
			log.debug("In else statement of search BO");
			}	
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException();
		}
		catch(Exception e)
		{
			e.printStackTrace();log.error("ERROR occured in BO SEarch");
			throw new BOException();
		}
		return results;
	}

}
