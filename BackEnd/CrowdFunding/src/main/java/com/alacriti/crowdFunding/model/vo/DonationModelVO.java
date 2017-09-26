package com.alacriti.crowdFunding.model.vo;

public class DonationModelVO {
	private int userId;
	private int campaignId;
	private int amountDonated;
	private String comments;
	private String debitCardNo;
	private int month;
	private int year;
	private String cvv;
	private String nameOnCard;
	private String accountNo;
	private String ifscCode;
	private String nameOnBook;
	private String name;
	public DonationModelVO(){}
	
	public DonationModelVO(int campaignId, int amountDonated,
			String comments, String debitCardNo, int month, int year,
			String cvv, String nameOnCard, String accountNo, String ifscCode,
			String nameOnBook,String name) {
		super();
		this.campaignId = campaignId;
		this.amountDonated = amountDonated;
		this.comments = comments;
		this.debitCardNo = debitCardNo;
		this.month = month;
		this.year = year;
		this.cvv = cvv;
		this.nameOnCard = nameOnCard;
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.nameOnBook = nameOnBook;
		this.name=name;
	}
	
	
	public DonationModelVO(int campaignId, int amountDonated,
			String comments,String name) {
		super();
		
		this.campaignId = campaignId;
		this.amountDonated = amountDonated;
		this.comments = comments;
		this.name=name;
	}
	
	

	public int getUerId() {
		return userId;
	}
	public void setUerId(int uerId) {
		this.userId = uerId;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public int getAmountDonated() {
		return amountDonated;
	}
	public void setAmountDonated(int amountDonated) {
		this.amountDonated = amountDonated;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDebitCardNo() {
		return debitCardNo;
	}
	public void setDebitCardNo(String debitCardNo) {
		this.debitCardNo = debitCardNo;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getNameOnBook() {
		return nameOnBook;
	}
	public void setNameOnBook(String nameOnBook) {
		this.nameOnBook = nameOnBook;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	}
