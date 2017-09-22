package com.alacriti.crowdFunding.model.vo;

public class CategoriesModelVO {
	private int categoryId;
	private String categoryName;
	
	public CategoriesModelVO() {
		super();
	}

	public CategoriesModelVO(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
