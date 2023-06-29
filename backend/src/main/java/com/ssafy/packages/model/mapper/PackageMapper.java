package com.ssafy.packages.model.mapper;

import com.ssafy.packages.model.PackageDto;
import com.ssafy.packages.model.PackageHistoryDto;
import com.ssafy.packages.model.PackageInquiryDto;
import com.ssafy.packages.model.PackageReviewDto;
import com.ssafy.util.model.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PackageMapper {
    PackageDto selectPackage(Map<String, Integer> map) throws SQLException;
    List<PackageDto> selectAllPackage(SearchCondition searchCondition) throws SQLException;
    List<PackageDto> selectListRegionPackage(Map<String, String> map) throws SQLException;
    List<PackageDto> selectListRandomPackage() throws SQLException;
    List<PackageDto> selectListRandomRegionPackage( Map<String, String> map) throws SQLException;
    void writePackage(PackageDto packageDto) throws SQLException;
    void deletePackage(int packageId) throws SQLException;
    void updatePackage(PackageDto packageDto) throws SQLException;
    void plusHeart(Map<String, Integer> map) throws SQLException;
    void minusHeart(Map<String, Integer> map) throws SQLException;
    int getPackageId(PackageDto packageDto) throws SQLException;
    void writeImg(Map<String, String> map) throws SQLException;
    void writeDescImg(Map<String, String> map) throws SQLException;
    void deleteImg(int packageId) throws SQLException;
    List<PackageInquiryDto> selectPackageInquiry(SearchCondition searchCondition) throws SQLException;
    void writePackageInquiry(PackageInquiryDto packageInquiryDto) throws SQLException;
    void updatePackageInquiry(PackageInquiryDto packageInquiryDto) throws SQLException;
    void deletePackageInquiry(int inquiryId) throws SQLException;
    void writePackageAnswer(PackageInquiryDto packageInquiryDto) throws SQLException;
    void updatePackageAnswer(PackageInquiryDto packageInquiryDto) throws SQLException;
    void deletePackageAnswer(int answerId) throws SQLException;
    int getTotalPackageCount(SearchCondition searchCondition) throws SQLException;
    int getTotalPackageInquiryCount(int packageId) throws SQLException;
    int getTotalPackageReviewCount(int packageId) throws SQLException;
    int getTotalPackageHistoryCount(int userId) throws SQLException;
    List<PackageReviewDto> selectPackageReview(SearchCondition searchCondition) throws SQLException;
    void writePackageReview(PackageReviewDto packageReviewDto) throws SQLException;
    void updatePackageReview(PackageReviewDto packageReviewDto) throws SQLException;
    void deletePackageReview(int reviewId) throws SQLException;
    void writePackageHistory(Map<String, Integer> map) throws SQLException;
    void cancelPackageHistory(int historyId) throws SQLException;
    void deletePackageHistory(int historyId) throws SQLException;
	List<PackageHistoryDto> searchPackageHistory(SearchCondition searchCondition) throws SQLException;
	int getTotalHistoryCount(SearchCondition searchCondition) throws SQLException;
}
