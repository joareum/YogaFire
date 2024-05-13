package com.yogafire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;
import com.yogafire.model.service.VideoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/video")
@Tag(name = "VideoRestController", description = "영상 관련 기능")
public class VideoRestController {

	private final VideoService videoService;

	@Autowired
	public VideoRestController(VideoService videoService) {
		this.videoService = videoService;
	}

	// 영상 리스트 전체 조회
	@GetMapping("/")
	@Operation(summary = "영상 리스트 전체 조회")
	public ResponseEntity<?> getVideoList() {
		List<Video> list = videoService.getVideoList();
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	// 영상 하나만 가져오기
	@GetMapping("/{videoKey}")
	@Operation(summary = "영상 1개 조회")
	public ResponseEntity<Video> getVideo(@PathVariable("videoKey") int videoKey) {
		Video video = videoService.getVideo(videoKey);

		if (video != null) {
			return new ResponseEntity<Video>(video, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// 영상 등록
	@PostMapping("/{videoKey}")
	@Operation(summary = "영상 등록")
	public ResponseEntity<?> uploadVideo(@RequestBody Video video, @PathVariable("videoKey") int videoKey) {
		videoService.uploadVideo(video);
		return new ResponseEntity<Video>(video, HttpStatus.CREATED);
	}

	// 영상 삭제
	@DeleteMapping("/{videoKey}")
	@Operation(summary = "영상 삭제")
	public ResponseEntity<?> removeVideo(@PathVariable("videoKey") int videoKey) {
		videoService.removeVideo(videoKey);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 영상 수정
	@PutMapping("/{videoKey}")
	@Operation(summary = "영상 수정")
	public ResponseEntity<?> modifyVideo(@PathVariable("videoKey") int videoKey, @ModelAttribute Video video) {
		video.setVideoKey(videoKey);
		videoService.modifyVideo(video);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 영상 검색하기
	@GetMapping("/detail")
	@Operation(summary = "영상 상세 검색")
	public ResponseEntity<?> searchVideo(@ModelAttribute SearchCondition condition) {
		List<Video> list = videoService.searchVideo(condition);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<>("검색된 영상이 없습니다.", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

	@GetMapping("/{videoKey}/comment")
	@Operation(summary = "영상 댓글 전체 조회")
	public ResponseEntity<?> getComments(@PathVariable("videoKey") int videoKey) {
		List<VideoComment> list = videoService.getComments(videoKey);
		return new ResponseEntity<List<VideoComment>>(list, HttpStatus.OK);
	}
	
	// 영상 댓글 작성
	@PostMapping("/{videoKey}/comment/{vCommentId}")
	@Operation(summary = "영상 댓글 작성")
//	@RequestBody Video video, , @PathVariable("vCommentId") int vCommentId
	public ResponseEntity<?> writeComment(@RequestBody VideoComment videoComment, @PathVariable("videoKey") int videoKey) {
		videoService.writeComment(videoComment);
//		videoComment.setVideoKey(videoKey);
		return new ResponseEntity<VideoComment>(videoComment, HttpStatus.OK);
	}

	// 영상 댓글 삭제
	@DeleteMapping("/{videoKey}/comment/{vCommentId}")
	@Operation(summary = "영상 댓글 삭제")
	public ResponseEntity<?> removeComment(@PathVariable("videoKey") int videoKey, @PathVariable("vCommentId") int vCommentId) {
		videoService.removeComment(vCommentId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 영상 댓글 수정
	@PutMapping("/{videoKey}/comment/{vCommentId}")
	@Operation(summary = "영상 댓글 수정")
	public ResponseEntity<?> editComment(@PathVariable("videoKey") int videoKey, @PathVariable("vCommentId") int vCommentId,
			@ModelAttribute VideoComment videoComment) {
		videoComment.setvCommentId(vCommentId);
		videoComment.setvCommentId(videoKey);
		videoService.editComment(videoComment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 영상 찜하기
	@PutMapping("/{videoKey}/like")
	@Operation(summary = "영상 찜")
	public ResponseEntity<?> likeVideo(@PathVariable("videoKey") int videoKey) {
		videoService.likeVideo(videoKey);
		return new ResponseEntity<Video>(HttpStatus.CREATED);
	}

//	// 영상 찜 해제
//	@DeleteMapping("/{videoKey}/{likeId}")
//	@Operation(summary = "영상 찜 해제")
//	public ResponseEntity<?> unlikeVideo(@PathVariable("videoKey") int videoKey, @PathVariable("likeId") int likeId) {
//		videoService.unlikeVideo(videoKey);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}

	// 찜한 영상 조회
	@GetMapping("/{userId}/like")
	@Operation(summary = "찜한 영상 리스트 조회")
	public ResponseEntity<?> getLikeList(@PathVariable("userId") String userId) {
		List<Video> list = videoService.getLikeList(userId);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<>("찜한 영상이 없습니다.", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}

}
