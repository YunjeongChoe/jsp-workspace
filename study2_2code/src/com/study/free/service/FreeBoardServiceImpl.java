package com.study.free.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.exception.DaoException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;

public class FreeBoardServiceImpl implements IFreeBoardService {

	
	IFreeBoardDao freeBoardDao = new FreeBoardDaoOracle();
	
	
	@Override
	public List<FreeBoardVO> getBoardList() {
		return freeBoardDao.getBoardList();
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
		FreeBoardVO freeBoard = freeBoardDao.getBoard(boNo);
		if(freeBoard == null) {
			throw new BizNotFoundException();
		}
		return freeBoard;
	}

	@Override
	public void increaseHit(int boNo) throws BizNotEffectedException {
		// BizNotEffectedException 특별한 일 없으면 발생 안함
		
		int cnt = freeBoardDao.increaseHit(boNo);
		if(cnt == 0) throw new BizNotEffectedException();
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		//글 쓸 때 입력한 비밀번호(현재 DB에 있는 비밀번호)랑 
		//지금 입력한 비밀번호가 같을 때만 update
		FreeBoardVO vo = freeBoardDao.getBoard(freeBoard.getBoNo());
		if(vo == null) {
			throw new BizNotFoundException();
		}
		//DB에서 Pass는 null아님 
		if(!vo.getBoPass().equals(freeBoard.getBoPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		if(vo.getBoPass().equals(freeBoard.getBoPass())) {
			//vo는 DB에 있는 현재 값
			//freeBoard는 edit.jsp에서 사용자가 입력한 값
			int cnt = freeBoardDao.updateBoard(freeBoard);
			if(cnt<1) throw new BizNotEffectedException();

		}
		
	}
	
	@Override
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO vo = freeBoardDao.getBoard(freeBoard.getBoNo());
		if(vo == null) {
			throw new BizNotFoundException();
		}
		//DB에서 Pass는 null아님 
		if(!vo.getBoPass().equals(freeBoard.getBoPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		if(vo.getBoPass().equals(freeBoard.getBoPass())) {
			//vo는 DB에 있는 현재 값
			//freeBoard는 edit.jsp에서 사용자가 입력한 값
			int cnt = freeBoardDao.deleteBoard(freeBoard);
			if(cnt<1) throw new BizNotEffectedException();

		}
		
	}

	@Override
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		int cnt = freeBoardDao.insertBoard(freeBoard);
		if(cnt<1) throw new BizNotEffectedException();
		
	}

}
