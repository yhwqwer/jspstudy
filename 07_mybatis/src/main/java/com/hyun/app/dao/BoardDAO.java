package com.hyun.app.dao;

import java.util.List;
import java.util.Map;

import com.hyun.app.dto.BoardDTO;

public interface BoardDAO {
  int getBoardCount();
  List<BoardDTO> getBoardList(Map<String, Object> params);
  BoardDTO getBoardByNo(int boardNo);
  int insertBoard(BoardDTO board);
  int updateBoard(BoardDTO board);
  int deleteBoard(int boardNo);
}
