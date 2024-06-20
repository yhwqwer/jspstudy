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
<title>책목록</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  div{
    box-sizing: border-box;
  }
  .books{
    overflow: hidden;
    width: 360px;
    height: 360px;
    border: 1px solid gray;
    padding: 10px;
  }
  .book{
    float: left;
    width: 130px;
    height: 130px;
    border: 1px solid orange;
    margin: 10px;
    cursor: pointer;
  }
  .book:hover{
    background-color: beige;
  }
</style>
</head>
<body>

  <div class="books">
    <c:forEach var="book" items="${books}">
      <div class="book" data-book-no="${book.bookNo}">
        <div>${book.bookNo}</div>
        <div>${book.title}</div>
      </div>
    </c:forEach>
  </div>

  <script>
    $('.book').on('click', evt=>{
      location.href = '${contextPath}/detail.do?bookNo=' + evt.currentTarget.dataset.bookNo;
    })
  </script>



</body>
</html>