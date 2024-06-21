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
    <form id="edit-form" action="${contextPath}/modify.do" method="post">
      <input type="hidden" name="bookNo" value="${book.book_no}">
      <div><input type="text" name="title" id="title" value="${book.title}"></div>
      <div><input type="text" name="author" value="${book.author}"></div>
      <div><input type="text" name="price" id="price" value="${book.price}"></div>
      <div>
        <button type="submit">수정하기</button>
        <button type="button" onclick="history.back()">취소하기</button>
      </div>      
    </form>
  </div>
<script>
  
    const editForm = document.getElementById('register-form');
    const title = document.getElementById('title');
    const price = document.getElementById('price');
    
    const checkSubmit = ()=>{      
      editForm.addEventListener('submit', (evt)=>{
        if(title.value === ''){
          alert('제목은 반드시 입력해야 합니다.');
          title.focus();
          evt.preventDefault();
          return;
        }
        const regPrice = /^$|[^0-9]/;
        if(regPrice.test(price.value)){
          alert('가격은 숫자만 입력할 수 있습니다.');
          price.focus();
          evt.preventDefault();
          return;
        }
      })
    }
    
    checkSubmit();
    
  </script>
</body>
</html>
