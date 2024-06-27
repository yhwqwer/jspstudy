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
<title>신규 학생 등록</title>
</head>
<body>
  <div>
    <form id="register-form" action="${contextPath}/register.do" method="post">
      <div><input type="text" name="name" id="name" placeholder="이름"></div>
      <div><input type="text" name="kor" id="kor" placeholder="국어"></div>
      <div><input type="text" name="eng" id="eng" placeholder="영어"></div>
      <div><input type="text" name="math" id="math" placeholder="수학"></div>
      <div><button type="submit">등록하기</button></div>
    </form>
  </div>
  <script>
    const registerForm = document.getElementById('register-form');
    registerForm.addEventListener('submit', (evt) => {
      const name = document.getElementById('name').value;
      const kor = document.getElementById('kor').value;
      const eng = document.getElementById('eng').value;
      const math = document.getElementById('math').value;
      if (!name || !kor || !eng || !math) {
        alert('모든 필드를 채워주세요.');
        evt.preventDefault();
      }
    });
  </script>
</body>
</html>
