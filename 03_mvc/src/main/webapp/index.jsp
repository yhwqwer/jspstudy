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
  <select id="type">
    <option value="date">현재날짜</option>
    <option value="time">현재시간</option>
  </select>
  <a href="javascript:getDateTime()">요청</a>
  <button type="button" onclick="getDateTime()">요청</button>
</div>

<script>
  const getDateTime = ()=>{
    const type = document.getElementById('type');
    location.href = '${contextPath}/datetime?type=' + type.value;
  }
</script>

</body>
</html>