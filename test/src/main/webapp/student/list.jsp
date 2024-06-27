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
<title>학생 관리</title>
</head>
<body>
  <div>
    <h1>학생 관리</h1>
  </div>
  <div>
    <button type="button" onclick="location.href='${contextPath}/write.do'">신규학생등록</button>
  </div>
  <hr>
  <div>
    평균 <input type="text" id="begin"> ~ <input type="text" id="end">
    <button type="button" onclick="search()">조회</button>
    <button type="button" onclick="location.href='${contextPath}/list.do'">전체조회</button>
  </div>
  <hr>
  <div>전체 학생 <c:out value="${students.size()}" />명</div>
  <table border="1">
    <thead>
      <tr>
        <th>학번</th>
        <th>성명</th>
        <th>국어</th>
        <th>영어</th>
        <th>수학</th>
        <th>평균</th>
        <th>학점</th>
        <th>버튼</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="student" items="${students}">
        <tr>
          <td><c:out value="${student.stu_no}" /></td>
          <td><c:out value="${student.name}" /></td>
          <td><c:out value="${student.kor}" /></td>
          <td><c:out value="${student.eng}" /></td>
          <td><c:out value="${student.math}" /></td>
          <td><c:out value="${student.ave}" /></td>
          <td><c:out value="${student.mark}" /></td>
          <td>
            <button type="button" onclick="location.href='${contextPath}/detail.do?stuNo=${student.stu_no}'">상세</button>
            <button type="button" onclick="deleteStudent(${student.stu_no})">삭제</button>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${students.size() == 0}">
        <tr>
          <td colspan="8">등록된 학생이 없습니다.</td>
        </tr>
      </c:if>
    </tbody>
  </table>
  <div>전체평균 <c:out value="${averageScore}" /></div>

  <script>
    // 평균 범위 검색 기능
    function search() {
      var begin = document.getElementById('begin').value;
      var end = document.getElementById('end').value;
      location.href = '${contextPath}/getStudentsByAverageRange.do?begin=' + begin + '&end=' + end;
    }

    // 학생 삭제 기능
    function deleteStudent(stuNo) {
      if (confirm('정말 삭제하시겠습니까?')) {
        location.href = '${contextPath}/delete.do?stuNo=' + stuNo;
      }
    }
  </script>
</body>
</html>
