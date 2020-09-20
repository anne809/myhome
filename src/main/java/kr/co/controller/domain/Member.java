package kr.co.controller.domain;

public class Member {
	
	/*
	  DTO(Data Transfer Object)
	  
	  개념 
	  - 데이터 전달 단위, 객체를 표현하는 단위
	  - 보통 테이블의 컬럼들을 멤버 변수로 처리한다
	  - 캡슐화된 객체여야한다. (멤버변슈는 private, public getter/setter 메서드가 필수)
	  
	  
	  * 데이터 베이스, 비지니스 객체, 뷰 객체에서 가져온 값을 저장하거나
	  * 데이터 베이스, 비지니스 객체, 뷰 객체에 보낼 값을 저장하는 객체를
	  * 도메인 객체(Domain Object) 또는 도메인 모델(Domain Model) 이라 한다.
	  
	  
	 작성시 joinForm.jsp에서 name 속성값을 확인 해주세요
	  
	 */
	//멤버 변수는 반드시 private로 지정해야 한다.
	private String id;
	private String password;
	private String name;
	private int age;
	private String gender;
	private String email;
	private int code;
	private String address;
	private String address_datail;
	private String address_datail2;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_datail() {
		return address_datail;
	}
	public void setAddress_datail(String address_datail) {
		this.address_datail = address_datail;
	}
	public String getAddress_datail2() {
		return address_datail2;
	}
	public void setAddress_datail2(String address_datail2) {
		this.address_datail2 = address_datail2;
	}
	
	
	
}
