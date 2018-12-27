<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		$("#insert").click(function(){
			var job = $("#job").val();
			var query = {
					job:job
			};
			$.ajax({
				type:"post",
				url:"/api/insert",
				data:query,
				success:function(data){
					var obj = JSON.parse(data);
					console.log(obj.result)
					if(obj.result == "OK"){
						var li = "<li><input type='radio' name='job' value='" + obj.id + "'/>JOB:" + job +" , <div id='" + obj.id + "'>Done:false</li>";
						$("#list").append(li);
						$("#job").val("");
					}else{
						
					}
				}	//success
			});	//ajax
		});	//insert
		
		$("#update").click(function(){
			var setValue = $("input[name=job]:checked").val();
			var query = {
					id:setValue
			};
			$.ajax({
				type:"post",
				url:"/api/update",
				data:query,
				success:function(data){
					var obj = JSON.parse(data);
					if(obj.result == "OK"){
						alert("update");
						var divid = "#"+setValue;
						$(divid).text("Done:true");
					}else{
						
					}
				}	//success
			});	//ajax
		});	//update
	});	//document
</script>
</head>
<body>
	<h1>info페이지</h1>
	<h3>추가</h3>
	<label for="job">할일</label>
	<input type="text" id="job"/>
	<button id="insert">add</button>
	<h3>수정</h3>
	<button id="update">update</button>
	
	<ul id="list">
		<c:forEach var="row" items="${rs}">
			<li><input type="radio" name="job" value="${row.id}"/>JOB:${row.job } , <span id="${row.id}">Done:${row.done}</span></li>
		</c:forEach>
	</ul>
</body>
</html>