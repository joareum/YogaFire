package com.yogafire.model.dao;

import java.util.List;
import java.util.Map;

import com.yogafire.model.dto.User;

public interface UserDao {
	// 회원 가입(등록)
	public void insertUser(User user);
	
	// 회원 정보 수정 ( 비밀번호 재설정에도 사용 ) 
	// -> mapper가 비번만 업데이트 하는거랑 다를 수도 있어서 dao 따로 만들어야 할 수도 있음.
	public void updateUser(User user);
	
	// 회원 한명 선택 ( 로그인 & 비밀번호 재설정 때 활용 )
	public User selectOne(Map<String, String> info);
	
	// 아이디 찾기 ???
	public String findId(User user);
	
	// 비밀번호 찾기
	public String findPw(User user);
	
	// 비밀번호 재설정 -> 비밀번호 변경 -> update 활용
//	public String resetPw(User user);
	
	// 회원 정보 조회 ( 전체 회원 조회 )
	public List<User> selectAll();
	
	// 회원 강제 탈퇴
	public void deleteUser(String userId);
	
	// 아이디를 기반으로 회원 정보 반환 -> 비밀번호 재설정에 활용
	public User findById(String userId);
}
