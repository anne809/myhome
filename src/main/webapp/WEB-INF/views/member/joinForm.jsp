<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>반갑습니다! 회원가입을 환영합니다.</title>
<link href="resources/css/join.css" type="text/css" rel="stylesheet">
<script src="resources/js/jquery-3.5.0.js"></script>
<script src="resources/js/join.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	var checkid=false;
	var checkemail=false;
	$('form').submit(function(){
	
		if(!$.isNumeric($("input[name='age']").val())){
			alert("나이는 숫자로 입력하세요");
			$("input[name='age']").val('');
			$("input[name='age']").focus('');
			return false;			
		}
		
		if(!checkid){
			alert("사용 가능한 id로 입력하세요.");
			$("input:eq(0)").val('').focus();
			return false;			
		}
		
		if(!checkemail){
			alert("E-mail형식을 확인하세요.");
			$("input:eq(6)").focus();
			
			return false;
			
		}		
		
	});//submit
	
	$("input:eq(6)").on('keyup',function(){
		$("#email_message").empty(); //처음에 pattern에 적합하지 않은 경우 메지시 출력후 적합한
		
		//[A-Za-z0-9_]와 동일한 것이 \w
		//+는 1회 이상 반복을 의미합니다. {1,}와 동일
		//\w+는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다.
		
		var pattern=/\w+@\w+[.]\w{3}/;
		var email=$("input:eq(6)").val();
		
		if(!pattern.test(email)){
			$("#email_message").css('color','red').html
			("이메일 형식에 맞지 않습니다.");
			checkemail=false;			
		}else{
			$("#email_message").css('color','green').html()
			("이메일 형식에 맞습니다.");
			checkemail=true;
		}
		
	}); //email keyup 이벤트 처리 끝
	
	$("input:eq(0)").on('keyup',
	
	function(){
		$("#message").empty();
		
		var pattern=/^\w{5,12}$/;
		var id=$("input:eq(0)").val();
		if(!pattern.test(id)){
			$("#message").css('color','red').html
				("영문자 숫자_로 5~12자 가능합니다.");
			
			checkid=false;
			return;			
		}
		
		$.ajax({
			url:"idcheck.net",
			data: {"id" : id},
			success:function(resp){
				if(rest==-1){ //db에 해당하는 id가 없는 경우
					$("#message").css('color','green').html(
						"사용 가능한 아이디입니다.");
				checkid=true;
				}else{ //db에 해당 id가 있는 경우 (0) 
					$("#message").css('color','blue').html(
							"사용 중인 아이디입니다.");
							checkid=false;		
					}
			}
			
		}); //ajax End
	}) //id keyup end
});


	
	
</script>

</head>


<body>
	<form name="joinform" action="joinProcess.net" method="post">
		<h1>회원가입</h1>
		<hr>
		<b>아이디</b> 
		<input type="text" name="id" placeholder="아이디를 입력해주세요"
			required maxLenth="12"> <span id="message"></span> 
		<b>비밀번호</b>
		<input type="password" name="password" placeholder="비밀번호를 입력해주세요"
			required> 
		<b>이름</b> 
		<input type="text" name="name"
			placeholder="이름을 입력해주세요" maxlength="15" required> 
		<b>나이</b> 
		<input type="text" name="age" maxlength="2" placeholder="나이를 입력해주세요"
			required> 
			
		<b>성별</b>

		<div>
			<input type="radio" name="gender" value="남" checked><span>남자</span>
			<input type="radio" name="gender" value="여"><span>여자</span>
		</div>
		<b>이메일</b> 
		<input type="text" name="email" placeholder="Enter email"
			required><span id="email_message"></span>
			
		<b>우편번호</b> 
		<input type="text" name="code" id="code" onclick="sample6_execDaumPostcode()" placeholder="이 곳을 클릭하세요" required> 

		<b>주소</b> 
		<input type="text" name="address" id="address" placeholder="주소" onclick="sample6_execDaumPostcode()" required> 
		<input type="text" name="address_datail" id="address_datail" placeholder="상세주소 1" onclick="sample6_execDaumPostcode()" required readonly> 
		<input type="text" name="address_datail2" id="address_datail2" placeholder="상세주소 2" onclick="sample6_execDaumPostcode()" > 
		

		<div class="clearfix">
			<button type="submit" class="submitbtn">회원가입</button>
			<button type="reset" class="cancelbtn">다시 작성</button>

		</div>
	</form>

</body>
</html>