<!-- HTML 주석 : "페이지 소스 보기"에서 보인다. Java 코드는 주석 처리할 수 없다. -->
<%-- JSP  주석 : "페이지 소스 보기"에서 안 보인다. Java 코드를 주석 처리할 수 있다. --%>

<%-- 
  JSP 스크립트 요소 - 1 : 지시어(directive)
    1. <%@ page %>    : 현재 페이지 설정(언어, 타입, 인코딩 등), import 등
    2. <%@ include %> : 현재 페이지에 다른 페이지를 포함
    3. <%@ taglib %>  : 태그 라이브러리를 사용할 때(JSTL : JSP Standard Tag Library)
 --%>

<%--
  JSP 스크립트 요소 - 2 : 스크립트릿(scriptlet)
   1. 일반 Java 코드를 작성할 때 사용
   2. 형식
      <% Java 코드 %> 
 --%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%
    // 여기는 Java 구역
    LocalDateTime now = LocalDateTime.now();
    String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss.SSS").format(now);
%>

<%--
  JSP 스크립트 요소 - 3 : 표현식 (expression)
  1. Java 변수 값을 나타낼 때 사용한다.
  2. 형식
    <%=값%>
 --%>
<h1><%=strNow%></h1>

<%--
  Java 와 JavaScript
  1. Java 변수를 JavaScript 에서 사용할 수 있다.
  2. 반대는 안 된다.
 --%>
<script>
  alert('<%=strNow%>')
</script>

<!-- 1~10 화면에 출력하기 -->
<% for(int i = 1; i<= 10; i++) { %>
  <div><%=i%></div>
<% } %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>