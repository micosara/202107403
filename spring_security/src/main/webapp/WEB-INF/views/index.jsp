<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp</h1>
	<h1>${loginUser.name }님 환영합니다.</h1>
	
	<ul>
		<li><a href="<c:url value='/home/main' />" >/home/main</a></li>	
		
		<sec:authorize access="isAuthenticated()">	
		<li><a href="<c:url value='/member/main' />">/member/main</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAuthority('ROLE_MANAGER')">			
		<li><a href="<c:url value='/manager/main' />">/manager/main</a></li>
		</sec:authorize>
		
		<sec:authorize access="hasAuthority('ROLE_ADMIN')">		
		<li><a href="<c:url value='/admin/main' />">/admin/main</a></li>
		</sec:authorize>
	</ul>	
	
	<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='commons/login'/>">로그인</a><br/>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">로그아웃</a>
	</sec:authorize>
</body>
</html>



