package com.yogafire.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogafire.model.dao.VideoDao;
import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;

@Service
public class VideoServiceImpl implements VideoService {

	private final VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	
	@Override
	public List<Video> getVideoList() {
		return videoDao.selectAll();
	}

	@Override
	public Video getVideo(int videoKey) {
		System.out.println(videoKey + "번 영상을 불러옵니다.");
		return videoDao.selectOne(videoKey);
	}

	@Override
	public void uploadVideo(Video video) {
		videoDao.insertVideo(video);
		System.out.println("영상을 등록합니다.");
	}

	@Override
	public void removeVideo(int videoKey) {
		videoDao.deleteVideo(videoKey);
		System.out.println(videoKey + "번 영상을 삭제합니다.");
	}

	@Override
	public void modifyVideo(Video video) {
		videoDao.updateVideo(video);
		System.out.println("영상을 수정합니다.");
	}

	@Override
	public List<Video> searchVideo(SearchCondition searchCondition) {
		return videoDao.searchDetail(searchCondition);
	}
	
	@Override
	public List<VideoComment> getComments(int videoKey) {
		return videoDao.selectAllComment(videoKey);
	}


	@Override
	public void writeComment(VideoComment videoComment) {
		videoDao.insertVComment(videoComment);
	}

	@Override
	public void removeComment(int vCommentId) {
		videoDao.deleteVComment(vCommentId);
	}

	@Override
	public void editComment(VideoComment videoComment) {
		videoDao.updateVComment(videoComment);
	}

	@Override
	public void likeVideo(int videoKey) {
		videoDao.like(videoKey);
	}
	
//	@Override
//	public void unlikeVideo(int videoKey) {
//		videoDao.unlike(videoKey);
//	}
	
	@Override
	public List<Video> getLikeList(String userId) {
		return videoDao.selectLike(userId);
	}

}
