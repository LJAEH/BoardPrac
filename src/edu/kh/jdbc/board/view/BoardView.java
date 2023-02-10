package edu.kh.jdbc.board.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.service.BoardService;
import static edu.kh.jdbc.main.view.MainView.*;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	
	private BoardService bs = new BoardService();
	
	int input = -1;
	
	public void boardMenu() {

		do {
			try {
				System.out.println("게시판 기능");
				System.out.println("1. 목록 조회" );
				System.out.println("2. 상세조회");
				System.out.println("3. 작성");
				System.out.println("4. 검색");
				System.out.println("0. 로그인메뉴로 이동");
				
				System.out.print("메뉴선택 :");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				
				switch(input) {
				case 1: selectAllBoard(); break;
				case 2: selectBoard(); break;
				case 3: insertBoard(); break;
				case 4: searchBoard(); break;
				
				case 0: System.out.println("로그인 메뉴로"); break;
				default : System.out.println("알맞은 번호 입력");
				}
			} catch(Exception e) {
				e.printStackTrace();
				sc.nextLine();
			}
		} while( input != 0);
	}
	
	
	private void selectAllBoard() {
		// TODO Auto-generated method stub
		
		System.out.println("게시글 목록 조회");
		
		try {
			List<Board> boardList = bs.selectAllBoard();
			
			if(boardList.isEmpty()) {//조회 결과가 없을 경우
				System.out.println("게시글이 존재하지 않음");
				
			} else {
				for(Board b : boardList) {
					System.out.println();
					b.getBoardContent();
					b.getBoardNo();
					b.getBoardTitle();
					b.getCommentCount();
					b.getCreateDate();
					b.getMemberName();
					b.getReadCount();
					b.getMemberNo();
				}
			}
			
			
			
		} catch (Exception e) {
			System.out.println("게시글 목록 조회 중 예외 발생");
			e.printStackTrace();
		}
		
	}
	private void searchBoard() {
		// TODO Auto-generated method stub
		
	}
	private void insertBoard() {
		System.out.println("게시글 등록");
		
		System.out.print("제목 :");
		String boardTitle = sc.nextLine();
		
		System.out.println("내용 :");
		String boardContent = inputContent();
	
		// Board 객체에 제목, 내용, 회원 번호를 담아서 서비스에 전달
		
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setMemberNo(LoginMember.getMemberNo());
		
	}
	
	/** 내용 입력
	 * @return
	 */
	private String inputContent() {
		// TODO Auto-generated method stub
		
		String content = ""; // 빈 문자열
		String input = null; // 참조하는 객체가 없음
		
		System.out.println("입력 종료 시 ($exit) 입력");
		
		while(true) {
			input = sc.nextLine();
			
			if(input.equals("&exit")) {
				break;
			} 
			content += input + "\n";
		}
		return content;
	}


	private void selectBoard() {
		// TODO Auto-generated method stub
		
	}
		

}
