package kr.co.controller.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.controller.domain.Member;

/*
   @Component를 이용해서 스프링 컨테이너가 해당 클래스 객체를 생성하도록 설정할 수 있지만
   	모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기 어렵습니다.
   	스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서 @Component를 상속하여
   	다음과 같은 세 개의 애노테이션을 제공합니다.
   	
   	1. @Controller(제어) - 사용자의 요청을 제어하는 controller 클래스
   	2. @Repository(저장소) - 데이터 베이스 연동을 처리하는 DAO 클래스
   	3. @Service - 비지니스 로직을 처리하는 Service 클래스
   	
   	@흐름
   	
   	MemberController 제어 -> 
   			MemberService 비지니스 로직->
   						MemberServiceImpl  ->
   									MemberDAO 저장소
*/


@Controller //stereotype.Controller를 import해주세요.. 
public class MemberController {
	
	
	/*
	
	
		@CookieValue(value="saveid", required=false) Cookie readCookie 이름이 saveid인
		쿠키를 Cookie 타입으로 전달 받습니다. 지정한 이름의 쿠키가 존재하지 않을 수도있기 때문에
		required=false로 설정해야 합니다.
		required=true상태에서 지정한 이름을 가진 쿠키가 존재하지 않으면 스프링 MVC는 익셉션을 발생시킵니다...
		
	*/
	//login폼으로 이동하는 main페이지
	@RequestMapping(value="/login.net", method=RequestMethod.GET)
	public ModelAndView login(ModelAndView mv,@CookieValue(value="saveid", required=false) Cookie readCookie)
		throws Exception {
		
		if(readCookie != null) {
			mv.addObject("saveid",readCookie.getValue());
			System.out.println("cookie time=" + readCookie.getMaxAge());
		}
		
		mv.setViewName("member/loginForm");
		return mv;
	}
	
	
	//조인 폼으로 이동하는 곳 -> 조인폼을 만들어야겠죠...
	//회원가입!
	@RequestMapping(value="/join.net",method=RequestMethod.GET)
	public String join() {
		return "member/joinForm";  //WEB-INF/views/member/joinForm.jsp
	}
	
	//회원가입처리
	//
	@RequestMapping(value="/joinProcess.net",method=RequestMethod.POST)
	public void joinProcess(Member member, HttpServletResponse response)throws Exception{
		
		
	}

















}
