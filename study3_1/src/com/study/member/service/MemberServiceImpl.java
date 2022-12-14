package com.study.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.common.util.MybatisSqlSessionFactory;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class MemberServiceImpl  implements IMemberService{
	SqlSessionFactory SqlSessionFactory=MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		// dao에서 받은 list데이터 그대로 return
		try(SqlSession session=SqlSessionFactory.openSession(true)){
			IMemberDao memberDao=session.getMapper(IMemberDao.class);
		int totalRowCount= memberDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return memberDao.getMemberList(searchVO);
		}
	}

	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		//DAO에서 member조회 
		//member가 없으면 BizNotFoundException 던지기
		// 그 외는 해당 member return
		try(SqlSession session=SqlSessionFactory.openSession(true)){
			IMemberDao memberDao=session.getMapper(IMemberDao.class);
		MemberVO vo=memberDao.getMember(memId);
		if(vo==null) {
			throw new BizNotFoundException();
		}
		return vo;
		}
	}

	@Override
	public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		//DAO에서 member조회
		//member가 없으면 BizNotFoundException던지기
		//있으면 DB업데이트
		// 업데이트 후 업데이트 된 행수가 0이면 BizNotEffectedException
		try(SqlSession session=SqlSessionFactory.openSession(true)){
			IMemberDao memberDao=session.getMapper(IMemberDao.class);
		MemberVO vo=memberDao.getMember(member.getMemId());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		int cnt=memberDao.updateMember(member);
		if(cnt==0) throw new BizNotEffectedException();
		}
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		//DAO에서 member조회
		//member가 없으면 BizNotFoundException던지기
		//있으면 DB업데이트(삭제)
		// 업데이트 후 업데이트 된 행수가 0이면 BizNotEffectedException
		try(SqlSession session=SqlSessionFactory.openSession(true)){
			IMemberDao memberDao=session.getMapper(IMemberDao.class);
		MemberVO vo=memberDao.getMember(member.getMemId());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		int cnt=memberDao.deleteMember(member);
		if(cnt==0) throw new BizNotEffectedException();
		}
	}

	@Override
	public void registMember(MemberVO member) throws BizNotEffectedException, BizDuplicateKeyException {
		//회원가입 
		//memId는 PK, 그래서 가입하려고 할 때 DB에 있는지 조회
		//DB에 있다는건 이미있는 아이디 => BizDuplicateKeyException
		//없으면 insert 
		//insert 후 업데이트 된 행수가 0이면 BizNotEffectedException
		try(SqlSession session=SqlSessionFactory.openSession(true)){
			IMemberDao memberDao=session.getMapper(IMemberDao.class);
		MemberVO vo=memberDao.getMember(member.getMemId());
		if(vo!=null) {
			throw new BizDuplicateKeyException();
		}
		int cnt=memberDao.insertMember(member);
		if(cnt==0) throw new BizNotEffectedException();
		}
		
	}
	
}
