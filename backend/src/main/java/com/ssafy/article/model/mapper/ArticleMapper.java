package com.ssafy.article.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ssafy.util.model.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.article.model.ArticleCommentDto;
import com.ssafy.article.model.ArticleDto;

@Mapper
public interface ArticleMapper {
	ArticleDto selectArticle(Map<String, Integer> map) throws SQLException;
	List<ArticleDto> selectAllArticle(SearchCondition searchCondition) throws SQLException;
	void writeArticle(ArticleDto articleDto) throws SQLException;
	void deleteArticle(int articleId) throws SQLException;
	void updateArticle(ArticleDto articleDto) throws SQLException;
	List<ArticleCommentDto> selectArticleComment(SearchCondition searchCondition) throws SQLException;
	void writeComment(ArticleCommentDto articleCommentDto) throws SQLException;
	void updateComment(ArticleCommentDto articleCommentDto) throws SQLException;
	void deleteComment(int commentId) throws SQLException;
	void plusHeart(Map<String, Integer> map) throws SQLException;
	void minusHeart(Map<String, Integer> map) throws SQLException;
	void plusView(int articleId) throws SQLException;
	int getTotalArticleCount(SearchCondition searchCondition) throws SQLException;
	int getTotalCommentCount(int articleId) throws SQLException;
}
