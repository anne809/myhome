package kr.co.controller.domain;

public class Board {
	
	private int 		BOARD_NUM; 		//글번호
	private String	    BOARD_NAME;		//글 작성자
	private String 		BOARD_PASS;		//글 비밀번호
	private String 		BOARD_SUBJECT;	//글 제목
	private String 		BOARD_CONTENT;	//글 내용
	private String 		BOARD_FILE;		//실제 저장된 파일의 이름
	private String 		BOARD_ORIGINAL;	// 첨부될 파일의 이름
	private int 		BOARD_RE_REF;	//답변 글 작성시 참조되는 글의 번호
	private int 		BOARD_RE_LEV; 	//답변 글의 깊이
	private int 		BOARD_RE_SEQ;	//답변 글의 순서
	private int 		READCOUNT;		//글의 조회수
	
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}
	public String getBOARD_PASS() {
		return BOARD_PASS;
	}
	public void setBOARD_PASS(String bOARD_PASS) {
		BOARD_PASS = bOARD_PASS;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	public String getBOARD_FILE() {
		return BOARD_FILE;
	}
	public void setBOARD_FILE(String bOARD_FILE) {
		BOARD_FILE = bOARD_FILE;
	}
	public String getBOARD_ORIGINAL() {
		return BOARD_ORIGINAL;
	}
	public void setBOARD_ORIGINAL(String bOARD_ORIGINAL) {
		BOARD_ORIGINAL = bOARD_ORIGINAL;
	}
	public int getBOARD_RE_REF() {
		return BOARD_RE_REF;
	}
	public void setBOARD_RE_REF(int bOARD_RE_REF) {
		BOARD_RE_REF = bOARD_RE_REF;
	}
	public int getBOARD_RE_LEV() {
		return BOARD_RE_LEV;
	}
	public void setBOARD_RE_LEV(int bOARD_RE_LEV) {
		BOARD_RE_LEV = bOARD_RE_LEV;
	}
	public int getBOARD_RE_SEQ() {
		return BOARD_RE_SEQ;
	}
	public void setBOARD_RE_SEQ(int bOARD_RE_SEQ) {
		BOARD_RE_SEQ = bOARD_RE_SEQ;
	}
	public int getREADCOUNT() {
		return READCOUNT;
	}
	public void setREADCOUNT(int rEADCOUNT) {
		READCOUNT = rEADCOUNT;
	}



}
