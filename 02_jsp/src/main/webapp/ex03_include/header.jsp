<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
  String contextPath = request.getContextPath();
  long timestamp = System.currentTimeMillis();
%>


<%-- lib --%>
<script src="<%=contextPath%>/assets/lib/jquery-3.7.1.min.js"></script>

<%-- js --%>
<script src="<%=contextPath%>/assets/js/today.js?dt=<%=timestamp%>"></script>

<%-- css --%>
<link href= "<%=contextPath%>/assets/css/header.css?dt=<%=timestamp%>" rel="stylesheet">

<%
  request.setCharacterEncoding("UTF-8");
  String title = request.getParameter("title");
%>
<title><%=title%></title>

</head>
<body>

<div class="header-wrap">
  <div>
    <img src="<%=contextPath%>/assets/images/logo_google.png" width="100px">
  </div>
  <nav class="nav">
    <a href="<%=contextPath%>/ex03_include/body1.jsp">body1</a>
    <a href="<%=contextPath%>/ex03_include/body2.jsp">body2</a>
    <a href="<%=contextPath%>/ex03_include/body3.jsp">body3</a>
  </nav>
</div>

<div class="body-wrap">
