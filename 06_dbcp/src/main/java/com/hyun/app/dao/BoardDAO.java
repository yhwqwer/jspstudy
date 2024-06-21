package com.hyun.app.dao;

import java.util.List;
import java.util.Map;

import com.hyun.app.dto.BoardDTO;

public interface BoardDAO {
  List<BoardDTO> getBoardList(Map<String, Object> params);
  int getBoardCount();
  BoardDTO getBoardByNo(int board_no);
  int insertBoard(BoardDTO board);
  int updateBoard(BoardDTO board);
  int deleteBoard(int board_no);
  void close() throws Exception;
}
