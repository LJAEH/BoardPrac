package edu.kh.jdbc.member.view;

import java.util.Scanner;

import edu.kh.jdbc.member.service.MemberService;
import edu.kh.jdbc.member.vo.Member;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberService ms = new MemberService();
	
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
			
			switch(memInput) {
			case 1: selectMyInfo(loginMember); break;  
			case 2: selectAll(); break;
			case 3: updateMember(); break;
			case 4: updatePw(); break;
			case 5: secession(); break;
			
			default : System.out.println("번호 재입력");
			}
		
		} while (memInput != 0);
	}
	
	private void selectMyInfo(Member loginMember) {
		System.out.println("내정보조회");
		System.out.println(loginMember);
		
	}
	
	private void selectAll() {
		System.out.println("회원정보조회");
		
		Member All = null;
		
		try {
			All = ms.selectAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	


	private void updateMember() {
		
		
	}
	

	private void updatePw() {
		
		
	}
	


	private void secession() {
		// TODO Auto-generated method stub
		
	}


	
}
