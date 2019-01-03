package bambooforest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PostDBBean {
	private static PostDBBean instance = new PostDBBean();	
	private PostDBBean() {};	//생성자 자체가 private이므로 다른 곳에서 DBBean을 new를 할 수가 없다.
	public static PostDBBean getInstance() { return instance; };
	
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
	
	public ArrayList<PostBean> getPostList(){
		ArrayList<PostBean> list = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn!=null) {
			try {
				String sql = "select * from post";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					PostBean p = new PostBean();
					p.setPostid(rs.getInt("postid"));
					p.setTitle(rs.getString("title"));
					p.setContent(rs.getString("content"));
					p.setCreated(rs.getTimestamp("created"));
					p.setMemberid(rs.getString("memberid"));
					list.add(p);
				}
			}catch(SQLException e) {
				list = null;
				e.printStackTrace();
				System.out.println("PostList를 불러오는데 문제가 있습니다.");
			}finally {
				if(rs!=null)try {rs.close();}catch(Exception e) {}
				if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
				if(conn!=null)try {conn.close();}catch(Exception e) {}
			}
		}
		return list;
	}
	
	public PostBean getPost(int postid) {
		PostBean p = new PostBean();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(conn!=null) {
			try {
				String sql = "select * from post where postid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,postid);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					p.setPostid(rs.getInt("postid"));
					p.setTitle(rs.getString("title"));
					p.setContent(rs.getString("content"));
					p.setCreated(rs.getTimestamp("created"));
					p.setMemberid(rs.getString("memberid"));
				}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("PostList를 불러오는데 문제가 있습니다.");
			}finally {
				if(rs!=null)try {rs.close();}catch(Exception e) {}
				if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
				if(conn!=null)try {conn.close();}catch(Exception e) {}
			}
		}
		return p;
	}
	
	public ArrayList<ReplyBean> getReplyList(int postid){
		ArrayList<ReplyBean> list = new ArrayList<>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(conn!=null) {
			try {
				String sql = "select * from reply where postid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, postid);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ReplyBean r = new ReplyBean();
					r.setReplyid(rs.getInt("replyid"));
					r.setComment(rs.getString("comment"));
					r.setCreated(rs.getTimestamp("created"));
					r.setPostid(rs.getInt("postid"));
					r.setMemberid(rs.getString("memberid"));
					list.add(r);
				}
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("ReplyList를 불러오는데 문제가 있습니다.");
			}finally {
				if(rs!=null)try {rs.close();}catch(Exception e) {}
				if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
				if(conn!=null)try {conn.close();}catch(Exception e) {}
			}
		}
		return list;
	}
	
	public int addPost(PostBean post) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		if(conn!=null) {
			try {
				String sql = "insert into post(title,content,memberid) values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, post.getTitle());
				pstmt.setString(2, post.getContent());
				pstmt.setString(3, post.getMemberid());
				result = pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("post를 insert하는데 문제가 있습니다.");
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
				if(conn!=null)try {conn.close();}catch(Exception e) {}
			}
		}
		return result;
	}
	
	public int addReply(ReplyBean reply) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		if(conn!=null) {
			try {
				String sql = "insert into post(comment,postid,memberid) values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, reply.getComment());
				pstmt.setInt(2, reply.getPostid());
				pstmt.setString(3, reply.getMemberid());
				result = pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("reply를 insert하는데 문제가 있습니다.");
			}finally {
				if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
				if(conn!=null)try {conn.close();}catch(Exception e) {}
			}
		}
		return result;
	}
}
