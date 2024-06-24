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

  <form action="${contextPath}/modify.do" method="post">
    <input type="hidden" id="board_no" name="board_no" value="${board.board_no}">
    <div>
      <label for="title">제목</label>
      <input type="text" name="title" id="title" value="${board.title}">
    </div>
    <div>
      <label for="contents">내용</label><br/>
      <textarea name="contents" id="contents" rows="5" cols="30">${board.contents}</textarea>
    </div>
    <div>
      <button type="submit">수정완료</button>
      <button type="reset">수정초기화</button>
      <button type="button" onclick="fnRemoveBoard()">삭제</button> 
      <button type="button" onclick="location.href='${contextPath}/list.do'">목록보기</button>
    </div>
  </form>
  <script>
    const board_no = document.getElementById('board_no');
    const fnRemoveBoard = ()=>{
      if(confirm('게시글을 삭제할까요?')){
        location.href = '${contextPath}/removeBoard.do?board_no=' + board_no.value;
      } else{
        alert('취소되었습니다.');
      }
    }
  </script>

</body>
</html>