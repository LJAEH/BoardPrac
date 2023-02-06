package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/*
		DB 연결(Connection 생성), 자동커밋 off, 
		트랜잭션 제어, JDBC 객체 지원 반환(close)
		
		이러한 JDBC에서 반복 사용되는 코드를 모아둔 클래스
		
		모든 필드, 메서드가 static
		=> 어디서든지 클래스명.필드명 / 클래스명.메서드명
			호출 가능(별도 객체 생성X)
	 */
	
	private static Connection conn = null;
	
	/** DB 연결 정보를 담고 있는 Connection 객체 생성 및 반환 메서드
	 * @return conn
	 */
	public static Connection getConnection() {
		try {
			//현재 커넥션 객체가 없을경우에만 새 커넥션 객체 생성
			if(conn == null || conn.isClosed()) {
				Properties prop = new Properties();
				// Map<String,String> 형태의 객체, XML 입출력 특화
			
				// driver.xml 파일 읽어오기
				
				/* 재활용 안하고, 코드길이 늘어남
				FileInputStream fos = new FileInputStream();
				prop.loadFromXML(fos);
				와 같다
				*/ 
				
				prop.loadFromXML(new FileInputStream("driver.xml"));
				// => XML 파일에 작성된 내용이 Properties 객체에 모두 저장됨.
				
				// XML에서 읽어온 값을 모두 String 변수에 저장
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String pw = prop.getProperty("pw");
				
				// 커넥션 생성
				
				Class.forName(driver); // Oracle JDBC Driver 객체 메모리에 로드
				// 
				conn = DriverManager.getConnection(url,user,pw);
				
			}
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return conn;
	}
	
	/** Connection 객체 지원 반환 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		// 전달받은 conn이 참조하는 Connection 객체가 있고
		// 그 Connection 객체가 Close상태가 아니라면 ~~~
		try {	
			if(conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** *오버로딩* Statement 반환
	 *  Statement(부모), PropareStatement(자식) 객체 지원 반환 메서드
	 *  (다형성, 동적 바인딩)
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {	
			if(stmt != null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	/** *오버로딩* ResultSet 반환
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {	
			if(rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

	public static void commit(Connection conn) {
		try { 
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
	}

	public static void rollback(Connection conn) {
		try { 
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
	}


}
