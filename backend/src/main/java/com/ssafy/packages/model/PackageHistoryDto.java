package com.ssafy.packages.model;

public class PackageHistoryDto {
	private int historyId;
	private int packageId;
	private String createTime;
	private String title;
	private int price;
	private int headcount;
	private String startDate;
	private String endDate;
	private String region;
	@Override
	public String toString() {
		return "PackageHistoryDto [historyId=" + historyId + ", createTime=" + createTime + ", title=" + title
				+ ", price=" + price + ", headcount=" + headcount + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", region=" + region + "]";
	}
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getHeadcount() {
		return headcount;
	}
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
}
