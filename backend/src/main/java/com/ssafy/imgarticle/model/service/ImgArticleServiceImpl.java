package com.ssafy.imgarticle.model.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.imgarticle.model.ImgArticleDto;
import com.ssafy.imgarticle.model.mapper.ImgArticleMapper;
import com.ssafy.util.model.SearchCondition;
import com.ssafy.util.model.SizeConstant;

@Service
public class ImgArticleServiceImpl implements ImgArticleService {
	private final ImgArticleMapper imgArticleMapper;

	@Autowired
	public ImgArticleServiceImpl(ImgArticleMapper imgArticleMapper) {
		this.imgArticleMapper = imgArticleMapper;
	}

	@Override
	public ImgArticleDto selectImgArticle(Map<String, Integer> map) throws Exception {
		return imgArticleMapper.selectImgArticle(map);
	}

	@Override
	public List<ImgArticleDto> selectListRegionImgArticle(String region) throws Exception {
		return imgArticleMapper.selectListRegionImgArticle(region);
	}
	@Override
	public List<ImgArticleDto> selectListRandomImgArticle() throws Exception {
		return imgArticleMapper.selectListRandomImgArticle();
	}
	@Override
	public List<ImgArticleDto> selectListImgArticle(SearchCondition searchCondition) throws Exception {
		String key = searchCondition.getKey();
		if("name".equals(key))
			searchCondition.setKey("u.name");
		int pgNo = searchCondition.getCurrentPage();
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		searchCondition.setStartIndex(start);

		return imgArticleMapper.selectListImgArticle(searchCondition);
	}

	@Override
	public void writeImgArticle(ImgArticleDto imgArticleDto) throws Exception {
		imgArticleMapper.writeImgArticle(imgArticleDto);
		
	}

	@Override
	public void deleteImgArticle(int articleId) throws Exception {
		imgArticleMapper.deleteImgArticle(articleId);
		
	}

	@Override
	public void updateImgArticle(ImgArticleDto imgArticleDto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plusHeart(Map<String, Integer> map) throws Exception {
		imgArticleMapper.plusHeart(map);
		
	}

	@Override
	public void minusHeart(Map<String, Integer> map) throws Exception {
		imgArticleMapper.minusHeart(map);
		
	}

	@Override
	public void plusView(int articleId) throws Exception {
		imgArticleMapper.plusView(articleId);
		
	}

	@Override
	public int getTotalImgArticleCount(SearchCondition searchCondition) throws Exception {
		return imgArticleMapper.getTotalImgArticleCount(searchCondition);
	}

	@Override
	public SearchCondition makeImgArticleSearchCondition(SearchCondition searchCondition) throws Exception {
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
	public int getArticleId(ImgArticleDto imgArticleDto) throws Exception {
		return imgArticleMapper.getArticleId(imgArticleDto);
	}

	@Override
	public void writeImg(Map<String, String> map) throws Exception {
		imgArticleMapper.writeImg(map);
	}





}
