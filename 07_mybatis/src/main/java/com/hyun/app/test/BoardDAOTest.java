package com.hyun.app.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hyun.app.dao.BoardDAO;
import com.hyun.app.dao.BoardDAOImpl;
import com.hyun.app.dto.BoardDTO;

public class BoardDAOTest {

  private BoardDAO boardDAO = BoardDAOImpl.getInstance();
  
  @Test
  public void test1() {
    assertEquals(1000, boardDAO.getBoardCount());
  }
  
  @Test
  public void test2() {
    Map<String, Object> params = Map.of("begin", 1
                                      , "end", 20
                                      , "column", "create_dt"
                                      , "sort", "ASC");
    List<BoardDTO> boardList = boardDAO.getBoardList(params);
    boardList.stream().forEach(board -> System.out.println(board));
  }
  
  @Test
  public void test3() {
    BoardDTO board = boardDAO.getBoardByNo(1);
    assertNotNull(board);
  }

  @Test
  public void test4() {
    BoardDTO board = BoardDTO.builder()
        .title("테스트제목")
        .contents("테스트내용")
        .build();
    assertEquals(1, boardDAO.insertBoard(board));
  }
  
  @Test
  public void test5() {
    BoardDTO board = BoardDTO.builder()
        .title("수정테스트제목")
        .contents("수정테스트내용")
        .boardNo(1001)
        .build();
    assertEquals(1, boardDAO.updateBoard(board));
  }
  
  @Test
  public void test6() {
    assertEquals(1, boardDAO.deleteBoard(1001));
  }
  
}
