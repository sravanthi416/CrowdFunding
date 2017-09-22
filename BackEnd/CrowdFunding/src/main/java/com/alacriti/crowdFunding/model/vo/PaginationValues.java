package com.alacriti.crowdFunding.model.vo;

public class PaginationValues {
	private int start;
	private int end;
	private int categoryId;
	public PaginationValues(){}
	
	public PaginationValues(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	public PaginationValues(int categoryId,int start, int end) {
		super();
		this.categoryId=categoryId;
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	

}
