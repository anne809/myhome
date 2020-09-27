package kr.co.controller.controller;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.controller.domain.Member;
import kr.co.controller.service.MemberService;

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
   									
   									
   									
   	@Controller 		 해당 클래스가 controller임을 나타내기 위한 어노테이션
   	@RequestMapping		 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지 맵핑하는 어노테이션
   	@RequestParam 		 Controller메소드의 파라미터와 웹요청 파라미터와 맵핑하기 위한 어노테이션
   	@ModelAttribute	 	 Controller 메소드의 파라미터나 리턴값을 Model 객체와 바인딩(속성이 구체적으로 결정되는것)하기위한 어노테이션 								
   	@SessionAttributes   Model객체를 세션에 저장하고 사용하기 위한 어노테이션
   	@RequestPart 		 Multipart요청의 경우, 웹요청 파라미터와 맵핑 가능한 어노테이션
   	@CommandMap 		 Controller메소드의 파라미터를 Map형태로 받을 떄 웹 요청 파라미터와 맵핑하기 위한 어노테이션								
   	@ControllerAdvice    Controller를 보조하는 어노테이션으로 Controller에서 쓰이는 공통기능들을 모듈화하여
   						 전역으로 사용하기 위한 어노테이션
   									
*/


@Controller //stereotype.Controller를 import해주세요.. 
			//해당 클래스가 controller임을 나타내는 어노테이션
public class MemberController {
	
	@Autowired //의존객체대상을 명시하지 않아도 스프링 컨테이너가 자동으로 의존대상객체를 찾아
				//해당 객체에 필요한 의존성을 주입하는것
				//주입하려는 객체의 타입이 일치하는지 찾고 객체를 자동으로 주입한다.
	 			//반드시 <context:annotation-config/>구문을 xml설정파일에 추가필수
				//속성, setter, 생성자 모두에 붙일수 있다.
	
				//@Resource는 이름을 기준으로 객체를 찾는다. bean의 id속성을 기준으로 프로퍼티의 이름을 찾는다.
				//@Inject는 Autowired와 동일하게 작동, 하지만 자동주입할때 순서가 다르다
				
	private MemberService memberservice; //MemberService로 이동해서 주입
	
	//비밀번호 암호화
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
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
	//RequestMapping : URL을 컨트롤러의 메서드와 매핑할 때 사용하는 어노테이션
	//				   viewName을 별도로 설정하지 않으면 path에서 설정한 URL이 그대로 viewName으로 설정				   
	//요약  : 클라이언트는 URL로 요청을 전송 -> 요청 URL은 어떤 메서드가 처리할지 여부를 결정하는 것
	//value: url값으로 매핑 조건을 부여 , method: HTTP Request메소드값을 매핑 조건으로 부여
	//params : HTTP Request 파라미터를 매핑조건으로 부여
	
	public String join() {
		return "member/joinForm";  //WEB-INF/views/member/joinForm.jsp
	}
	
	//회원가입처리
	//
	@RequestMapping(value="/joinProcess.net",method=RequestMethod.POST)
	public void joinProcess(Member member, HttpServletResponse response)throws Exception{
		
		//Member member 정보를 받아옵니다. import com.naver.myhome4.domain.Member;
		//command 객체로 간편하게..
		//우린 더이상 new MemberDAO를 쓰지 않습니다.
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//비밀번호 암호화 추가
		
		String encPassword=passwordEncoder.encode(member.getPassword());
		System.out.println(encPassword);
		member.setPassword(encPassword); //이걸로 변경을 할거에요
		
		int result=memberservice.insert(member); //그리고 여기서 확인 할거에요
		out.println("<script>");
		
		//삽입이 된 경우
		if(result ==1) {
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='login.net';");		
		} else if(result == -1 ) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요.);");
			//out.println("location.href='join.net';");  새로고침되어 이전에 입력한 데이터가 나타나지 않습니다.
			out.println("history.back()"); // 비밀번호를 제외한 다른 데이터는 유지되어 있습니다.
		}
		out.println("</script>");
		out.close();
		
	}
	
	// 회원가입폼에서 아이디 검사 (joinForm.jsp의 상단 제이쿼리 idcheck.net)
	@RequestMapping(value = "/idcheck.net", method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		//	@RequestParam : Controller메소드의 파라미터와 웹요청 파라미터와 맵핑하기 위한 어노테이션
		
		int result = memberservice.isId(id); // -> MemberServiceImpl 로 이동
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);

	}
	
	//로그인 처리
	@RequestMapping(value="/loginProcess.net", method=RequestMethod.POST)
		public String loginProcess(@RequestParam("id")String id, //로그인값 넘어온것
				//	@RequestParam : Controller메소드의 파라미터와 웹요청 파라미터와 맵핑하기 위한 어노테이션
								   @RequestParam("password")String password,
								   @RequestParam(value="remember", defaultValue="")String remember, 
								   //체크하는것, 기본값 string이니까 빈값으로 넣어줍니다. 
								   HttpServletResponse response,HttpSession session) throws Exception {
	
		int result = memberservice.isId(id, password); //두개 값을 result에 저장합니다.(넘겨줍니다.)
		System.out.println("결과는 " + result);
		
		
		
		if(result == 1) { //결과에 따라 나머지 조건 처리, 리멤버를 체크한 경우와 아닌 경우 두가지로 설정
		
			//로그인 성공시- 가입이 되어있는 경우
			session.setAttribute("id", id);
			Cookie savecookie=new Cookie("saveid", id);
			if(!remember.equals("")) {
				savecookie.setMaxAge(60*60); 
				System.out.println("쿠키저장 : 60*60"); // 아이디를 저장한 경우
			}else {
				System.out.println("쿠키저장:0");
				savecookie.setMaxAge(0); // 아이디를 저장히자 않은 경우
			}
			
			response.addCookie(savecookie);//모두 값을 담습니다.
			return "redirect:BoardList.bo"; // 그리고 리턴합니다. ->BoardController로 이동!
	
		} else { //로그인 실패시 - 가입되어있지 않은 경우
			String message="비밀번호가 일치하지 않습니다.";
		
			if(result == -1 )
				message = "아이디가 존재하지 않습니다.";
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("location.href='login.net';");
			out.println("</script>");
			out.close();
			return null;			
		}	

}
}
