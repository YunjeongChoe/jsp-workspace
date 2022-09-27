package com.study.recipe.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.common.vo.PagingVO;
import com.study.recipe.dao.IRecipeDao;
import com.study.recipe.vo.RecipeSearchVO;
import com.study.recipe.vo.RecipeVO;

@Controller
public class RecipeController {
	
	@Inject
	IRecipeDao recipeDao;
	
	@RequestMapping("/vege/recipe.wow")
	public String getRecipeList(Model model, @ModelAttribute("searchVO")RecipeSearchVO searchVO) {
		int totoalRowCount = recipeDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totoalRowCount);
		searchVO.pageSetting();
		List<RecipeVO> recipeList = recipeDao.getRecipeList(searchVO);
		model.addAttribute("recipeList", recipeList);
		return "vege/recipe";
		
	}
}
