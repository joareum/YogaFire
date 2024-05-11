package com.yogafire.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yogafire.model.dao.UserDao;
import com.yogafire.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public void signup(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("id", id);
		info.put("password", password);
		return userDao.selectOne(info);
	}

	@Override //??
	public String findId(User user) {
		return userDao.findId(user);
	}

	@Override
	public String findPw(User user) {
		return userDao.findPw(user);
	}

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}

	// 강제 탈퇴
	@Override
	public void outUser(String userId) {
		userDao.deleteUser(userId);
	}

	@Override
	public boolean updatePw(String userId, String newPw) {
		User user = userDao.findById(userId);
		if(user == null) {
			return false;
		}
		
		user.setPassword(newPw);
		userDao.updateUser(user);
		return true;
	}

}
