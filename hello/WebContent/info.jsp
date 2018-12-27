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
	<h1>info페이지</h1>
	<c:forEach var="row" items="${rs}">
		ID:${row.id }<br/>
		JOB:${row.job }<br/>
		DONE:${row.done }<hr/>
	</c:forEach>
</body>
</html>