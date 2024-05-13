package com.yogafire.model.dao;

import java.util.List;

import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;

public interface VideoDao {

	public List<Video> selectAll();

	public Video selectOne(int videoKey);

	public void insertVideo(Video video);

	public void deleteVideo(int videoKey);

	public void updateVideo(Video video);

	public List<Video> searchDetail(SearchCondition searchCondition);

	public List<VideoComment> selectAllComment(int videoKey);
	
	public void insertVComment(VideoComment videoComment);

	public void deleteVComment(int vCommentId);

	public void updateVComment(VideoComment videoComment);

	public void like(int videoKey);

//	public void unlike(int videoKey);

	public List<Video> selectLike(String userId);

}
