package com.coderby.myapp.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coderby.myapp.user.dao.IUserRepository;
import com.coderby.myapp.user.model.UserVO;

@Service
public class UserService implements IUserService {

	@Inject
	IUserRepository userRepository;
	
	@Override
	public UserVO selectUserInfo(String id) {
		return userRepository.selectUserInfo(id);
	}

	@Override
	public void insertUserInfo(UserVO user) {
		// TODO Auto-generated method stub
		userRepository.insertUserInfo(user);
	}

	@Override
	public void updateUserInfo(UserVO user) {
		// TODO Auto-generated method stub
		userRepository.updateUserInfo(user);
	}

	@Override
	public void deleteUserInfo(String id) {
		// TODO Auto-generated method stub
		userRepository.deleteUserInfo(id);
	}

	@Override
	//서비스와 리포지토리에 따로 메서드를 만들어 두는 방법 : 2개 이상의 메서드가 한몸처럼 같이 동작하도록 한다.
	//메서드들이 모두 작동되어야만 실행되도록!
	public boolean checkPassword(String id, String password) {
		if (userRepository.getPassword(id).equals(password)&& userRepository.getPassword(id)!=null){ 
			return true;
		}else{ 
	    	return false;
		}
		
	}

}
