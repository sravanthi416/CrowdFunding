package com.alacriti.crowdFunding.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.alacriti.crowdFunding.dao.impl.DAOException;
import com.alacriti.crowdFunding.dao.impl.DonationDAO;
import com.alacriti.crowdFunding.model.vo.DonationModelVO;

public class DonationBO extends BaseBO{
	public DonationBO(){}
	public DonationBO(Connection conn){
		super(conn);
	}
	
	public boolean donation(DonationModelVO donationModelVO,int userId)throws BOException
	{
		boolean result=false;
		DonationDAO donationDAO=null;
		int campaignId=0;
		int amount=0;
		int amountDonated=0;
		int amountToUpdate=0;
		boolean valid=false;
		try{
			donationDAO =new DonationDAO(getConnection());
			campaignId=donationDAO.donation(donationModelVO, userId);
			valid=isValidData(donationModelVO);
			amount=donationDAO.getAmountGotFromtbl(campaignId);
			amountDonated=donationModelVO.getAmountDonated();
			log.debug("AMount is donated"+amountDonated);
			amountToUpdate=amount+amountDonated;
			log.debug("AMount is donated"+amountToUpdate);
			result=donationDAO.updateCampaignTable(amountToUpdate,campaignId);
			
			/*result=true;*/
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new BOException();
			
		}	
		return result;
	}
	public List<DonationModelVO> supporters(int campaignId) throws BOException{
		List<DonationModelVO> supporters=null;
		DonationDAO donationDAO = null;
		try{
			supporters=new ArrayList<DonationModelVO>();
			donationDAO =new DonationDAO(getConnection());
			supporters=donationDAO.supporters(campaignId);
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException("ERROR occured in BO class of supporters");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new BOException("ERROR occured in BO class of supporters");
		}
		return supporters;
	}
	public boolean isValidData(DonationModelVO doModelVO)
	{
		boolean result=true;
		if(!Pattern.matches("^[1-9][0-9]{11}$", doModelVO.getAccountNo()))
		{
		result=false;	
		}
		if(!Pattern.matches("^[0-9]*",Integer.toString(doModelVO.getAmountDonated())))
				{
			result=false;
				}
		return result;
		
		
	}
	

}
