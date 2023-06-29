package com.ssafy.packages.model.service;

import com.ssafy.packages.model.PackageDto;
import com.ssafy.packages.model.PackageHistoryDto;
import com.ssafy.packages.model.PackageInquiryDto;
import com.ssafy.packages.model.PackageReviewDto;
import com.ssafy.util.model.SearchCondition;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PackageService {
    PackageDto selectPackage(Map<String, Integer> map) throws Exception;
    List<PackageDto> selectAllPackage(SearchCondition searchCondition) throws Exception;
    List<PackageDto> selectListRegionPackage(Map<String, String> map) throws Exception;
    List<PackageDto> selectListRandomPackage() throws Exception;
    List<PackageDto> selectListRandomRegionPackage(Map<String, String> map) throws Exception;
    void writePackage(PackageDto packageDto) throws Exception;
    void deletePackage(int packageId) throws Exception;
    void updatePackage(PackageDto packageDto) throws Exception;
    int getPackageId(PackageDto packageDto) throws Exception;
    void writeImg(Map<String, String> map) throws Exception;
    void writeDescImg(Map<String, String> map) throws Exception;
    void deleteImg(int packageId) throws Exception;
    void plusHeart(Map<String, Integer> map) throws Exception;
    void minusHeart(Map<String, Integer> map) throws Exception;
    List<PackageInquiryDto> selectPackageInquiry(SearchCondition searchCondition) throws Exception;
    void writePackageInquiry(PackageInquiryDto packageInquiryDto) throws Exception;
    void updatePackageInquiry(PackageInquiryDto packageInquiryDto) throws Exception;
    void deletePackageInquiry(int inquiryId) throws Exception;
    void writePackageAnswer(PackageInquiryDto packageInquiryDto) throws Exception;
    void updatePackageAnswer(PackageInquiryDto packageInquiryDto) throws Exception;
    void deletePackageAnswer(int answerId) throws Exception;
    int getTotalPackageCount(SearchCondition searchCondition) throws Exception;
    int getTotalPackageReviewCount(int packageId) throws Exception;
    int getTotalPackageInquiryCount(int packageId) throws Exception;
    int getTotalPackageHistoryCount(int userId) throws Exception;
    void writePackageHistory(Map<String, Integer> map) throws Exception;
    void cancelPackageHistory(int historyId) throws Exception;
    void deletePackageHistory(int historyId) throws Exception;
	List<PackageHistoryDto> searchPackageHistory(SearchCondition searchCondition) throws Exception;
    List<PackageReviewDto> selectPackageReview(SearchCondition searchCondition) throws Exception;
    void writePackageReview(PackageReviewDto packageReviewDto) throws Exception;
    void updatePackageReview(PackageReviewDto packageReviewDto) throws Exception;
    void deletePackageReview(int reviewId) throws Exception;
    SearchCondition makeSearchCondition(SearchCondition searchCondition) throws Exception;
	int getTotalHistoryCount(SearchCondition searchCondition) throws Exception;
}
