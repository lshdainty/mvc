<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$("#submit").click(function(){
				var query={
						id:$("#id").val(),
						passwd:$("#passwd").val()
				}
				$.ajax({
					type:'post',
					url:'/api/hello',
					data:query,
					dataType:'text',
					success:function(data){
						var obj = JSON.parse(data);	//객체처럼 만들어준다.
						if(obj.message == "1"){
							$("#message").text($("#id").val()+"님 환영합니다.");
							$("#message").css("color","blue");
						}
						else if(obj.message == "0"){
							$("#message").text("비밀번호가 틀렸습니다.");
							$("#message").css("color","red");
						}
						else{
							$("#message").text("아이디가 틀렸습니다.");
							$("#message").css("color","red");
						}
					}
				});	//ajax
			});	//submit
		});	//document
	</script>
</head>
<body>
	<h1>join</h1>
	<form>
		<label for="id">아이디</label>
		<input type="text" id="id">
		<label for="passwd">비밀번호</label>
		<input type="password" id="passwd">
		<input type="button" id="submit"  value="확인">
	</form>
	<p id="message"></p>
</body>
</html>