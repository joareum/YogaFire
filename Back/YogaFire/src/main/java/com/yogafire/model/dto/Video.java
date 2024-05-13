package com.yogafire.model.dto;

import java.sql.Timestamp;

public class Video {
	private int videoKey;
	private String videoTitle;
	private String area;
	private String centerName;
	private int viewCnt;
	private Timestamp regDate;
//	private int vCommentId;
//	private String vCommentContent;
//	private Timestamp vCommentReg;
//	private String deleteYn;
//	private String userId;
	
	public Video() {
	}

	public int getVideoKey() {
		return videoKey;
	}

	public void setVideoKey(int videoKey) {
		this.videoKey = videoKey;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

//	public int getvCommentId() {
//		return vCommentId;
//	}
//
//	public void setvCommentId(int vCommentId) {
//		this.vCommentId = vCommentId;
//	}
//
//	public String getvCommentContent() {
//		return vCommentContent;
//	}
//
//	public void setvCommentContent(String vCommentContent) {
//		this.vCommentContent = vCommentContent;
//	}
//
//	public Timestamp getvCommentReg() {
//		return vCommentReg;
//	}
//
//	public void setvCommentReg(Timestamp vCommentReg) {
//		this.vCommentReg = vCommentReg;
//	}
//
//	public String getDeleteYn() {
//		return deleteYn;
//	}
//
//	public void setDeleteYn(String deleteYn) {
//		this.deleteYn = deleteYn;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	@Override
	public String toString() {
		return "Video [videoKey=" + videoKey + ", videoTitle=" + videoTitle + ", area=" + area + ", centerName="
				+ centerName + ", viewCnt=" + viewCnt + ", regDate=" + regDate + "]";
	}

}
