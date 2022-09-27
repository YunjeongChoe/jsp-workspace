package com.study.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotFoundException;
import com.study.reply.service.IReplyService;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

@RestController  //ResponseBody + Controller
public class ReplyController {
		
	@Inject
	IReplyService replyService; //@Service 붙이기 
	
	@RequestMapping(value = "/reply/replyList.wow")
	public Map<String,Object> replyList(ReplySearchVO searchVO){
		Map<String,Object> map = new HashMap<String,Object>();
		List<ReplyVO> replyList=replyService.getReplyListByParent(searchVO);
		map.put("success", true);
		map.put("result", replyList);
		System.out.println(map.toString());
		return map;
	}
	
	@RequestMapping(value = "/reply/replyRegist.wow")
	public Map<String,Object> replyRegist(ReplyVO reply){
		replyService.registReply(reply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", "등록성공");
		return map;
	}
	
	@RequestMapping(value = "/reply/replyModify.wow")
	public Map<String,Object> replyModify(ReplyVO reply) throws BizNotFoundException, BizAccessFailException{
		replyService.modifyReply(reply);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("result", "등록성공");
		return map;
	}
	
	@RequestMapping(value = "/reply/replyDelete.wow")
	public Map<String,Object> replyDelete(ReplyVO reply) throws BizNotFoundException, BizAccessFailException{
		replyService.removeReply(reply);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("result", "삭제성공");
		return map;
	}
	
	
	
}
