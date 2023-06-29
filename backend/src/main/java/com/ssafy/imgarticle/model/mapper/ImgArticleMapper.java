package com.ssafy.imgarticle.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.imgarticle.model.ImgArticleDto;
import com.ssafy.util.model.SearchCondition;

@Mapper
public interface ImgArticleMapper {
	ImgArticleDto selectImgArticle(Map<String, Integer> map) throws SQLException;
	List<ImgArticleDto> selectListRegionImgArticle(String region) throws SQLException;
	List<ImgArticleDto> selectListImgArticle(SearchCondition searchCondition) throws SQLException;
	List<ImgArticleDto> selectListRandomImgArticle() throws SQLException;
	void writeImgArticle(ImgArticleDto articleDto) throws SQLException;
	void deleteImgArticle(int articleId) throws SQLException;
	void updateImgArticle(ImgArticleDto articleDto) throws SQLException;
	void plusHeart(Map<String, Integer> map) throws SQLException;
	void minusHeart(Map<String, Integer> map) throws SQLException;
	void plusView(int articleId) throws SQLException;
	int getTotalImgArticleCount(SearchCondition searchCondition) throws SQLException;
	int getArticleId(ImgArticleDto imgArticleDto) throws SQLException;
	void writeImg(Map<String, String> map) throws SQLException;
}
