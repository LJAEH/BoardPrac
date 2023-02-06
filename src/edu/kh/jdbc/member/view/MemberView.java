package edu.kh.jdbc.member.view;

import java.util.Scanner;

import edu.kh.jdbc.member.vo.Member;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	private Member loginMember = null;
	
	public void memberMenu(Member loginMember) {
		this.loginMember = loginMember;
		
		int memInput = 0;
		
		do {
		
		
		System.out.println("1.내정보조회");
		System.out.println("2.회원목록조회");
		System.out.println("3.내정보수정");
		System.out.println("4.비밀번호변경");
		System.out.println("5.회원탈퇴");
		
		System.out.print("입력 :");
		memInput = sc.nextInt();
		
		} while (memInput != 0);
	}

}
