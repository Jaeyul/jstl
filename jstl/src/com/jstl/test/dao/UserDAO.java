package com.jstl.test.dao;

import java.sql.Connection;
import java.util.List;

import com.jstl.test.vo.UserClass;

public interface UserDAO {
	
	public List<UserClass> selectUserList(Connection con);
	public List<UserClass> searchUser(Connection con, int uiNo);

}
