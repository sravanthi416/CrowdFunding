package com.alacriti.crowdFunding.bo.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.alacriti.crowdFunding.dao.impl.DAOException;
import com.alacriti.crowdFunding.dao.impl.NewCampaignDAO;
import com.alacriti.crowdFunding.model.vo.CampaignMultipartDataVO;
import com.alacriti.crowdFunding.model.vo.CategoriesModelVO;
import com.alacriti.crowdFunding.model.vo.NewCampaignVO;
import com.alacriti.crowdFunding.util.MailUtil;
import com.alacriti.crowdFunding.util.Mailer;

public class NewCampaignBO extends BaseBO {
	public NewCampaignBO(){}
	public NewCampaignBO(Connection conn){
		super(conn);
	}
	
	public boolean newCampaign(CampaignMultipartDataVO campaignMultipartDataVO,int id,String email)throws BOException
	{
		boolean result=false;
		NewCampaignDAO newCampaignDAO=null;
		try{
			
			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName=df.format(new Date())+"."+campaignMultipartDataVO.getFileType();
			String fileLocation = "/home/sravanthir/Downloads/wildfly-10.1.0.Final/assets/images/crowdFunding/"+fileName;
			writeFile(campaignMultipartDataVO.getFile(), fileLocation);
			newCampaignDAO=new NewCampaignDAO(getConnection());
			NewCampaignVO newCampaignVO=generateCampaignVO(campaignMultipartDataVO,fileName);
			
			result=newCampaignDAO.NewCampaign(newCampaignVO,id);
			sendingMail(newCampaignVO.getFriends(),email,newCampaignVO.getTitle());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException("Excepiton occured in BO of new Campaign"+e.getMessage());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new BOException("Excepiton occured in BO of new Campaign"+e.getMessage());
		}
		
		return result;
		
		
	}
	
	
	private NewCampaignVO generateCampaignVO(CampaignMultipartDataVO campMultiDataVO,String fileName) {
		
			NewCampaignVO newCampVo=new NewCampaignVO();
			newCampVo.setAmountRaised(campMultiDataVO.getAmountRaised());
			newCampVo.setTitle(campMultiDataVO.getTitle());
			newCampVo.setBeneficiaryName(campMultiDataVO.getBeneficiaryName());
			newCampVo.setCategory(campMultiDataVO.getCategory());
			newCampVo.setBeneficiaryName(campMultiDataVO.getBeneficiaryName());
			newCampVo.setImagePath(fileName);
			newCampVo.setStory(campMultiDataVO.getStory());
			newCampVo.setFriends(campMultiDataVO.getFriends());
			
			
				newCampVo.setExpiryDate((campMultiDataVO.getExpiryDate()));
			
			System.out.println("categoryis "+newCampVo.getCategory());
			
		return newCampVo;
	}
	private boolean writeFile(byte[] content, String filename) {
		
		try {

			File file = new File(filename);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fop = new FileOutputStream(file);

			fop.write(content);
			fop.flush();
			fop.close();
			return true;

		} catch (Exception e) {
			//log.error("Exception occured in writing file into a fileObject"+ e.getMessage(), e);
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CategoriesModelVO> categories()throws BOException
	{
		List<CategoriesModelVO> categories = null;
		NewCampaignDAO newCampaignDAO=null;
		try{
			categories = new ArrayList<CategoriesModelVO>();
			newCampaignDAO =new NewCampaignDAO(getConnection());
			categories=newCampaignDAO.getCategories();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new BOException("error occured in BO Class "+e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new BOException("error occured in BO Class "+e.getMessage());
		}
		return categories;
		
	}
	

	public void sendingMail(String friends,String invitedBy,String campaignTitle)
	{
		 StringTokenizer st = new StringTokenizer(friends);
		 String sendTo=null;
		Mailer mailer =null;
		 try{
			 mailer =new Mailer();
			 while(st.hasMoreTokens())
			 {
				  sendTo=st.nextToken(",");
				  log.debug("Sending mails before   "+sendTo);
				 /* MailUtil.sendMail(invitedBy, sendTo, campaignTitle);*/
				  mailer.send("sravanthireddivari@gmail.com","sravssravs",
						  sendTo,"Invitation For Campaign \""+campaignTitle+"\"",
						  "Hai,Please Login In to the crowd Funding http://192.168.35.58:4200/home and donate "+
						  "\nInvited BY:: "+invitedBy);
			 }
		 }
		 catch(Exception e)
		 {
			 log.error("Error occured while sending mail");
		 }
	}
	
	
	
	
	
	
	
}
