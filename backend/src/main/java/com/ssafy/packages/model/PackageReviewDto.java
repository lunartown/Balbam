package com.ssafy.packages.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PackageReviewDto (패키지 리뷰 정보)", description = "패키지 리뷰의 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class PackageReviewDto {
    @ApiModelProperty(value = "리뷰 구분자")
    private int reviewId;
    @ApiModelProperty(value = "패키지 구분자")
    private int packageId;
    @ApiModelProperty(value = "작성자 구분자")
    private int userId;
    @ApiModelProperty(value = "리뷰 내용")
    private String content;
    @ApiModelProperty(value = "리뷰 작성일")
    private String createTime;
    @ApiModelProperty(value = "리뷰 작성자 이름")
    private String name;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PackageReviewDto{" +
                "reviewId=" + reviewId +
                ", packageId=" + packageId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
