<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%
   request.setCharacterEncoding("UTF-8");
   out.println("<h1>" + request.getParameter("mobile") + "</h1>");
   out.println("<h1>" + request.getParameter("email") + "</h1>");
   out.println("<h1>" + request.getRemoteAddr() + "</h1>");
 %>

</body>
</html>