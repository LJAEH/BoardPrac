package edu.kh.jdbc.member.service;

import edu.kh.jdbc.member.dao.MemberDAO;
import edu.kh.jdbc.member.vo.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	public List<Member> selectAll() throws Exception{
		
		// 커넥션
		Connection conn = getConnection();
		
		
		List<Member> memberList = dao.selectAll(conn);
		// 커넥션 반환
		close(conn);
		
		// 조회 결과 반환
		return null;
	}
	
	public int updateMember(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, member);
		
		if (result >0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	public int updatePw(String currenPw, String newPw1, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result =dao.updatePw(conn,currenPw,newPw1);
		
		
		return result;
		
	}

	public int secession(String memberPw, int memberNo) {
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberPw,memberNo);
		if(result >0 )commit(conn);
		else r
		
		return 0;
	}
	
	

}
