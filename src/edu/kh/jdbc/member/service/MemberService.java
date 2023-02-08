package edu.kh.jdbc.member.service;

import edu.kh.jdbc.member.dao.MemberDAO;
import edu.kh.jdbc.member.vo.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	public Member selectAll() throws Exception{
		
		// 커넥션
		Connection conn = getConnection();
		
		// dao 호출 결과 반환
		List all = dao.selectAll(conn);
		
		// 커넥션 반환
		close(conn);
		
		// 조회 결과 반환
		return(all);
	}

}
