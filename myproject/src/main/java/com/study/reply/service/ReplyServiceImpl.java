package com.study.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotFoundException;
import com.study.reply.dao.IReplyDao;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService {

	
	@Inject
	IReplyDao replyDao;
	
	@Override
	public List<ReplyVO> getReplyListByParent(ReplySearchVO searchVO) {
		int totalRowCount = replyDao.getReplyCountByParent(searchVO);
		System.out.println(totalRowCount);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		System.out.println(searchVO);
		List<ReplyVO> replyList = replyDao.getReplyListByParent(searchVO);
		return replyList;
		
	}

	@Override
	public void modifyReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {
		ReplyVO vo = replyDao.getReply(reply.getReNo()); //vo는 DB에 있는거. reply는 파라미터로 넘어온거
		if(vo == null) {
			throw new BizNotFoundException();
		}
		if(!vo.getReMemId().equals(reply.getReMemId())) {
			throw new BizAccessFailException();
		}
		replyDao.updateReply(reply);
	}

	@Override
	public void removeReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {
		ReplyVO vo = replyDao.getReply(reply.getReNo()); //vo는 DB에 있는거. reply는 파라미터로 넘어온거
		if(vo == null) {
			throw new BizNotFoundException();
		}
		if(!vo.getReMemId().equals(reply.getReMemId())) {
			throw new BizAccessFailException();
		}
		replyDao.deleteReply(reply);

	}

	@Override
	public void registReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		int cnt = replyDao.insertReply(reply);
	}

}
