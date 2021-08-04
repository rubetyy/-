package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.dto.User.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserid(userRegisterInfo.getId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setUserpassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setUsernickname(userRegisterInfo.getNickname());
		user.setUserCreateAt(LocalDateTime.now());
		userRepository.save(user);
		user = userRepositorySupport.findMaxIdx();
		return user;
	}

	@Override
	public User deleteUser(String userId) {
		User user = userRepositorySupport.deleteUser(userId).get();
		return user;
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserId(userId).orElse(null);
		return user;
	}

	@Override
	public long updateUser(User userUpdatePostReq, String userId) {
		return userRepositorySupport.updateUser(userUpdatePostReq, userId);
	}

	@Override
	public boolean check(String userId) {
		Optional<User> u = userRepository.findById(userId);
		if (u.toString().equals("Optional.empty")) return false;
		return true;
	}

}
