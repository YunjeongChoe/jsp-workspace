package com.study.free.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.study.attach.dao.IAttachDao;
import com.study.attach.vo.AttachVO;
import com.study.common.vo.PagingVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Service
public class FreeBoardServiceImpl implements IFreeBoardService {

	@Inject
	IFreeBoardDao freeBoardDao;

	@Inject
	IAttachDao attachDao;

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		int totalRowCount = freeBoardDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		// 나머지 필드 전부 세팅이 됐겠지..
		return freeBoardDao.getBoardList(searchVO);
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {

		FreeBoardVO freeBoard = freeBoardDao.getBoard(boNo);
		if (freeBoard == null) {
			throw new BizNotFoundException();
		}
		// freeboard테이블에서 freeBoard는 문제없이 get했음
		// boNo, "FREE"가지고 Attach 테이블 조회 => List<AttachVO> 받기
		// 받은 다음에 freeBoard에 set

		// result맵 쓰면 이거 필요없음
//		List<AttachVO> attaches = attachDao.getAttachListByParent(boNo, "FREE");
//		freeBoard.setAttaches(attaches);

		return freeBoard;
	}

	@Override
	public void increaseHit(int boNo) throws BizNotEffectedException {
		// BizNotEffectedException은 특별한 일 없으면 발생 안해요.

		int cnt = freeBoardDao.increaseHit(boNo);
		if (cnt == 0)
			throw new BizNotEffectedException();
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// 글 쓸 때 입력한 비밀번호(현재 DB에 있는 비밀번호)랑
		// 지금 입력한 비밀번호가 같을 때만 update

		FreeBoardVO vo = freeBoardDao.getBoard(freeBoard.getBoNo());
		if (vo == null) {
			throw new BizNotFoundException();
		}
		// DB에서 Pass는 null 아님
		if (!vo.getBoPass().equals(freeBoard.getBoPass())) {
			throw new BizPasswordNotMatchedException();
		}

		if (vo.getBoPass().equals(freeBoard.getBoPass())) {
			// vo는 DB에 있는 현재 값
			// freeBoard는 edit.jsp에서 사용자가 입력한 값
			int cnt = freeBoardDao.updateBoard(freeBoard);
			if (cnt < 1)
				throw new BizNotEffectedException();
		}
		// attach 테이블에 추가 + 삭제
		List<AttachVO> attaches = freeBoard.getAttaches();
		if (attaches != null && attaches.size() > 0) {
			for (AttachVO attach : attaches) {
				attach.setAtchParentNo(freeBoard.getBoNo());
				attachDao.insertAttach(attach);
			}
		}//추가 
		
		int[] delAtchNos=freeBoard.getDelAtchNos();
		if(delAtchNos!=null && delAtchNos.length>0) {
			attachDao.deleteAttaches(delAtchNos);
		}
	}

	@Override
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {

		FreeBoardVO vo = freeBoardDao.getBoard(freeBoard.getBoNo());
		if (vo == null) {
			throw new BizNotFoundException();
		}
		if (!vo.getBoPass().equals(freeBoard.getBoPass())) {
			throw new BizPasswordNotMatchedException();
		}
		if (vo.getBoPass().equals(freeBoard.getBoPass())) {
			int cnt = freeBoardDao.deleteBoard(freeBoard);
			if (cnt < 1)
				throw new BizNotEffectedException();
		}

	}

	@Override
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {

		int cnt = freeBoardDao.insertBoard(freeBoard);
		// freeBoard.getAttaches()하면 List<AttachVO>
		// freeBoard의 boNo는? 0인데 mybatis selectKey를 사용하면 seq_nexstval를 얻을 수 있다.
		if (cnt < 1)
			throw new BizNotEffectedException();
		List<AttachVO> attaches = freeBoard.getAttaches();
		if (attaches != null && attaches.size() > 0) {
			for (AttachVO attach : attaches) {
				attach.setAtchParentNo(freeBoard.getBoNo());
				attachDao.insertAttach(attach);
			}
		}
	}

}
