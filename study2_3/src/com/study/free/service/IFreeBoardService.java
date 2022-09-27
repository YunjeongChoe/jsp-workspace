package com.study.free.service;

import java.util.List;

import com.study.common.vo.PagingVO;

//javadoc
	/**
	 * 	free_board테이블에서 (조건에 맞는) 데이터만 가져오는 메소드
	 * @author 한창희
	 * @param 아직은 없지만 나중에 FreeBoardSearchVO
	 * @return List<FreeBoardVO>
	 * 
	 * 
	 * 
	 */

import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;

import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public interface IFreeBoardService {
	
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO);
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException;
	
	public void increaseHit(int boNo) throws BizNotEffectedException;
	
	public void modifyBoard(FreeBoardVO freeBoard) 
			throws BizNotFoundException,BizPasswordNotMatchedException, BizNotEffectedException ;
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException,BizPasswordNotMatchedException, BizNotEffectedException ;
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException;

}
