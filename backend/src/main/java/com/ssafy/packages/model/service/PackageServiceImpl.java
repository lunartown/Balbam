package com.ssafy.packages.model.service;

import com.ssafy.packages.model.PackageDto;
import com.ssafy.packages.model.PackageHistoryDto;
import com.ssafy.packages.model.PackageInquiryDto;
import com.ssafy.packages.model.PackageReviewDto;
import com.ssafy.packages.model.mapper.PackageMapper;
import com.ssafy.util.model.SearchCondition;
import com.ssafy.util.model.SizeConstant;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PackageServiceImpl implements PackageService {
    private final PackageMapper packageMapper;

    public PackageServiceImpl(PackageMapper packageMapper) {
        this.packageMapper = packageMapper;
    }

    @Override
    public PackageDto selectPackage(Map<String, Integer> map) throws Exception {
        return packageMapper.selectPackage(map);
    }

    @Override
    public List<PackageDto> selectAllPackage(SearchCondition searchCondition) throws Exception {
        String key = searchCondition.getKey();
        if("guide_id".equals(key))
            searchCondition.setKey("u.guide_id");
        int pgNo = searchCondition.getCurrentPage();
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        searchCondition.setStartIndex(start);

        return packageMapper.selectAllPackage(searchCondition);
    }

    @Override
    public List<PackageDto> selectListRegionPackage(Map<String, String> map) throws Exception {
        return packageMapper.selectListRegionPackage(map);
    }
    

    @Override
	public List<PackageDto> selectListRandomPackage() throws Exception {
		return packageMapper.selectListRandomPackage();
	}
    
	@Override
	public List<PackageDto> selectListRandomRegionPackage(Map<String, String> map) throws Exception {
		return packageMapper.selectListRandomRegionPackage(map);
	}

	@Override
    public void writePackage(PackageDto packageDto) throws Exception {
        packageMapper.writePackage(packageDto);
    }

    @Override
    public void deletePackage(int packageId) throws Exception {
        packageMapper.deletePackage(packageId);
    }

    @Override
    public void updatePackage(PackageDto packageDto) throws Exception {
        packageMapper.updatePackage(packageDto);
    }
	@Override
	public int getPackageId(PackageDto packageDto) throws Exception {
		return packageMapper.getPackageId(packageDto);
	}
    @Override
    public void writeImg(Map<String, String> map) throws Exception{
    	packageMapper.writeImg(map);
    }
    @Override
    public void writeDescImg(Map<String, String> map) throws Exception{
    	packageMapper.writeDescImg(map);
    }

    @Override
    public void deleteImg(int packageId) throws Exception {

    }

    @Override
    public void plusHeart(Map<String, Integer> map) throws Exception {
        packageMapper.plusHeart(map);
    }

    @Override
    public void minusHeart(Map<String, Integer> map) throws Exception {
        packageMapper.minusHeart(map);
    }

    @Override
    public List<PackageInquiryDto> selectPackageInquiry(SearchCondition searchCondition) throws Exception {
        int pgNo = searchCondition.getCurrentPage();
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        searchCondition.setStartIndex(start);
        return packageMapper.selectPackageInquiry(searchCondition);
    }

    @Override
    public void writePackageInquiry(PackageInquiryDto packageInquiryDto) throws Exception {
        packageMapper.writePackageInquiry(packageInquiryDto);
    }

    @Override
    public void updatePackageInquiry(PackageInquiryDto packageInquiryDto) throws Exception {
        packageMapper.updatePackageInquiry(packageInquiryDto);
    }

    @Override
    public void deletePackageInquiry(int inquiryId) throws Exception {
        packageMapper.deletePackageInquiry(inquiryId);
    }

    @Override
    public void writePackageAnswer(PackageInquiryDto packageInquiryDto) throws Exception {
        packageMapper.writePackageAnswer(packageInquiryDto);
    }

    @Override
    public void updatePackageAnswer(PackageInquiryDto packageInquiryDto) throws Exception {
        packageMapper.updatePackageAnswer(packageInquiryDto);
    }

    @Override
    public void deletePackageAnswer(int answerId) throws Exception {
        packageMapper.deletePackageAnswer(answerId);
    }

    @Override
    public int getTotalPackageCount(SearchCondition searchCondition) throws Exception {
        return packageMapper.getTotalPackageCount(searchCondition);
    }

    @Override
    public int getTotalPackageInquiryCount(int packageId) throws Exception {
        return packageMapper.getTotalPackageInquiryCount(packageId);
    }

    @Override
    public int getTotalPackageHistoryCount(int userId) throws Exception {
        return packageMapper.getTotalPackageHistoryCount(userId);
    }

    @Override
    public void writePackageHistory(Map<String, Integer> map) throws Exception {
        packageMapper.writePackageHistory(map);
    }

    @Override
    public int getTotalPackageReviewCount(int packageId) throws Exception {
        return packageMapper.getTotalPackageReviewCount(packageId);
    }
    
    @Override
    public int getTotalHistoryCount(SearchCondition searchCondition) throws Exception {
        return packageMapper.getTotalHistoryCount(searchCondition);
    }

    @Override
    public void deletePackageHistory(int historyId) throws Exception {
        packageMapper.deletePackageHistory(historyId);
    }

    @Override
    public List<PackageReviewDto> selectPackageReview(SearchCondition searchCondition) throws Exception {
        int pgNo = searchCondition.getCurrentPage();
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        searchCondition.setStartIndex(start);
        return packageMapper.selectPackageReview(searchCondition);
    }

    @Override
    public void writePackageReview(PackageReviewDto packageReviewDto) throws Exception {
        packageMapper.writePackageReview(packageReviewDto);
    }

    @Override
    public void updatePackageReview(PackageReviewDto packageReviewDto) throws Exception {
        packageMapper.updatePackageReview(packageReviewDto);
    }

    @Override
    public void deletePackageReview(int reviewId) throws Exception {
        packageMapper.deletePackageReview(reviewId);
    }

    @Override
    public SearchCondition makeSearchCondition(SearchCondition searchCondition) throws Exception {
        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;

        searchCondition.setCurrentPage(searchCondition.getCurrentPage());
        searchCondition.setNaviSize(naviSize);
        int totalCount = searchCondition.getTotalCount();
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        searchCondition.setTotalPageCount(totalPageCount);
        searchCondition.setTotalCount(totalCount);
        boolean startRange = searchCondition.getCurrentPage() <= naviSize;
        searchCondition.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < searchCondition.getCurrentPage();
        searchCondition.setEndRange(endRange);

        return searchCondition;
    }

	@Override
	public void cancelPackageHistory(int historyId) throws Exception {
		packageMapper.cancelPackageHistory(historyId);		
	}

	@Override
	public List<PackageHistoryDto> searchPackageHistory(SearchCondition searchCondition) throws Exception {
		String key = searchCondition.getKey();
        if("history_id".equals(key))
            searchCondition.setKey("h.history_id");
        int pgNo = searchCondition.getCurrentPage();
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        searchCondition.setStartIndex(start);

        return packageMapper.searchPackageHistory(searchCondition);
	}
}
