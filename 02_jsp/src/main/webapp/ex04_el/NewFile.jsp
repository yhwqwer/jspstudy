
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="vo.BookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--
  EL
  1. Expression Language (표현 언어)
  2. JSP 스크립트 요소 중에서 표현식(<%=값%>)을 대체할 수 있다.
  3. Data Bind 영역에 저장된 값만 EL 로 나타낼 수 있다.
    1) pageContext : NewFile_jsp (this) : 현재 페이지에서만 접근 가능          : ${값}, ${pageScope.값}
    2) request     : HttpServletRequest : 요청 후 응답 전까지만 접근 가능      : ${값}, ${requestScope.값}
    3) session     : HttpSession        : 브라우저 닫기 전까지만 접근 가능     : ${값}, ${sessionScope.값}
    4) application : ServletContext     : 애플리케이션 종료 전까지만 접근 가능 : ${값}, ${applicationScope.값}
--%>

<%
  pageContext.setAttribute("a", 1);
  request.setAttribute("b", 2);
  session.setAttribute("c", 3);
  application.setAttribute("d", 4);
%>

<h1>pageContext'a : ${a}</h1>
<h1>request'b : ${b}</h1>
<h1>session'c : ${c}</h1>
<h1>application'd : ${d}</h1>

<%
  pageContext.setAttribute("x", 1);
  request.setAttribute("x", 2);
  session.setAttribute("x", 3);
  application.setAttribute("x", 4);
%>
<h1>high priority : shortest life cycle : pageContext : ${x}</h1>
<h1>pageContext'x : ${pageScope.x}</h1>
<h1>request'x : ${requestScope.x}</h1>
<h1>session'x : ${sessionScope.x}</h1>
<h1>application'x : ${applicationScope.x}</h1>

<%
  BookVO book = new BookVO();
  book.setTitle("어린왕자");
  book.setAuthor("생텍쥐베리");
  book.setPrice(10000);
  pageContext.setAttribute("book", book);
%>
<h1>${book.getTitle()}</h1>
<h1>${book.title}</h1>
<h1>${book.getAuthor()}</h1>
<h1>${book.author}</h1>
<h1>${book.getPrice()}</h1>
<h1>${book.price}</h1>

<%
  /*
  BookVO[] books = new BookVO[]{
      new BookVO("책1", "저자1", 1),
      new BookVO("책2", "저자2", 2)
  };
  */
  List<BookVO> books = Arrays.asList(
      new BookVO("책1", "저자1", 1),
      new BookVO("책2", "저자2", 2)
  );
  pageContext.setAttribute("books", books);
%>
<h1>${books.get(0).title}</h1>
<h1>${books.get(0).author}</h1>
<h1>${books.get(0).price}</h1>
<h1>${books[1].title}</h1>
<h1>${books[1].author}</h1>
<h1>${books[1].price}</h1>

<%
  Map<String, Object> map = Map.of("title", "책", "author", "저자", "price", 1);
  pageContext.setAttribute("map", map);
%>
<h1>${map.title}</h1>
<h1>${map.get("title")}</h1>
<h1>${map.author}</h1>
<h1>${map.get("author")}</h1>
<h1>${map.price}</h1>
<h1>${map.get("price")}</h1>

<%
  pageContext.setAttribute("a", 10);
  pageContext.setAttribute("b", 3);
%>
<div>${a + b}</div>
<div>${a - b}</div>
<div>${a * b}</div>
<div>${a / b}, ${a div b}</div>
<div>${a % b}, ${a mod b}</div>

<div>${a > b},  ${a gt b}</div>
<div>${a >= b}, ${a ge b}</div>
<div>${a < b},  ${a lt b}</div>
<div>${a <= b}, ${a le b}</div>
<div>${a == b}, ${a eq b}</div>
<div>${a != b}, ${a ne b}</div>

<div>${a == 10 && b == 3}, ${a eq 10 and b eq 3}</div>
<div>${a == 10 || b == 3}, ${a eq 10 or b eq 3}</div>
<div>${!(a == 10)}, ${not (a eq 10)}</div>

<div>${a > 0 ? "양수" : "음수"}</div>

</body>
</html>
