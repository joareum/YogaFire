package com.yogafire.model.dto;

public class VideoLike {
	private int likeId;
	private String likeYn;
	private int videoKey;
	private String userId;
	
	public VideoLike() {
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public String getLikeYn() {
		return likeYn;
	}

	public void setLikeYn(String likeYn) {
		this.likeYn = likeYn;
	}

	public int getVideoKey() {
		return videoKey;
	}

	public void setVideoKey(int videoKey) {
		this.videoKey = videoKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "VideoLike [likeId=" + likeId + ", likeYn=" + likeYn + ", videoKey=" + videoKey + ", userId=" + userId
				+ "]";
	}

}
