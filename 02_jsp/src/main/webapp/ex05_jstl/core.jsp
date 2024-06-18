
<%@page import="vo.BookVO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
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

<%--
  <c:set> 태그
  1. JSP Data Binding 영역 4개에 데이터를 저장하는 태그이다.
  2. 형식
    <c:set var="속성명" value="값" scope="영역"></c:set>
    <c:set var="속성명" value="값" scope="영역"/>
  3. scope 속성을 생략하면 page 범위가 사용된다.
--%>

<c:set var="a" value="1" scope="page" />
<c:set var="b" value="2" scope="request" />
<c:set var="c" value="3" scope="session" />
<c:set var="d" value="4" scope="application" />
<div>${a}, ${pageScope.a}</div>
<div>${b}, ${requestScope.b}</div>
<div>${c}, ${sessionScope.c}</div>
<div>${d}, ${applicationScope.d}</div>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div>${contextPath}</div>

<hr/>

<%--
  <c:if> 태그
  1. if 문을 대체하는 태그이다.
  2. else if, else 문이 존재하지 않는 태그이다.
  3. 형식
    <c:if test="${조건식}">
      실행문
    </c:if>
--%>
<c:set var="age" value="<%=(int)(Math.random() * 100 + 1)%>" />
<c:if test="${age lt 20}">
  <div>${age}살은 미성년자입니다.</div>
</c:if>
<c:if test="${age ge 20}">
  <div>${age}살은 성인입니다.</div>
</c:if>

<%-- 0 ~ 100 점수에 따른 A/B/C/D/F 성적 출력하기 --%>
<c:set var="score" value="<%=(int)(Math.random() * 101)%>" />
<c:if test="${score ge 90}">
  <c:set var="mark" value="A" />
</c:if>
<c:if test="${score lt 90 and score ge 80}">
  <c:set var="mark" value="B" />
</c:if>
<c:if test="${score lt 80 and score ge 70}">
  <c:set var="mark" value="C" />
</c:if>
<c:if test="${score lt 70 and score ge 60}">
  <c:set var="mark" value="D" />
</c:if>
<c:if test="${score lt 60}">
  <c:set var="mark" value="F" />
</c:if>
<div>점수는 ${score}점이고, 학점은 ${mark}입니다.</div>

<hr/>
  
<%--
  <c:choose> 태그
  1. else 개념이 존재하는 분기문을 작성하는 태그이다.
  2. 조건식 작성을 위한 <c:when> 하위 태그와 <c:otherwise> 태그가 있다.
  3. 형식
    <c:choose>
      <c:when test="${조건식}">실행문</c:when>  // if()
      <c:when test="${조건식}">실행문</c:when>  // else if()
      <c:when test="${조건식}">실행문</c:when>  // else if()
      <c:otherwise>실행문</c:otherwise>         // else
    </c:choose>
--%>

<%-- 1 ~ 100 나이에 따른 미취학아동/초등학생/중학생/고등학생/성인 성적 출력하기 --%>
<c:set var="age" value="<%=(int)(Math.random() * 100 + 1)%>" />
<c:choose>
  <c:when test="${age >= 20}"><c:set var="result" value="성인" /></c:when>
  <c:when test="${age >= 17}"><c:set var="result" value="고등학생" /></c:when>
  <c:when test="${age >= 14}"><c:set var="result" value="중학생" /></c:when>
  <c:when test="${age >= 8}"><c:set var="result" value="초등학생" /></c:when>
  <c:otherwise><c:set var="result" value="미취학아동" /></c:otherwise>
</c:choose>
<div>${age}살은 ${result}입니다.</div>

<hr/>

<%--
  <c:forEach> 태그
  1. 반복문을 대체하는 태그이다.
  2. 일반 for 문과 향상 for 문을 모두 지원한다.
  3. 일반 for 문 형식
    <c:forEach var="속성" begin="시작값" end="종료값" step="증가값"></c:forEach>
  4. 향상 for 문 형식 (배열, 리스트)
    <c:forEach var="요소" items="${배열} 또는 ${리스트}" varStatus="vs">
      요소   : ${요소}
      인덱스 : ${vs.index}
    </c:forEach>
--%>

<%-- 1 ~ 5 출력하기 --%>
<c:forEach var="a" begin="1" end="5" step="1">
  <div>${a}</div>
</c:forEach>

<%-- 5 ~ 1 출력하기 --%>
<c:forEach var="a" begin="1" end="5" step="1">
  <div>${6 - a}</div>
</c:forEach>

<%-- String[] 배열 --%>
<%
  String[] seasons = {"spring", "summer", "autumn", "winter"};
  pageContext.setAttribute("seasons", seasons);
%>
<c:forEach var="season" items="${seasons}" varStatus="vs">
  <div>${vs.index} : ${season}</div>
</c:forEach>

<%-- List<String> 리스트 --%>
<%
  List<String> hobbies = Arrays.asList("cook", "travel", "swimming");
  pageContext.setAttribute("hobbies", hobbies);
%>
<c:forEach var="hobby" items="${hobbies}" varStatus="k">
  <div>${k.index} : ${hobby}</div>
</c:forEach>

<%-- List<BookVO> 리스트 만들어서 제목/저자/가격 테이블로 출력하기 --%>
<%
  List<BookVO> books = Arrays.asList(
      new BookVO("태백산맥", "조정래", 1000),
      new BookVO("홍길동전", "허균", 2000),
      new BookVO("어린왕자", "생텍쥐베리", 3000),
      new BookVO("소나기", "황순원", 4000)
  );
  pageContext.setAttribute("books", books);
%>
<table border="1">
  <thead>
    <tr>
      <td>인덱스</td>
      <td>제목</td>
      <td>저자</td>
      <td>가격</td>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="book" items="${books}" varStatus="s">
      <tr>
        <td>${s.index}</td>
        <td>${book.title}</td>
        <td>${book.author}</td>
        <td>${book.price}원</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
