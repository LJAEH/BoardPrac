package edu.kh.jdbc.board.view;

import java.util.Scanner;

import edu.kh.jdbc.board.service.BoardService;
import edu.kh.jdbc.member.vo.Member;

public class BoardView {
	
	private Member loginMember = null;
	
	private Scanner sc = new Scanner(System.in);
	
	private BoardService bs = new BoardService();
	
	public void boardMenu(Member LoginMember) {
		
		this.loginMember = LoginMember;
		
		
	}

}
