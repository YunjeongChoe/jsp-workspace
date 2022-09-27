
package com.study.free.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.study.free.vo.FreeBoardVO;

@Mapper
public interface IFreeBoardDao {
	
	public List<FreeBoardVO> getBoardList();

}