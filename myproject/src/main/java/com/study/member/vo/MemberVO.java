package com.study.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.study.common.valid.Step1;
import com.study.common.valid.Step2;
import com.study.common.valid.Step3;

public class MemberVO {

	//validation 동의요소
	@NotBlank(message = "이용약관 동의는 필수" ,groups = {Step1.class})
	private String agreeYn;
	@NotBlank(message = "개인정보 동의는 필수" ,groups = {Step1.class})
	private String privacyYn;

	@NotBlank(message = "아이디 입력 필수" ,groups = {Step2.class})
	private String memId;         /* 회원 아이디 */

	
	@NotBlank(message = "비밀번호 입력 필수" ,groups = {Step2.class})
	//@Pattern(regexp = "비밀번호")
	private String memPass;       /* 회원 비밀번호 */
	
	@NotBlank(message = "이름 입력 필수" ,groups = {Step2.class})
	private String memName;       /* 회원 이름 */
	
	
	@NotBlank(message = "이메일 입력 필수" ,groups = {Step2.class})
	@Email(message = "email형식이 아닙니다.",groups = {Step2.class})
	private String memMail;       /* 이메일 */

	
	@NotBlank(message = "생일 입력 필수" ,groups = {Step2.class})
	private String memBir;        /* 회원 생일 */
	
	
	@NotBlank(message = "연락처 입력 필수" ,groups = {Step2.class})
	private String memHp;         /* 연락처 */
	
	
	@NotBlank(message = "취미 입력 필수" ,groups = {Step2.class})
	private String memHobby;       /* 취미 코드 */ 
	
	
	
	private String memDelYn;     /* 탈퇴여부 */
	private String memHobbyNm;
	
 
	//private String eventYn;
	
	
	
	
	
	
	
	public String getMemDelYn() {
		return memDelYn;
	}
	public String getAgreeYn() {
		return agreeYn;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemHobby() {
		return memHobby;
	}
	public void setMemHobby(String memHobby) {
		this.memHobby = memHobby;
	}
	public String getMemHobbyNm() {
		return memHobbyNm;
	}
	public void setMemHobbyNm(String memHobbyNm) {
		this.memHobbyNm = memHobbyNm;
	}
	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}
	public String getPrivacyYn() {
		return privacyYn;
	}
	public void setPrivacyYn(String privacyYn) {
		this.privacyYn = privacyYn;
	}
	public void setMemDelYn(String memDelYn) {
		this.memDelYn = memDelYn;
	}
	
}
