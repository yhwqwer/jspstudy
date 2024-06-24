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

  <form action="${contextPath}/register.do" method="post">
    <div>
      <label for="title">제목</label>
      <input type="text" name="title" id="title">
    </div>
    <div>
      <label for="contents">내용</label><br/>
      <textarea name="contents" id="contents" rows="5" cols="30"></textarea>
    </div>
    <div>
      <button type="submit">작성완료</button>
      <button type="reset">입력취소</button>
      <button type="button" onclick="location.href='${contextPath}/list.do'">목록보기</button>
    </div>
  </form>

</body>
</html>
