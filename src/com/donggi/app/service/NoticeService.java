package com.donggi.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.donggi.app.entity.Notice;

public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String uid = "DONGGI";
	private String pwd = "donggi";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList() throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM NOTICE";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Notice> list = new ArrayList<Notice>();
		// 전부 가져올때
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			String files = rs.getString("Files");
			int hit = rs.getInt("HIT");
			
			Notice notice = new Notice(
					id,
					title,
					writerId,
					regDate,
					content,
					files,
					hit
					);
			list.add(notice);
			
		}
		
		rs.close();
		st.close();
		con.close();
		return list;
	}
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		Date regDate = notice.getRegDate();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String sql = "INSERT INTO NOTICE ("
				+ "title,"
				+ "writer_id,"
				+ "content,"
				+ "files"
				+ ") VALUES(?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		PreparedStatement st = con.prepareStatement(sql); // 다음에 excuteUpdate를할때 sql을 다시보내면 에러가 뜬다.
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();
		
		return result;
	}
	
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String sql = "UPDATE NOTICE SET TITLE =?, CONTENT=?, FILES=? WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		PreparedStatement st = con.prepareStatement(sql); // 다음에 excuteUpdate를할때 sql을 다시보내면 에러가 뜬다.
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE NOTICE WHERE ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		PreparedStatement st = con.prepareStatement(sql); // 다음에 excuteUpdate를할때 sql을 다시보내면 에러가 뜬다.
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}
}
