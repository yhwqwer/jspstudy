package com.hyun.app.service;

import com.hyun.app.common.ActionForward;

import jakarta.servlet.http.HttpServletRequest;

public interface BoardService {
  ActionForward getBoardList(HttpServletRequest request);
  ActionForward getBoardByNo(HttpServletRequest request);
  ActionForward registerBoard(HttpServletRequest request);
  ActionForward modifyBoard(HttpServletRequest request);
  ActionForward removeBoard(HttpServletRequest request);
}