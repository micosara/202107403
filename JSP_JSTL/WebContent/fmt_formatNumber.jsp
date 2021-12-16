<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	int price = 10000;
	String priceStr = new DecimalFormat("#,###").format(price);
	out.println("￦"+priceStr);
%>

<fmt:formatNumber var="price" value="10000" pattern="#,###" />
<br/>
￦${price }

</body>
</html>










