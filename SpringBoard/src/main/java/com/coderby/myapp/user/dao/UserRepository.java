package com.coderby.myapp.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.user.model.UserVO;

@Repository //이 클래스가 빈으로 등록되게 해주세요
public class UserRepository implements IUserRepository {

	@Inject
	JdbcTemplate jdbcTemplate;
	
//	private class UserMapper implements RowMapper<UserVO> {
//		@Override
//		public UserVO mapRow(ResultSet rs, int rowCount) throws SQLException {
//			UserVO uservo = new UserVO();
//			uservo.setId(rs.getString("id"));
//			uservo.setPassword(rs.getString("password"));
//			uservo.setName(rs.getString("name"));
//			uservo.setRole(rs.getString("role"));
//			
//			return uservo;
//		}
//	}
//	
	@Override
	public UserVO selectUserInfo(String id) {
		String sql = "SELECT id, password, name, role FROM uservo WHERE id=?"; //쿼리문이 길더라도 열이름은 생략하지 말자
		return jdbcTemplate.queryForObject(sql, new RowMapper<UserVO>(){ //결과가 UserVO로 반환되도록 Mapper클래스 하나 만들기
			@Override
			public UserVO mapRow(ResultSet rs, int count) throws SQLException{
				UserVO uservo = new UserVO();
				uservo.setId(rs.getString("id"));
				uservo.setPassword(rs.getString("password"));
				uservo.setName(rs.getString("name"));
				uservo.setRole(rs.getString("role"));
				return  uservo;
			}
		}, id);
	}

	@Override
	public void insertUserInfo(UserVO user) {
		String sql = "INSERT INTO uservo (id, password, name, role) "
				+"VALUES (?, ?, ?, ?)"; 
			jdbcTemplate.update(sql, // 물음표 순서대로 입력해야 한다
			user.getId(), 
			user.getPassword(), 
			user.getName(), 
			user.getRole()
			);
}

	@Override
	public void updateUserInfo(UserVO user) {
		String sql = "UPDATE uservo SET password=?, name=?, role=? WHERE id=?";
		 jdbcTemplate.update(sql,
				user.getPassword(), 
				user.getName(), 
				user.getRole(), 
				user.getId()
		);
	}

	@Override
	public void deleteUserInfo(String id) {
		String sql = "DELETE uservo WHERE id=?";
		 jdbcTemplate.update(sql, id);
	}

	@Override
	public String getPassword(String id) {
		String sql = "SELECT password FROM uservo where id=?";
		return jdbcTemplate.queryForObject(sql,String.class,id);
	}
}
	
	