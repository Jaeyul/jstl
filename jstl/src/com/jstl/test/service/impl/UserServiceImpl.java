package com.jstl.test.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jstl.test.common.DBCon;
import com.jstl.test.dao.UserDAO;
import com.jstl.test.dao.impl.UserDAOImpl;
import com.jstl.test.service.UserService;
import com.jstl.test.vo.UserClass;

public class UserServiceImpl implements UserService {
	Connection con = null;
	UserDAO udao = new UserDAOImpl();
	
	private boolean isName(String numStr) {		
		try {
			int num = Integer.parseInt(numStr);
			return true;
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return false;	
		
	}
	
	
	
	private UserClass getUserClass(int i) {
		UserClass uc = new UserClass();
		uc.setUiNo(i);
		uc.setUiName("�씠由�" + i);
		uc.setUiAge(i);
		uc.setUiId("�븘�씠�뵒" + i);
		uc.setAddress("二쇱냼" + i);
		return uc;		
	}
	
	private List<UserClass> getUserClassList(){
		List<UserClass> userList = new ArrayList<UserClass>();
		for(int i=1; i<10; i++) {
			UserClass uc = getUserClass(i);
			userList.add(uc);
		}
		return userList;
	}
	

	@Override
	public void getUserList(HttpServletRequest req) {
		con = DBCon.getCon();	
		ArrayList<UserClass> userList = (ArrayList<UserClass>)udao.selectUserList(con);		
		req.setAttribute("userList", userList);
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
		
		
	}

	@Override
	public void searchUser(HttpServletRequest req) {
		con = DBCon.getCon();
		String filter = req.getParameter("param");
		if(isName(filter)) {
			int intFilter = Integer.parseInt(filter);
			ArrayList<UserClass> userList = (ArrayList<UserClass>)udao.searchUser(con, intFilter);
			if(userList != null) {
				req.setAttribute("userList", userList);	
			}else {
				userList = (ArrayList<UserClass>)udao.searchUserForUiAge(con, intFilter);
				
			}							
		}
		else {
			ArrayList<UserClass> userList = (ArrayList<UserClass>)udao.searchUser(con, filter);
			req.setAttribute("userList", userList);				
		}
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
