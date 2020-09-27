package kr.co.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.controller.dao.MemberDAO;
import kr.co.controller.domain.Member;

//사용하려면 애노테이션 꼭 빼놓지마세요! 그리고 꼭 인터페이스 상속 받아주세요~
//MemberController -> Member생성 -> Memberservice-> MemberDAO -> MemberSeriveImpl
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO dao;

	
	//MemberController에서 넘어옴 -> MemberDAO에서 insert 만들고...
	@Override
	public int insert(Member m) {
		return dao.insert(m);
	}


	@Override
	public int isId(String id) {   	   // 첫번째 isId
		Member rmember = dao.isId(id); //  만들고 다오에서 isid만들러..
		return (rmember==null)? -1 :1; //  -1은 아이디가 존재하지 않는경우
									   //  1은 아이디가 존재하는 경우
	}

	//비밀번호 암호화
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public int isId(String id, String password) {
		Member rmember = dao.isId(id);
		int result = -1; 	  //아이디가 존재하지 않는 경우 - rmember가 null인 ㅣ경우
		if(rmember != null) { //아이디가 존재하는 경우
			
			//passwordEncoder.matches(rawPassword,encodePassword)
			//사용자에게 입력받은 패스워드를 비교하고자 할 때 사용하는 메서드 입니다.
			//rawPassword : 사용자가 입력한 패스워드
			//encodedPassword : DB에 저장된 패스워드
			
			if(passwordEncoder.matches(password,rmember.getPassword())) {
				result=1; //아이디와 비밀번호가 일치하는 경우
			} else 
				result=0;// 아이디는 존재하지만 비밀번호가 일치하지 않는 경우			
		}
		return result;
	}
	
	
	

}
