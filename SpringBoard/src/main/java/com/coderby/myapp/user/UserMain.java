package com.coderby.myapp.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coderby.myapp.user.model.UserVO;
import com.coderby.myapp.user.service.IUserService;

public class UserMain {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("application-config.xml");
		IUserService service = context.getBean("userService", IUserService.class);
		
		try{
		UserVO uservo = new UserVO();
//		uservo.setId("hongkd");
//		uservo.setName("길동쓰");
//		uservo.setPassword("user123");
//		uservo.setRole("User");
//		service.insertUserInfo(uservo); //사용자 추가
//		System.out.println(uservo); // 추가한 정보 나옴
		
		//System.out.println(service.selectUserInfo("test"));
		//System.out.println(service.selectUserInfo("hongkd"));//사용자 정보 출력
		//System.out.println(service.checkPassword("hongkd", "user124")); //false
		//System.out.println(service.checkPassword("hongkd", "user123")); //true
		
		uservo.setPassword("user124");
		uservo.setName("옥주22");
		uservo.setRole("User");
		uservo.setId("okju");
		service.updateUserInfo(uservo);
		System.out.println(uservo);
		
		//service.deleteUserInfo("test");
		
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
