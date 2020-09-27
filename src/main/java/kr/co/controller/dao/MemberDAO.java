package kr.co.controller.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.controller.domain.Member;

@Repository
public class MemberDAO {
	
	/*
	
	DAO(Data Access Object)
	
	개념
	-Data Access Object의 약자로 데이터 베이스의 data에 접근하기 위한 객체 입니다.
	-DAO의 경우는 DB와 연결할 Connection까지 설정되어 있는 경우가 많습니다.
	 그래서 현재 많이 쓰이는 Mybatis 등을 사용할 경우 커넥션풀까지 제공합니다.
	-데이터베이스 관련 작업을 전담하는 클래스
	-데이터베이스에 연결하여 입력, 수정, 삭제, 조회 등의 작업을 하는 클래스
	-데이터베이스에 CRUD 작업이 필요하다.
	
	
	*/
	
	//MemberServiceImpl 따라서 작성하러 이동했습니다~
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insert(Member m) {
		return sqlSession.insert("Members.insert",m);
	}

	public Member isId(String id) { 
		return sqlSession.selectOne("Members.idcheck", id); // 이부분이 member.xml에서 mapper Members
	}
	
	public Member member_info(String id) {
		return sqlSession.selectOne("Members.idcheck",id);
	}

}
