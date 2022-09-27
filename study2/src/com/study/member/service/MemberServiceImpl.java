package com.study.member.service;

import java.util.List;

import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	
	IMemberDao memberDao = new MemberDaoOracle();

	@Override
	public List<MemberVO> getMemberList() {
		return memberDao.getMemberList();
	}

	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		MemberVO member = memberDao.getMember(memId);
		if(member == null) {
			throw new BizNotFoundException();
		}
		return member;
	}

	@Override
	public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
	
			int cnt = memberDao.updateMember(member);
			if(cnt<1) {
				throw new BizNotEffectedException();
			}
			if(member == null) {
				throw new BizNotFoundException();
			}	
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		
			int cnt = memberDao.deleteMember(member);
			if(cnt<1) {
				throw new BizNotEffectedException();
			}
			//MemberVO vo = memberDao.getMember(member.getMemId());
			if(member == null) {
				throw new BizNotFoundException();
			}
			
	}

	@Override
	public void registMember(MemberVO member) throws BizNotEffectedException, BizDuplicateKeyException {
		//아이디가 중복일 때 인서트 X 중복이 아닐 때 insert
		MemberVO vo = memberDao.getMember(member.getMemId());
		if(vo == null) {
			int cnt = memberDao.insertMember(member);
			if(cnt<1) {
				throw new BizNotEffectedException();
			}
		}
		if(vo != null) {
			throw new BizDuplicateKeyException();
		}
		
		
		
	}

	
	
	
	
	
}
