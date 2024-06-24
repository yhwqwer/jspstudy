package com.hyun.app.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hyun.app.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {

  // MyBatis Framework 사용 시 SqlSessionFactory 객체 선언 (Connection/PreparedStatement/Result)
  private SqlSessionFactory factory;
  
  // Singleton Pattern
  private BoardDAOImpl() {
    // SqlSessionFactory factory Build
    try {
      String resource = "com/hyun/app/config/mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static BoardDAO boardDAO = new BoardDAOImpl();
  public static BoardDAO getInstance() {
    return boardDAO;
  }
  
  @Override
  public int getBoardCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<BoardDTO> getBoardList(Map<String, Object> params) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BoardDTO getBoardByNo(int boardNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int insertBoard(BoardDTO board) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int updateBoard(BoardDTO board) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int deleteBoard(int boardNo) {
    // TODO Auto-generated method stub
    return 0;
  }

}
