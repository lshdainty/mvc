package bambooforest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDBBean {
	private static MemberDBBean instance = new MemberDBBean();	
	private MemberDBBean() {};	//생성자 자체가 private이므로 다른 곳에서 DBBean을 new를 할 수가 없다.
	public static MemberDBBean getInstance() { return instance; };
	
	public static final int VALID = 0;	//유지보수가 편하도록 상수로 만들어서 관리한다.
	public static final int INVALID_PASSWORD = 1;
	public static final int INVALID_USER = 2;
	public static final int ERROR = 100;
	
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
	
	public int memberCheck(String memberid, String password) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from member where memberid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();	//아이디가 없거나 하나 있거나이므로
			
			if(rs.next()) {
				String dbPassword = rs.getString("password");
				if(dbPassword.equals(password)) {
					return VALID;	//로그인 성공
				}else {
					return INVALID_PASSWORD;	//비번이 다름
				}
			}else {
				return INVALID_USER;	//아이디가 없는 경우이다.
			}
		}catch(SQLException e) {
			e.printStackTrace();//개발중에는 지우지 말것
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return ERROR;
	}
}
