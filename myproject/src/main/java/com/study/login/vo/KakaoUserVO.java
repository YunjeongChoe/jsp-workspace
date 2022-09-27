package com.study.login.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class KakaoUserVO {
	private String kakaoId;
	private String kakaoName;
	private String kakaoMail;
	
	public KakaoUserVO() {
		
	}
	
	public String ToString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getKakaoId() {
		return kakaoId;
	}

	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}

	public String getKakaoName() {
		return kakaoName;
	}

	public void setKakaoName(String kakaoName) {
		this.kakaoName = kakaoName;
	}

	public String getKakaoMail() {
		return kakaoMail;
	}

	public void setKakaoMail(String kakaoMail) {
		this.kakaoMail = kakaoMail;
	}
	
	
}
