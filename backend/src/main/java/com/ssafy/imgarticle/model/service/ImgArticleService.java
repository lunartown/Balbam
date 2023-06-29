package com.ssafy.imgarticle.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ssafy.imgarticle.model.ImgArticleDto;
import com.ssafy.util.model.SearchCondition;

public interface ImgArticleService {
	ImgArticleDto selectImgArticle(Map<String, Integer> map) throws Exception;
	List<ImgArticleDto> selectListRegionImgArticle(String region) throws Exception;
	List<ImgArticleDto> selectListImgArticle(SearchCondition searchCondition) throws Exception;
	List<ImgArticleDto> selectListRandomImgArticle() throws Exception;
	void writeImgArticle(ImgArticleDto imgArticleDto) throws Exception;
	void deleteImgArticle(int articleId) throws Exception;
	void updateImgArticle(ImgArticleDto imgArticleDto) throws Exception;
	void plusHeart(Map<String, Integer> map) throws Exception;
	void minusHeart(Map<String, Integer> map) throws Exception;
	void plusView(int articleId) throws Exception;
	int getTotalImgArticleCount(SearchCondition searchCondition) throws Exception;
	SearchCondition makeImgArticleSearchCondition(SearchCondition searchCondition) throws Exception;
	int getArticleId(ImgArticleDto imgArticleDto) throws Exception;
	void writeImg(Map<String, String> map) throws Exception;
}
