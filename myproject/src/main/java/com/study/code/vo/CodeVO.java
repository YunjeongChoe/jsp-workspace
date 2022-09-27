package com.study.code.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@Lombok
public class CodeVO {
	private String cateCd;                               /*코드*/
	private String cateNm;                               /*코드명*/
	private String cateParent;                           /*부모 코드*/
	private int cateOrd;                                 /*순번*/
	
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}



	public String getCateCd() {
		return cateCd;
	}



	public void setCateCd(String cateCd) {
		this.cateCd = cateCd;
	}



	public String getCateNm() {
		return cateNm;
	}



	public void setCateNm(String cateNm) {
		this.cateNm = cateNm;
	}



	public String getCateParent() {
		return cateParent;
	}



	public void setCateParent(String cateParent) {
		this.cateParent = cateParent;
	}



	public int getCateOrd() {
		return cateOrd;
	}



	public void setCateOrd(int cateOrd) {
		this.cateOrd = cateOrd;
	}


	
	
	
	
	
}
