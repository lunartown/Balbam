package com.ssafy.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AttractionDto (관광지 정보)", description = "관광지에 대한 상세 정보")
public class AttractionDto {
	@ApiModelProperty(value = "관광지 고유 아이디")
	private int contentId;
	@ApiModelProperty(value = "카테고리")
	private int contentTypeId;
	@ApiModelProperty(value = "관광지 이름")
	private String title;
	@ApiModelProperty(value = "관광지 주소 1")
	private String addr1;
	@ApiModelProperty(value = "관광지 주소 2")
	private String addr2;
	@ApiModelProperty(value = "관광지 우편번호")
	private String zipCode;
	@ApiModelProperty(value = "관광지 전화번호")
	private String tel;
	@ApiModelProperty(value = "관광지 이미지1")
	private String firstImage;
	@ApiModelProperty(value = "관광지 이미지2")
	private String firstImage2;
	@ApiModelProperty(value = "관광지 조회수")
	private int readCount;
	@ApiModelProperty(value = "관광지 시도 코드")
	private int sidoCode;
	@ApiModelProperty(value = "관광지 구군 코드")
	private int gugunCode;
	@ApiModelProperty(value = "관광지 위도")
	private double latitude;
	@ApiModelProperty(value = "관광지 경도")
	private double longitude;
	@ApiModelProperty(value = "관광지 줌")
	private String mlevel;
//	@ApiModelProperty(value = "관광지 상세 분류1")
//	private String cat1;
//	@ApiModelProperty(value = "관광지 상세 분류2")
//	private String cat2;
//	@ApiModelProperty(value = "관광지 상세 분류3")
//	private String cat3;
	@ApiModelProperty(value = "관광지 분류")
	private String category;
	@ApiModelProperty(value = "관광지 설명")
	private String overview;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getFirstImage2() {
		return firstImage2;
	}

	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMlevel() {
		return mlevel;
	}

	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}

	@Override
	public String toString() {
		return "AttractionDto [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title=" + title
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipCode=" + zipCode + ", tel=" + tel + ", firstImage="
				+ firstImage + ", firstImage2=" + firstImage2 + ", readCount=" + readCount + ", sidoCode=" + sidoCode
				+ ", gugunCode=" + gugunCode + ", latitude=" + latitude + ", longitude=" + longitude + ", mlevel="
				+ mlevel + ", category=" + category + ", overview=" + overview + "]";
	}

	

}
