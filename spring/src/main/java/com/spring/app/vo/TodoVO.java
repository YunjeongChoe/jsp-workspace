package com.spring.app.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TodoVO {
	
	private int tdNo;                       
	private String tdTitle;                 
	private String tdContent;               
	private String tdWriter;                
	private String tdDate;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public int getTdNo() {
		return tdNo;
	}
	public void setTdNo(int tdNo) {
		this.tdNo = tdNo;
	}
	public String getTdTitle() {
		return tdTitle;
	}
	public void setTdTitle(String tdTitle) {
		this.tdTitle = tdTitle;
	}
	public String getTdContent() {
		return tdContent;
	}
	public void setTdContent(String tdContent) {
		this.tdContent = tdContent;
	}
	public String getTdWriter() {
		return tdWriter;
	}
	public void setTdWriter(String tdWriter) {
		this.tdWriter = tdWriter;
	}
	public String getTdDate() {
		return tdDate;
	}
	public void setTdDate(String tdDate) {
		this.tdDate = tdDate;
	}    
	
	
}
