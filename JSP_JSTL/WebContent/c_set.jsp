<%@page import="sun.util.locale.StringTokenIterator"%>
<%@page import="com.java.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<% pageContext.setAttribute("msg","hello!"); %>

${msg}

<hr/>

<c:set var="msg" value="hello!!!!!!!!!!!!!!!!!!!" scope="request"/>
${requestScope.msg}

<hr/>
<c:remove var="msg" scope="page"/>
${msg}<br/>
${requestScope.msg}

<hr/>
<c:set var = "member" value="<%=new MemberVO() %>" />
<% ((MemberVO)pageContext.getAttribute("member")).setId("mimi"); %>
<c:set target="${member }" property="id" value="mimi" />

${member.id }















