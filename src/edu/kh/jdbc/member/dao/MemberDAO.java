package edu.kh.jdbc.member.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.member.vo.Member;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop;
	
	public MemberDAO() {
		
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream ("member-query.xml"));
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Member> selectAll(Connection conn) throws Exception{
		
		List<Member> memberList = new ArrayList<>();
		String sql = prop.getProperty("selectAll");
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			// 컬럼값을 얻어와서 member객체에 저장후 리스트에 추가
			
			String memberId = rs.getString("MEMBER_ID");
			String memberNm = rs.getString("MEMBER_NM");
			String memberGender = rs.getString("MEMBER_GENDER");
			
			Member member = new Member();
			
			member.setMemberId(memberId);
			member.setMemberName(memberNm);
			member.setMemberGender(memberGender);
			
			memberList.add(member);
		} 
			close(rs);
			close(stmt);
		
		
		return memberList;
	}

	public int updateMember(Connection conn, Member member) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberGender());
			pstmt.setInt(3, member.getMemberNo());
			
			result = pstmt.executeUpdate();
			
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updatePw(Connection conn ,String currentPw, String newPw1, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw1);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);
			
			result=pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int secession(Connection conn, String memberPw, int memberNo) throws Exception {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return 0;
	}
}
