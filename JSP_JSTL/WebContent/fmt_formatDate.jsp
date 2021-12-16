<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Date today = new Date();
	String todayStr 
			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today);
	out.print(today);
	out.print("<br/>");
	out.print(todayStr);
%>

<hr/>

<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>







