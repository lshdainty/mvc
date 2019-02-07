<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h3>${post.title }</h3>
	</div>
	<div style="height:100px; border-style:solid">
		${post.content }
	</div>
	<div style="float:right;">
		글번호 : ${post.postid }, 작성일자 : ${post.created }
	</div>
	<ul>
		<c:forEach var="re" items="${ replyList}">
		<li><p>
			<c:if test="${post.memberid == re.memberid}">(글쓴이)</c:if>
			${re.comment } - ${re.created }</p>
		</li>
		</c:forEach>
	</ul>
	<h1>view테스트중입니다.</h1>
</body>
</html>