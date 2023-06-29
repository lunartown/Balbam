package com.ssafy.article.model.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.article.model.ArticleCommentDto;
import com.ssafy.article.model.ArticleDto;
import com.ssafy.article.model.mapper.ArticleMapper;
import com.ssafy.util.model.SearchCondition;
import com.ssafy.util.model.SizeConstant;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	private final ArticleMapper articleMapper;

	@Autowired
	public ArticleServiceImpl(ArticleMapper articleMapper) {
		this.articleMapper = articleMapper;
	}

	@Override
	public ArticleDto selectArticle(Map<String, Integer> map) throws Exception {
		return articleMapper.selectArticle(map);
	}

	@Override
	public List<ArticleDto> selectAllArticle(SearchCondition searchCondition) throws Exception {
		String key = searchCondition.getKey();
		if("name".equals(key))
			searchCondition.setKey("u.name");
		int pgNo = searchCondition.getCurrentPage();
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		searchCondition.setStartIndex(start);

		return articleMapper.selectAllArticle(searchCondition);
	}

	@Override
	@Transactional
	public void writeArticle(ArticleDto articleDto) throws Exception {
		articleMapper.writeArticle(articleDto);
	}

	@Override
	public void deleteArticle(int articleId) throws Exception {
		articleMapper.deleteArticle(articleId);
	}

	@Override
	public void updateArticle(ArticleDto articleDto) throws Exception {
		articleMapper.updateArticle(articleDto);
	}

	@Override
	public List<ArticleCommentDto> selectArticleComment(SearchCondition searchCondition) throws Exception {
		int pgNo = searchCondition.getCurrentPage();
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		searchCondition.setStartIndex(start);
		return articleMapper.selectArticleComment(searchCondition);
	}

	@Override
	public void writeComment(ArticleCommentDto articleCommentDto) throws Exception {
		articleMapper.writeComment(articleCommentDto);
	}

	@Override
	public void updateComment(ArticleCommentDto articleCommentDto) throws Exception {
		articleMapper.updateComment(articleCommentDto);
	}

	@Override
	public void deleteComment(int commentId) throws Exception {
		articleMapper.deleteComment(commentId);
	}

	@Override
	public void plusHeart(Map<String, Integer> map) throws Exception {
		articleMapper.plusHeart(map);
	}

	@Override
	public void minusHeart(Map<String, Integer> map) throws Exception {
		articleMapper.minusHeart(map);
	}

	@Override
	public void plusView(int articleId) throws Exception {
		articleMapper.plusView(articleId);
	}

	@Override
	public int getTotalArticleCount(SearchCondition searchCondition) throws Exception {
		return articleMapper.getTotalArticleCount(searchCondition);
	}
	@Override
	public int getTotalCommentCount(int articleId) throws Exception {
		return articleMapper.getTotalCommentCount(articleId);
	}

	@Override
	public SearchCondition makeArticleSearchCondition(SearchCondition searchCondition) throws Exception {
		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;

		searchCondition.setNaviSize(naviSize);
		if ("userid".equals(searchCondition.getKey()))
			searchCondition.setKey("user_id");
		int totalCount = searchCondition.getTotalCount();
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		searchCondition.setTotalPageCount(totalPageCount);
		boolean startRange = searchCondition.getCurrentPage() <= naviSize;
		searchCondition.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < searchCondition.getCurrentPage();
		searchCondition.setEndRange(endRange);

		return searchCondition;
	}

	@Override
	public SearchCondition makeCommentSearchCondition(SearchCondition searchCondition) throws Exception {

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;

		searchCondition.setCurrentPage(searchCondition.getCurrentPage());
		searchCondition.setNaviSize(naviSize);
		int totalCount = searchCondition.getTotalCount();
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		searchCondition.setTotalPageCount(totalPageCount);
		boolean startRange = searchCondition.getCurrentPage() <= naviSize;
		searchCondition.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < searchCondition.getCurrentPage();
		searchCondition.setEndRange(endRange);

		return searchCondition;
	}

}
