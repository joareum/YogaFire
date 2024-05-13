package com.yogafire.model.service;

import java.util.List;

import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;

public interface VideoService {
	
	// 영상 리스트 전체 조회
	public List<Video> getVideoList();
	
	// 영상 하나만 가져오기
	public Video getVideo(int videoKey);

	// 영상 등록
	public void uploadVideo(Video video);
	
	// 영상 삭제
	public void removeVideo(int videoKey);
	
	// 영상 수정
	public void modifyVideo(Video video);
	
	// 영상 검색하기
	public List<Video> searchVideo(SearchCondition searchCondition);
	
	
	public List<VideoComment> getComments(int videoKey);
	
	// 영상 댓글 작성
	public void writeComment(VideoComment videoComment);
	
	// 영상 댓글 삭제
	public void removeComment(int vCommentId);
	
	// 영상 댓글 수정
	public void editComment(VideoComment videoComment);
	
	// 영상 찜하기
	public void likeVideo(int videoKey);
	
//	// 영상 찜 해제
//	public void unlikeVideo(int videoKey);
	
	// 찜한 영상 조회
	public List<Video> getLikeList(String userId);

}
