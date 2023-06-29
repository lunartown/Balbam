package com.ssafy.packages.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "PackageDto (패키지 정보)", description = "패키지 하나의 제목, 내용, 작성시간 등을 가진 Domain Class!!!!")
public class PackageDto {
	@ApiModelProperty(value = "패키지 구분자")
	private int packageId;
	@ApiModelProperty(value = "패키지 제목")
	private String title;
	@ApiModelProperty(value = "패키지 내용")
	private String content;
	@ApiModelProperty(value = "패키지 지역")
	private String region;
	@ApiModelProperty(value = "패키지 생성일")
	private String createTime;
	@ApiModelProperty(value = "가이드 구분자")
	private int guideId;
	@ApiModelProperty(value = "패키지 시작일")
	private String startDate;
	@ApiModelProperty(value = "패키지 종료일")
	private String endDate;
	@ApiModelProperty(value = "패키지 가격")
	private int price;
	@ApiModelProperty(value = "패키지 최대인원")
	private int maxHeadCount;
	@ApiModelProperty(value = "패키지 이미지")
	private List<String> img;
	@ApiModelProperty(value = "패키지 대표 이미지")
	private String mainImg;
	@ApiModelProperty(value = "패키지 설명 이미지")
	private List<String> descImg;
	@ApiModelProperty(value = "패키지 좋아요 여부")
	private boolean heart;
	@ApiModelProperty(value = "패키지 좋아요 개수")
	private int heartCnt;
	@ApiModelProperty(value = "가이드 이름")
	private String name;
	@ApiModelProperty(value = "가이드 프로필 사진")
	private String profileImg;
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getGuideId() {
		return guideId;
	}
	public void setGuideId(int guideId) {
		this.guideId = guideId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMaxHeadCount() {
		return maxHeadCount;
	}
	public void setMaxHeadCount(int maxHeadCount) {
		this.maxHeadCount = maxHeadCount;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	public boolean isHeart() {
		return heart;
	}
	public void setHeart(boolean heart) {
		this.heart = heart;
	}

	public int getHeartCnt() {
		return heartCnt;
	}

	public void setHeartCnt(int heartCnt) {
		this.heartCnt = heartCnt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public List<String> getDescImg() {
		return descImg;
	}

	public void setDescImg(List<String> descImg) {
		this.descImg = descImg;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "PackageDto{" +
				"packageId=" + packageId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", region='" + region + '\'' +
				", createTime='" + createTime + '\'' +
				", guideId=" + guideId +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", price=" + price +
				", maxHeadCount=" + maxHeadCount +
				", img=" + img +
				", mainImg='" + mainImg + '\'' +
				", descImg=" + descImg +
				", heart=" + heart +
				", heartCnt=" + heartCnt +
				", name='" + name + '\'' +
				", profileImg='" + profileImg + '\'' +
				'}';
	}
}
