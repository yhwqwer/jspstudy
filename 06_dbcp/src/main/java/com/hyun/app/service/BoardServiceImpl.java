package com.hyun.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hyun.app.common.ActionForward;
import com.hyun.app.dao.BoardDAO;
import com.hyun.app.dao.BoardDAOImpl;
import com.hyun.app.dto.BoardDTO;
import com.hyun.app.utils.PageUtils;

import jakarta.servlet.http.HttpServletRequest;

public class BoardServiceImpl implements BoardService {

  // Field
  private BoardDAO boardDAO = BoardDAOImpl.getInstance();
  private PageUtils pageUtils = new PageUtils();
  
  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
    
    // PageUtils's total
    int total = boardDAO.getBoardCount();
    
    // PageUtils's display
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));
    
    // PageUtils's page
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    
    // PageUtils' begin / end 계산
    pageUtils.setPaging(total, display, page);
    
    // sort
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    // 데이터베이스로 보낼 Map<String, Object> params 생성
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("begin", pageUtils.getBegin());
    params.put("end", pageUtils.getEnd());
    params.put("sort", sort);
    
    // 데이터베이스에서 목록 가져오기
    List<BoardDTO> boardList = boardDAO.getBoardList(params);
    
    // registerBoard() 실행 이후인 경우, /board/list.jsp 로 성공/실패 메시지를 전달함
    if(request.getParameter("register") != null) {
      request.setAttribute("registerMessage", request.getParameter("register").equals("1") ? "게시글 추가 성공" : "게시글 추가 실패");
    }
    
    // removeBoardList() 실행 이후인 경우, /board/list.jsp 로 성공 / 실패 메시지를 전달함
    if(request.getParameter("remove") != null) {
      request.setAttribute("removeMessage", request.getParameter("remove").equals("0") ? "게시글 삭제 실패" : "게시글 삭제 성공");
    }
    
    // modifyBoard() 실행 이후인 경우, /board/list.jsp 로 성공 / 실패 메시지를 전달함
    if(request.getParameter("modify") != null) {
      request.setAttribute("modifyMessage", request.getParameter("modify").equals("1") ? "게시글 수정 성공" : "게시글 수정 실패");
    }
    
    
    
    
    // /board/list.jsp 로 보낼 데이터 저장하기
    request.setAttribute("total", total);
    request.setAttribute("boardList", boardList);
    request.setAttribute("sort", sort);
    request.setAttribute("display", display);
    request.setAttribute("paging", pageUtils.getPaging(request.getRequestURI(), sort, display));
    
    // SELECT 이후에는 forward 로 이동한다.
    return new ActionForward("/board/list.jsp", false);
    
  }

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    
    
    // 요청 파라미터 board_no (전달되지않으면 0을 사용)
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    
    // 데이터베이스로부터 결과 받아오기
    BoardDTO board = boardDAO.getBoardByNo(board_no);
    
    // 결과를 JSP 에 전달하기 위해서 request 에 저장하기
    request.setAttribute("board", board);
    
    
    // 결과가 있으면 /board/detail.jsp, 결과가 없으면 /board/list.jsp 로 forward 이동
    return new ActionForward(board != null ? "/board/detail.jsp" : "/board/list.jsp", false);
    
    
  }

  @Override
  public ActionForward registerBoard(HttpServletRequest request) {
    
    // 요청 파라미터
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    
    // 데이터베이스로 전달할 BoardDTO 객체 생성
    BoardDTO board = BoardDTO.builder()
        .title(title)
        .contents(contents)
        .build();
    
    // 데이터베이스에 추가하기
    int result = boardDAO.insertBoard(board);
    
    // /board/list.jsp 로 redirect 이동하기
    return new ActionForward(request.getContextPath() + "/list.do?register=" + result, true);
    
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {

    // 요청 파라미터
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    
    // 데이터베이스로 보낼 BoardDTO 객체 생성
    BoardDTO board = BoardDTO.builder()
        .board_no(board_no)
        .title(title)
        .contents(contents)
        .build();
        
    // 수정 
    int result = boardDAO.updateBoard(board);
    
    // /board/list.jsp 로 redirect 이동하기
    return new ActionForward(request.getContextPath() + "/list.do?modify=" + result, true);    
    
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    
    int result = boardDAO.deleteBoard(board_no);
    
    return new ActionForward(request.getContextPath() + "/list.do?remove=" + result, true);
  }
  
  @Override
  public ActionForward removeBoardList(HttpServletRequest request) {
    
    // 요청 파라미터들
    String[] board_no_list = request.getParameterValues("board_no_list");
    
    // 데이터베이스로 전달해서 삭제하기
    int result = boardDAO.deleteBoardList(String.join(",", board_no_list));
    
    // /board/list.jsp 로 redirect 이동하기
    return new ActionForward(request.getContextPath() + "/list.do?remove=" + result, true);
    
  }

}