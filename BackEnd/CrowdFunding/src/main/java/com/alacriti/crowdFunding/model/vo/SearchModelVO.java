package com.alacriti.crowdFunding.model.vo;

public class SearchModelVO {
private String text;
private String date;


public SearchModelVO(String text, String date) {
	super();
	this.text = text;
	this.date = date;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}

