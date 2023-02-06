package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.board.view.BoardView;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.view.MemberView;
import edu.kh.jdbc.member.vo.Member;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	// 회원기능 메뉴 객체 생성
	private MemberView memberView = new MemberView();
	
	
	// 게시판 기능 메뉴 객체 생성
	private BoardView boardView = new BoardView();
	
	// 로그인된 회원정보를 저장할 객체를 참조하는 참조 변수
	private static Member LoginMember = null;
	// => 로그인 x == null
	// => 로그인 o != null
	
	
	/**
	 * 메인 메뉴 출력 메서드
	 */
	public void mainMenu() {
		/*
		비회원
		1.로그인
		2.회원 가입
		0. 프로그램 종료
		------------------------
		회원
		1.회원기능
		2.게시판기능
		0.로그아웃
		99.프로그램 종료
		*/
		
		int input = -1;
		
		do {
			try {
				if(LoginMember == null) {

					System.out.println("\n ====게시판 프로그램====");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램종료");
					
					System.out.print("메뉴 선택 :");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(input) {
					case 1: login(); break; // 로그인
					case 2: signUp(); break; // 회원가입
					case 3: // 프로그램종료
					default : System.out.println("메뉴에 작성된 번호만 입력해주세요");
					}
					
				} else { // 로그인상태시 
					System.out.println("===회원 메뉴===");
					System.out.println("1.회원 기능");
					System.out.println("2.게시판 기능");
					System.out.println("0.로그아웃");
					System.out.println("99.프로그램종료");
					
					System.out.print("\n 메뉴선택 :");
					input = sc.nextInt();
					
					System.out.println();
					
					switch(input) {
					case 1: memberView.memberMenu(LoginMember); break;// 회원기능 서브 메뉴 출력
					case 2: boardView.boardMenu(LoginMember); break;// 게시판 기능 서브 메뉴 출력	
					case 0: 
						LoginMember = null; // <= 로그아웃
						System.out.println("로그아웃 되었습니다.");
						input = 48; // input이 0일때 do-while문이 종료되므로 
									// 값을 지정하여 다시 반복되게함
						break; // 0 누르면 끝남
						
					case 99: System.out.println("프로그램 종료"); 
					// System.exit(0); // JVM 종료, 매개변수는 0
					System.exit(0);
					break;
					
					default : System.out.println("메뉴에 있는 번호만 입력");
					}
				
				
				}
			} catch(InputMismatchException e) {
				System.out.println("\n 입력이 올바르지않습니다");
				sc.nextLine();
				e.printStackTrace();
			}
			
		} while (input != 0);
		
	}


	/**
	 * 로그인 화면
	 */
	private void login() {
		// TODO Auto-generated method stub
		System.out.println("로그인");
		System.out.print("아이디 :");
		String memberId = sc.next();
		System.out.print("비밀번호 :");
		String memberPw = sc.next();
		
		try {
			// 로그인 서비스 호출 후 조회 결과를 loginMember에 저장
			LoginMember = service.login(memberId,memberPw);
			
			if(LoginMember != null) {
				System.out.println(LoginMember.getMemberName() + "님 환영합니다");
				
			} else {
				System.out.println("아이디 또는 비밀 번호가 일치하지 않습니다");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 회원 가입 화면
	 */
	private void signUp() {
		// TODO Auto-generated method stub
		System.out.println("회원 가입");
		
		String memberId = null;
		String memberPw1 = null;
		String memberPw2 = null;
		
		String memberName = null;
		String memberGender = null;
		
		
		try {
			// 아이디를 입력받아 중복이 아닐 때 까지 반복
			while(true) {
				System.out.print("아이디 입력:");
				memberId = sc.nextLine();
				
				// 입력받은 아이디를 매개변수로 전달하여 
				// 중복 여부를 검사하는 서비스 호출
				// 후 결과 반환 받기
				
				int result = service.idDupCheck(memberId);
				//   1 / 0
				
				System.out.println();
				
				if(result == 0) {
					System.out.println("사용가능한 아이디입니다");
					break;
					
				} else { 
					System.out.println("이미 사용중인 아이디입니다");
				}
			}
			
			// 비밀번호 입력
			// 비밀번호 // 비밀번호 확인이 일치 할 때 까지 무한 반복
			while(true) {
				System.out.print("비밀번호 일력:");
				memberPw1 = sc.next();
				
				System.out.print("비밀번호 확인:");
				memberPw2 = sc.next();
				
				System.out.println();
				
				if(memberPw1.equals(memberPw2)) { // 일치할 경우
					System.out.println("일치합니다");
					break;
				} else { // 일치하지않을 경우
					System.out.println("비밀번호 불일치 재입력");
				}
			}	
			
			// 이름입력
			System.out.print("이름입력 :");
			memberName = sc.nextLine();
			
			// 성별저장
			// m 또는 f가 입력될 때 까지 무한 반복
			while(true) {
				
				System.out.print("성별입력(M/F):");
				memberGender = sc.next().toUpperCase();
				
				System.out.println();
				
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				} else {
					System.out.println("M 또는 F 입력");
				}
				System.out.println();
			}
			
			// 아이디 비밀번호 이름 성별 입력된 상태
			
			// => 하나의 VO에 담아서 서비스 호출 후 결과 반환 받기
			
			Member member = new Member(memberId,memberPw1,memberName,memberGender);
			
			int result = service.signUp(member);
			
			// 서비스 처리 결과에 따른 출력 화면 제어
			System.out.println();
			if(result > 0) {
				System.out.println("회원가입완료");
			} else {
				System.out.println("회원가입실패");
			}
			
			
		} catch(Exception e) {
			System.out.println("\n<<회원가입중예외발생>>\n");
			e.printStackTrace();
		}
	}


}

/* 회원기능 (Member View, Service, DAO, member-query.xml)
 * 
 * 1. 내 정보 조회
 * 2. 회원 목록 조회(아이디, 이름, 성별)
 * 3. 내 정보 수정(이름, 성별)
 * 4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)
 * 5. 회원 탈퇴
 * 
 * ------------------------------------------------------------------
 * 
 * 게시판 기능 (Board View, Service, DAO, board-query.xml)
 * 
 * 1. 게시글 목록 조회(작성일 내림차순)
 * 	  (게시글 번호, 제목[댓글 수], 작성자명, 작성일, 조회수 )
 * 
 * 2. 게시글 상세 조회(게시글 번호 입력 받음)
 *    (게시글 번호, 제목, 내용, 작성자명, 작성일, 조회수, 
 *     댓글 목록(작성일 오름차순 )
 *     
 *     2-1. 댓글 작성
 *     2-2. 댓글 수정 (자신의 댓글만)
 *     2-3. 댓글 삭제 (자신의 댓글만)
 * 
 *     // 자신이 작성한 글 일때만 메뉴 노출
 *     2-4. 게시글 수정
 *     2-5. 게시글 삭제
 *     
 *     
 * 3. 게시글 작성(제목, 내용 INSERT) 
 * 	-> 작성 성공 시 상세 조회 수행
 * 
 * 4. 게시글 검색(제목, 내용, 제목+내용, 작성자)
 * 
 * */

//board-query.xml
//comment-query.xml
//member-query.xml

