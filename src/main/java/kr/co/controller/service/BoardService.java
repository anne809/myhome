package kr.co.controller.service;

import java.util.List;

import kr.co.controller.domain.Board;

public interface BoardService {
	
	//글의 갯수 구하기
	public int getListCount();
	
	//글 목록 보기
	public List<Board> getBoardList(int page, int limit);
	
	//글 내용 보기
	public Board getDetail(int num);
	
	//글 답변
	public int boardReply(Board board);
	
	//글 수정
	public int boardModify(Board modifyboard);
	
	//글 삭제
	public int boardDelete(int num);
	
	//조회수 업데이트
	public int setReadCountUpdate(int num);
	
	//글쓴이 확인
	public boolean isBoardWriter(int num, String pass);
	
	//글 등록하기
	public void insertBoard(Board board);
	
	//시퀀스 수정
	public int boardReplyUpdate(Board board);
	
	//파일 삭제를 위한 파일명 추가하는 곳
	public int insert_deleteFile(String before_file);
	
	//삭제할 파일 목록
	public List<String> getDeleteFileList();
	
	
	
	

}
