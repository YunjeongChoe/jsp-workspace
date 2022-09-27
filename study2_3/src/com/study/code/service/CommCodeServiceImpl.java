package com.study.code.service;

import java.util.List;

import com.study.code.dao.ICommCodeDao;
import com.study.code.vo.CodeVO;
import com.study.code.dao.CommCodeDaoOracle;


public class CommCodeServiceImpl implements ICommCodeService{
	ICommCodeDao codeDao = new CommCodeDaoOracle();
	
	@Override
	public List<CodeVO> getCodeListByParent(String parentCode){
		return codeDao.getCodeListByParent(parentCode);
	}
	
	
	
}
