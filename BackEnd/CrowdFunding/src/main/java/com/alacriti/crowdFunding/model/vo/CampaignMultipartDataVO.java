package com.alacriti.crowdFunding.model.vo;

import java.util.List;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class CampaignMultipartDataVO {

	public int amountRaised;
	public String title;
	public String category;
	public String beneficiaryName;
	public String nameYourFundRaiser;
	public String fileType;
	public String story;
	public String expiryDate;
	public byte[] file;
	public String friends;
	
	public int getAmountRaised() {
		return amountRaised;
	}
	@FormParam("amountRaised")
	public void setAmountRaised(int amountRaised) {
		this.amountRaised = amountRaised;
	}
	public String getTitle() {
		return title;
	}
	@FormParam("title")
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	@FormParam("category")
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	@FormParam("beneficiaryName")
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getNameYourFundRaiser() {
		return nameYourFundRaiser;
	}
	@FormParam("nameYourFundRaiser")
	public void setNameYourFundRaiser(String nameYourFundRaiser) {
		this.nameYourFundRaiser = nameYourFundRaiser;
	}
	public String getFileType() {
		return fileType;
	}
	@FormParam("fileType")
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getStory() {
		return story;
	}
	@FormParam("story")
	public void setStory(String story) {
		this.story = story;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	@FormParam("expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public byte[] getFile() {
		return file;
	}
	
	@FormParam("file")
	@PartType("application/octet-stream")
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
	
}
