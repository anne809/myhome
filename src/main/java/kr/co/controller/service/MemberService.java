package kr.co.controller.service;

import kr.co.controller.domain.Member;

public interface MemberService {
	public int insert(Member m);

	public int isId(String id); //joinForm에서의 ajax로 controller에서 만들어서 이동,db에서 id체크용
	
	public int isId(String id, String pass);

}
