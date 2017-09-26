package com.alacriti.crowdFunding.model.vo;



import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class NewCampaignVO {
	
	private int amountRaised;
	private int amountGot;
	private String title;
	private String category;
	private String beneficiaryName;
	private String nameYourFundRaiser;
	private String imagePath;
	private String story;
	private String expiryDate;
	private int status;
	private int campaignId;
	private String userName;
	private String friends;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public NewCampaignVO(){}
	NewCampaignVO(int amountRaised,String title,String category,
			String beneficiaryName,String nameYourFundRaiser, String imagePath,String story,String expiryDate,String friends,int status){
		this.amountRaised=amountRaised;
		this.title=title;
		this.category=category;
		this.beneficiaryName=beneficiaryName;
		this.nameYourFundRaiser=nameYourFundRaiser;
		this.imagePath=imagePath;
		this.story=story;
		this.expiryDate=expiryDate;
		this.friends=friends;
		this.status=status;
	}
	public String getFriends() {
		return friends;
	}
	public void setFriends(String friends) {
		this.friends = friends;
	}
	public NewCampaignVO(int amountRaised,int amountGot,String title,String story,String userName,String beneficiaryName,int campaignId,String imagePath)
	{
		
		this.amountRaised=amountRaised;
		this.amountGot=amountGot;
		this.title=title;
		this.story=story;
		this.userName=userName;
		this.beneficiaryName=beneficiaryName;
		this.campaignId=campaignId;
		this.imagePath=imagePath;
	}
	public NewCampaignVO(int amountRaised,int amountGot,int campaignId,String imagePath,String story,String expiryDate)
	{
		this.amountRaised=amountRaised;
		this.amountGot=amountGot;
		this.campaignId=campaignId;
		this.imagePath=imagePath;
		this.story=story;
		this.expiryDate=expiryDate;
	}
	
	
	
	
	public int getAmountRaised() {
		return amountRaised;
	}
	public void setAmountRaised(int amountRaised) {
		this.amountRaised = amountRaised;
	}
	public int getAmountGot() {
		return amountGot;
	}
	public void setAmountGot(int amountGot) {
		this.amountGot = amountGot;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getNameYourFundRaiser() {
		return nameYourFundRaiser;
	}
	public void setNameYourFundRaiser(String nameYourFundRaiser) {
		this.nameYourFundRaiser = nameYourFundRaiser;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	
	
	
	
	}


