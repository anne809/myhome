package kr.co.controller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.controller.domain.Board;
import kr.co.controller.service.BoardService;
import kr.co.controller.service.CommentService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardSerivice;
	
	@Autowired
	private CommentService commentService;
	
	
	//로그인 후 글 목록 리스트 불러오기
	
	@RequestMapping(value="/BoardList.bo")
	public ModelAndView boardlist(
				@RequestParam(value="page",defaultValue="1",required=false)
				int page, ModelAndView mv) {
		
		int limit = 10; //한 화면에 출력할 레코드 개수
		int listcount = boardSerivice.getListCount(); //boardSerivice에서 총 리스트 수를 받아옵니다.

		//총 페이지 수
		int maxpage = (listcount + limit -1 ) / limit;
		
		//현재 페이지에 보여줄 마지막 페이지 수 (1, 11, 21등...)
		int startpage=((page-1)/10)*10+1;
		
		//현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30...)
		int endpage=startpage+10-1;
		
		if(endpage > maxpage)
			endpage = maxpage;
		
		List<Board> boardlist=boardSerivice.getBoardList(page, limit); //리스트를 받아옴
		
		mv.setViewName("board/qna_board_list"); // 뷰의 이름 설정, 뷰 페이지 이름
		mv.addObject("page", page); 			// 데이터를 보내는 작업 (변수이름,데이터값)
		mv.addObject("maxpage",maxpage);		// 이것들을 jsp에서는 ${}으로 값을 가져온다.
		mv.addObject("startpage",startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount",listcount);
		mv.addObject("boardlist",boardlist);
		mv.addObject("limit",limit);
		
		return mv; 								//반환한다.
	
	
	}
	
	

}
