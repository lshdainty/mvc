package com.hello.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBBean {
	private static DBBean instance = new DBBean();	
	private DBBean() {};	//생성자 자체가 private이므로 다른 곳에서 DBBean을 new를 할 수가 없다.
	public static DBBean getInstance() { return instance; };
	
	private Connection getConnection(){
		Connection conn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp");
			conn = ds.getConnection();
		}catch(NamingException | SQLException e) {
			e.printStackTrace();
			return null;
		};
		return conn;
	}
	
	public ArrayList<TodoBean> select(){
		ArrayList<TodoBean> list = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(conn!=null) {
			try {
				String sql = "select * from todo";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					TodoBean t = new TodoBean();
					t.setId(rs.getInt("id"));
					t.setJob(rs.getString("job"));
					t.setDone(rs.getBoolean("done"));
					list.add(t);
				}
			}catch(Exception e) {
				System.out.println("select에 문제가 있습니다.");
			}finally {
				if(rs!=null)try {rs.close();}catch(Exception e) {}
				if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
				if(conn!=null)try {conn.close();}catch(Exception e) {}
			}
		}
		return list;
	}	
	
	public int insert(String job) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "insert into todo(job) values(?)";
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, job);
			result = pstmt.executeUpdate();
			if(result == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			}
		}catch(Exception e) {
			System.out.println("insert에 문제가 있습니다.");
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return result;
	}
	
	public int update(int id, boolean done) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "update todo set done=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, done);
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("update에 문제가 있습니다.");
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return result;
	}
}
