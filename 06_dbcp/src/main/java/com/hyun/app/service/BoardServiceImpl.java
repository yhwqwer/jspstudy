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
    
    // /board/list.jsp 로 보낼 데이터 저장하기
    request.setAttribute("total", total);
    request.setAttribute("boardList", boardList);
    
    // SELECT 이후에는 forward 로 이동한다.
    return new ActionForward("/board/list.jsp", false);
    
  }

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward registerBoard(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

}