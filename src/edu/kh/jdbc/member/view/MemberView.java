package edu.kh.jdbc.member.view;

import static edu.kh.jdbc.main.view.MainView.*;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.member.service.MemberService;
import edu.kh.jdbc.member.vo.Member;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberService ms = new MemberService();
	
	private Member loginMember = null;
	
	int memInput = 0;
	
	public void memberMenu(Member loginMember) {
		this.loginMember = loginMember;
		
		
		
		do {
			try {
				System.out.println("1.내정보조회");
				System.out.println("2.회원목록조회");
				System.out.println("3.내정보수정");
				System.out.println("4.비밀번호변경");
				System.out.println("5.회원탈퇴");
				
				System.out.print("입력 :");
				memInput = sc.nextInt();
				
				switch(memInput) {
				case 1: selectMyInfo(); break;  
				case 2: selectAll(); break;
				case 3: updateMember(); break;
				case 4: updatePw(); break;
				case 5: secession(); break;
				
				default : System.out.println("번호 재입력");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		} while (memInput != 0);
	}
	
	private void selectMyInfo() {
		System.out.println("\n내정보조회\n");
		
		System.out.println("회원번호 : " + loginMember.getMemberNo());
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		System.out.print("성별 : " );
		if(loginMember.getMemberGender().equals("M")){
			System.out.println("남자");
		} else { 
			System.out.println("여자");
		}
		System.out.println("가입일" + loginMember.getEnrollDate());
		
	}
	
	private void selectAll() {
		System.out.println("회원정보조회");
		//DB에서 회원 목록 조회(탈퇴한 회원 미포함)
		try {
		
		List<Member> memberList = ms.selectAll();
		
		// 조회결과가 있으면 모두 출력
		// 없으면 "조회결과 없다" 출력
		
			if(memberList.isEmpty()) {
				System.out.println("\n조회결과가 없습니다.");
			} else {
				System.out.println("아이디          이름     성별");
				System.out.println("=============================");
				
				// 향상된 for문 
				for(Member member : memberList) {
					System.out.printf("%10s %5s %3s\n", member.getMemberId(),
							member.getMemberName(), member.getMemberGender());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void updateMember() {
		
		System.out.println("회원 정보 수정");
		
		try {
			System.out.print("변경할 이름 : ");
			String memberName = sc.next();
			while(true) {	
				System.out.print("변경될 성별(M/F) :");
				String memberGender = sc.next().toUpperCase();
				
				if(memberGender.equals("M")||memberGender.equals("F")) {
					break;
				} else {
					System.out.println("M 또는 F 만 입력");
				}
				
			}
			
			// 서비스로 전달할 Member 객체 생성
			
			Member member = new Member();
			member.setMemberNo(loginMember.getMemberNo());
			member.setMemberName(memberName);
			member.setMemberGender(memberName);
			
			
			//
			int result = ms.updateMember(member);
			
			if (result > 0) {
				loginMember.setMemberName(memberName);
				loginMember.setMemberGender(memberName);
				
				System.out.println("회원정보가 수정되었습니다.");
			} else {
				System.out.println("수정실패");
			}
		} catch(Exception e) {
			System.out.println("정보수정중 예외 발생");
			e.printStackTrace();
		}
	} 
	

	private void updatePw() {
		System.out.println("비밀번호 변경");
		
		try {
			System.out.println("현재 비밀번호 :");
			String currentPw = sc.next();
			
			String newPw1 = null;
			String newPw2 = null;
			
			while(true) {
				System.out.print("새 비밀번호 : ");
				newPw1 = sc.next();
				System.out.println(" 확인 : ");
				newPw2= sc.next();
			if(newPw1.equals(newPw2)) {
				break;
			} else {
				System.out.println("!2123");
			}
				
			}
			int result = ms.updatePw();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	


	private void secession() {
		// TODO Auto-generated method stub
		
		try {
			
			System.out.print("비번 입력 : ");
			String memberPw = sc.next();
			
			while(true) {
				System.out.print("정말?(Y/N)");
				char ch = sc.next().toUpperCase().charAt(0);
				
				if(ch == 'Y') {
					int result = ms.secession(memberPw, loginMember.getMemberNo());
					
					if(result >0) {
						System.out.println("탈퇴됨");
						
						
						memInput = 0;// 메인메뉴로 이동
						loginMember = null;// 로그아웃
						
						
					}
					
				} else if (ch == 'N') {
				 System.out.println("최소되었습니다");	
				 break;
				} else {
					System.out.println("잘못입력되엇습니다");
				}
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	
}
