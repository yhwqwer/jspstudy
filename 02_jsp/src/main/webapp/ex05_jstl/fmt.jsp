<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 날짜 형식 지정하기 --%>
<c:set var="now" value="<%=new Date()%>" />

<div>
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd E" />
</div>
<div>
	<fmt:formatDate value="${now}" pattern="a hh:mm:ss.SSS" />
</div>
<div>
	<fmt:formatDate value="${now}" pattern="HH:mm:ss.SSS" />
</div>

<%-- 숫자 형식 지정하기 --%>
<c:set var="number" value="12345.6789"/>
<div>
	<fmt:formatNumber value="${number}" pattern="#,##0" />
</div>
<div>
	<fmt:formatNumber value="${number}" pattern="#,##0.00" />
</div>



</body>
</html>