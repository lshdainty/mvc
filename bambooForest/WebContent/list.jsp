<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
</head>
<body>
	<table>
		<thead>
			<tr><th>번호</th><th>제목</th><th>등록일</th></tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${postList }">
			<tr>
				<td>${post.postid }</td>
				<td><a href="/web/view?postid=${post.postid }">${post.title }</a></td>
				<td>${post.created }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<h1>test중입니다.</h1>
</body>
</html>