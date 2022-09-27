package com.study.recipe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.common.vo.PagingVO;
import com.study.recipe.vo.RecipeSearchVO;
import com.study.recipe.vo.RecipeVO;

@Mapper
public interface IRecipeDao {


	public int getTotalRowCount(RecipeSearchVO searchVO);
	public List<RecipeVO> getRecipeList(RecipeSearchVO searchVO);

}
