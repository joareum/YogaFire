package com.yogafire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yogafire.model.dto.User;
import com.yogafire.model.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Tag(name = "UserRestController", description = "유저 관련")
public class UserRestController {
	private UserService userService;
	
	@Autowired
	private UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> regist(@ModelAttribute User user){
		userService.signup(user);
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<User> login(@ModelAttribute User user) {
		User tmp = userService.login(user.getUserId(), user.getPassword());

		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}
	
	// 아이디 찾기
	@GetMapping("/find-account")
	public ResponseEntity<?> returnId(@ModelAttribute User user){
		String id = userService.findId(user);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
	
	// 비밀번호 찾기
	@GetMapping("/find-password")
	public ResponseEntity<?> returnPw(@ModelAttribute User user){
		String pw = userService.findPw(user);
		return new ResponseEntity<String>(pw, HttpStatus.OK);
	}
	
	// 비밀번호 재설정 - id 가져와서 설정하기
	@PutMapping("/find-password/{userId}/{newPw}")
	public ResponseEntity<Void> changePw(@PathVariable String userId, @PathVariable String newPw){
		boolean resetSuccess = userService.updatePw(userId, newPw);
		
		if(resetSuccess) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 로그아웃
	@GetMapping("/signout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 마이페이지 -> 뭐뭐 끌어올건지.(회원정보 - selectOne, 회원정보 수정, 찜 정보도..) 
	
	// 정보 수정
	@PatchMapping("/myInfo/modify-profile/{user_id}")
	public ResponseEntity<Void> modifyProfile(@PathVariable("user_id") String id, @ModelAttribute User user){
		user.setUserId(id);
		userService.modifyUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 작성한 게시글 내역 조회 -> 게시글 파트에서 불러와야 할 듯..
	
	// 회원탈퇴 -> id로 삭제하려고 해서 url에 id 받아오려고 url 수정했음
	// 그리고 도메인 설계 엑셀에 왜 put 빨간색? delete 아닌가?? -> yn만 바꾸기
	@PutMapping("/myInfo/withdraw/{user_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("user_id") String id){
		userService.outUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
