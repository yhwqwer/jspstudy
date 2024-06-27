<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>학생 상세 정보</title>
</head>
<body>
  <div>
    <div>학번: <c:out value="${student.stu_no}" /></div>
    <div>이름: <c:out value="${student.name}" /></div>
    <div>국어: <c:out value="${student.kor}" /></div>
    <div>영어: <c:out value="${student.eng}" /></div>
    <div>수학: <c:out value="${student.math}" /></div>
    <div>평균: <c:out value="${student.ave}" /></div>
    <div>학점: <c:out value="${student.mark}" /></div>
  </div>
  <div>
    <a href="${contextPath}/edit.do?stuNo=${student.stu_no}">수정하기</a>
    <a href="${contextPath}/list.do">목록보기</a>
    <a href="javascript:deleteStudent(${student.stu_no})">삭제</a>
  </div>
  <script>
    // 학생 삭제 기능
    function deleteStudent(stuNo) {
      if (confirm('정말 삭제하시겠습니까?')) {
        location.href = '${contextPath}/delete.do?stuNo=' + stuNo;
      }
    }
  </script>
</body>
</html>
