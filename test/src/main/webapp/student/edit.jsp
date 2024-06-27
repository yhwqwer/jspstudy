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
<title>학생 정보 수정</title>
</head>
<body>
  <div>
    <form id="edit-form" action="${contextPath}/modify.do" method="post">
      <input type="hidden" name="stuNo" value="${student.stu_no}" readonly="readonly">
      <div><input type="text" name="name" id="name" value="${student.name}"></div>
      <div><input type="text" name="kor" id="kor" value="${student.kor}"></div>
      <div><input type="text" name="eng" id="eng" value="${student.eng}"></div>
      <div><input type="text" name="math" id="math" value="${student.math}"></div>
      <div><button type="submit">수정하기</button></div>
      <div><button type="button" onclick="history.back()">취소하기</button></div>
    </form>
  </div>
  <script>
    const editForm = document.getElementById('edit-form');
    editForm.addEventListener('submit', (evt) => {
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
