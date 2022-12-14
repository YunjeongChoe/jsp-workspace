package com.study.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

@Service
public class MemberServiceImpl  implements IMemberService{
	
	@Inject
	IMemberDao memberDao;
	
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		int totalRowCount= memberDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return memberDao.getMemberList(searchVO);
		}
	

	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		MemberVO vo=memberDao.getMember(memId);
		if(vo==null) {
			throw new BizNotFoundException();
		}
		return vo;
		
	}

	@Override
	public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {

		MemberVO vo=memberDao.getMember(member.getMemId());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		int cnt=memberDao.updateMember(member);
		if(cnt==0) throw new BizNotEffectedException();
		
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {

		MemberVO vo=memberDao.getMember(member.getMemId());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		int cnt=memberDao.deleteMember(member);
		if(cnt==0) throw new BizNotEffectedException();
		
	}

	@Override
	public void registMember(MemberVO member) throws BizNotEffectedException, BizDuplicateKeyException {

		MemberVO vo=memberDao.getMember(member.getMemId());
		if(vo!=null) {
			throw new BizDuplicateKeyException();
		}
		int cnt=memberDao.insertMember(member);
		if(cnt==0) throw new BizNotEffectedException();
		
		
	}
	
	@Override
	public String getAccessToken (String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
            
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST ????????? ?????? ???????????? false??? setDoOutput??? true???
            
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST ????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
            
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
            
			sb.append("&client_id=b792c927c88597449803f7ffaeb0fafa"); //????????? ???????????? key
			sb.append("&redirect_uri=http://localhost:8080/home/login/kakaoLogin"); // ????????? ????????? ??????
            
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
            
			// ?????? ????????? 200????????? ??????
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
            
			// ????????? ?????? ?????? JSON????????? Response ????????? ????????????
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
            
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
            
			// Gson ?????????????????? ????????? ???????????? JSON?????? ?????? ??????
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
            
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
            
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}
	
	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) {

		// ???????????? ????????????????????? ?????? ????????? ?????? ??? ????????? HashMap???????????? ??????
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// ????????? ????????? Header??? ????????? ??????
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			System.out.println("properties:"+properties);
			System.out.println("kakao_account:"+kakao_account);
//			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//			String email = kakao_account.getAsJsonObject().get("email").getAsString();
//
//			userInfo.put("nickname", nickname);
//			userInfo.put("email", email);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	
	
	
}
