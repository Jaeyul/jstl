package com.jstl.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jstl.test.common.DBCon;
import com.jstl.test.dao.UserDAO;
import com.jstl.test.vo.UserClass;

public class UserDAOImpl implements UserDAO{
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public List<UserClass> selectUserList(Connection con) {
		ArrayList<UserClass> userList = new ArrayList<UserClass>();
		try {			
			String sql = "select * from user_info";
			ps = con.prepareStatement(sql);			
			rs = ps.executeQuery();			
			while(rs.next()) {
				UserClass uc = new UserClass();
				uc.setAddress(rs.getString("address"));					
				uc.setUiAge(rs.getInt("uiage"));
				uc.setUiId(rs.getString("uiId"));
				uc.setUiName(rs.getString("uiname"));
				uc.setUiNo(rs.getInt("uino"));
				uc.setUiPwd(rs.getString("uipwd"));				
				userList.add(uc);				
			}return userList;
		}catch(Exception e) {
			e.printStackTrace();		
		}
		finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public List<UserClass> searchUser(Connection con, int uiNo) {		
		ArrayList<UserClass> userList = new ArrayList<UserClass>();
		try {			
			String sql = "select * from user_info where uiNo like %?%";
			ps = con.prepareStatement(sql);		
			ps.setInt(1, uiNo);
			rs = ps.executeQuery();			
			while(rs.next()) {
				UserClass uc = new UserClass();
				uc.setAddress(rs.getString("address"));					
				uc.setUiAge(rs.getInt("uiage"));
				uc.setUiId(rs.getString("uiId"));
				uc.setUiName(rs.getString("uiname"));
				uc.setUiNo(rs.getInt("uino"));
				uc.setUiPwd(rs.getString("uipwd"));				
				userList.add(uc);				
			}return userList;
		}catch(Exception e) {
			e.printStackTrace();		
		}
		finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		return null;
	}
	
}
