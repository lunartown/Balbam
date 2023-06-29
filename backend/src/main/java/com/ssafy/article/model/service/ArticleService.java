package com.ssafy.article.model.service;

import java.util.List;
import java.util.Map;
import com.ssafy.article.model.ArticleCommentDto;
import com.ssafy.article.model.ArticleDto;
import com.ssafy.util.model.SearchCondition;

public interface ArticleService {
	ArticleDto selectArticle(Map<String, Integer> map) throws Exception;
	List<ArticleDto> selectAllArticle(SearchCondition searchCondition) throws Exception;
	void writeArticle(ArticleDto articleDto) throws Exception;
	void deleteArticle(int articleId) throws Exception;
	void updateArticle(ArticleDto articleDto) throws Exception;
	List<ArticleCommentDto> selectArticleComment(SearchCondition searchCondition) throws Exception;
	void writeComment(ArticleCommentDto articleCommentDto) throws Exception;
	void updateComment(ArticleCommentDto articleCommentDto) throws Exception;
	void deleteComment(int commentId) throws Exception;
	void plusHeart(Map<String, Integer> map) throws Exception;
	void minusHeart(Map<String, Integer> map) throws Exception;
	void plusView(int articleId) throws Exception;
	int getTotalArticleCount(SearchCondition searchCondition ) throws Exception;
	int getTotalCommentCount(int articleId ) throws Exception;
	SearchCondition makeArticleSearchCondition(SearchCondition searchCondition) throws Exception;
	SearchCondition makeCommentSearchCondition(SearchCondition searchCondition) throws Exception;
}
