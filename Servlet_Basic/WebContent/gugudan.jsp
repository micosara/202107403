<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<%
	
	for(int dan=2;dan<10;dan++){ 				%>
	
	<h3><%=dan%>단</h3>
		
		<%
		for(int gop=1;gop<10;gop++){			
		%>
		
		<span><%=dan%> * <%=gop%>  = <%=(dan*gop)%></span><br/>
		
		<%
		}
		%>
												
		<br/>
<%
		
	}

%>


</body>
</html>