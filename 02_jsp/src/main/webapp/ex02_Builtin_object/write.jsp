<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <% String contextPath = request.getContextPath(); %>
  <h1><%=contextPath%></h1>

  <form action="<%=contextPath%>/ex02_builtin_object/save.jsp" method="post">
    <div>
      <label for="mobile">휴대전화</label>
      <input type="text" id="mobile" name="mobile">
    </div>
    <div>
      <label for="email">이메일</label>
      <input type="text" id="email" name="email">
    </div>    
    <button type="submit">전송</button>
  </form>

</body>
</html>