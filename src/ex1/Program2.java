package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		/*
		 * Update
		 * 
		 * */
		
		String title = "TEST3";
		String writerId = "donggi";
		String content = "HELLO DG";
		String files = "";
		int id = 7;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "UPDATE NOTICE SET TITLE =?, CONTENT=?, FILES=? WHERE ID=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"DONGGI","donggi");
//		Statement st = con.createStatement();
//		con.prepareState
		
//		미리 값도 채워넣고 준비한뒤에 실행해라
		PreparedStatement st = con.prepareStatement(sql); // 다음에 excuteUpdate를할때 sql을 다시보내면 에러가 뜬다.
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		System.out.println("result"+result);
		
//		int rs = st.executeUpdate(sql);	// insert는 excuteUpdate 이고 리턴값도 다르다.
		
		
		
		
		
		
	}

}
 