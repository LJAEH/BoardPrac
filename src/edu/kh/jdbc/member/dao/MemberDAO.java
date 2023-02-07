package edu.kh.jdbc.member.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.member.vo.Member;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("member-query.xml"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member selectAll(Connection conn) throws Exception{
		
		Member all = null;
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				all = new Member
						(rs.getString("MEMBER_ID"),
						rs.getString("MEMBER_NM"),
						rs.getString("MEMBER_GENDER"));
				
				System.out.printf("아이디 : %s / 이름 : %s / 성별 : %s\n",
						memberId,memberNm,memberGender);
				
			}
			
			
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return null;
	}
}
