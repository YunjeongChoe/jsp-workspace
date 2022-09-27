package com.study.recipe.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RecipeVO {
	private int recNo;                      
	private String recTitle;                
	private String recContent;              
	private String recUrl;                  
	private String recImg;
	
	
	public String ToString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
	public int getRecNo() {
		return recNo;
	}
	public void setRecNo(int recNo) {
		this.recNo = recNo;
	}
	public String getRecTitle() {
		return recTitle;
	}
	public void setRecTitle(String recTitle) {
		this.recTitle = recTitle;
	}
	public String getRecContent() {
		return recContent;
	}
	public void setRecContent(String recContent) {
		this.recContent = recContent;
	}
	public String getRecUrl() {
		return recUrl;
	}
	public void setRecUrl(String recUrl) {
		this.recUrl = recUrl;
	}
	public String getRecImg() {
		return recImg;
	}
	public void setRecImg(String recImg) {
		this.recImg = recImg;
	}                  
	
	
}
