<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

  <div>
    <div>번호 : ${book.bookNo}</div>
    <div>제목 : ${book.title}</div>
    <div>저자 : ${book.author}</div>
    <div>가격 : ${book.price}</div>
  </div>

  <div>
    <a href="${contextPath}/list.do">목록보기</a>
  </div>

</body>
</html>