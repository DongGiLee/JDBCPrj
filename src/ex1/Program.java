package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*
		 * JDBC process 어디서든 거의 동일하다 
		 * 
		 * 1~4 순차적으로 실행되도록 new 연산자없이 
		 * 하단으로 쭉이어지게되어있다.
		 * 
		 * 메모리상에 드라이버가 올라간다.
		 * 1. Class.forName("oracle.jdbc.driver.OracleDriver");
		 * 
		 * 연결이 이뤄진다.
		 * 2. Connection con = DriverManager.getConnecttion(...);
		 * 
		 * 사용자로부터 받은 쿼리를 실행하게된다.
		 * 3. Statement st = con.createStatement();
		 * 
		 * 결과집합은 아직 서버에 있는상태이다. 
		 * ResultSet을 받았다라는것은 결과집합을 받은것이아닌 결과집합을 이용할수있는 상태이다.
		 * 하나씩 받아올수있는 ResultSet 비어있는객체 이라고 보면된다. (처음은 BoF; Before of File; 아직 Cursor가 파일앞에 있는상태)
		 * (EoF; End of File; 파일의 끝)
		 * 그래서 rs.next(); -- 결과집합에 next()함수로 다음 커서로 옮겨달라고 요청을한다.
		 * String title = rs.getString("title"); 로 현재커서의 title을 가져온다.
		 * 4. ResultSet rs = st.executeQuery(sql);
		 * 
		 * 
		 * */ 
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE HIT > 10";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"DONGGI","donggi");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {			
			String name = rs.getString("TITLE"); // 대소문자를 구분하지않지만 맞게 가져오는게 좋다.
			System.out.printf("NAME: %s\n",name);
		} else {
			System.out.println("NO DATA");
		}
		// 전부 가져올때
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			
			System.out.printf("id: %d, title: %s, writerId: %s, register Date: %s, content: %s, hit: %d \n",
					id, title, writerId, regDate, content,hit
					);
			
		}
		
		rs.close();
		st.close();
		con.close();
		
		
		
		
	}

}
 