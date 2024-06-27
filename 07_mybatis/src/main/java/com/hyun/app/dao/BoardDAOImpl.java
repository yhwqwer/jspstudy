package com.hyun.app.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hyun.app.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {

  // MyBatis Framework 사용 시 SqlSessionFactory 객체 선언 (Connection/PreparedStatement/ResultSet 대신 활용)
  private SqlSessionFactory factory;
  
  // Singleton Pattern
  private BoardDAOImpl() {
    // SqlSessionFactory factory Build
    try {
      String resource = "com/min/app/config/mybatis-config.xml";
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
  
  private final String NS = "com.min.app.dao.BoardMapper.";
  
  @Override
  public int getBoardCount() {
    SqlSession ss = factory.openSession();
    int count = ss.selectOne(NS + "getBoardCount");
    ss.close();
    return count;
  }

  @Override
  public List<BoardDTO> getBoardList(Map<String, Object> params) {
    SqlSession ss = factory.openSession();
    List<BoardDTO> boardList = ss.selectList(NS + "getBoardList", params);
    ss.close();
    return boardList;
  }

  @Override
  public BoardDTO getBoardByNo(int boardNo) {
    SqlSession ss = factory.openSession();
    BoardDTO board = ss.selectOne("getBoardByNo", boardNo);
    ss.close();
    return board;
  }

  @Override
  public int insertBoard(BoardDTO board) {
    SqlSession ss = factory.openSession(false);  // autoCommit = false
    int result = ss.insert(NS + "insertBoard", board);
    if(result == 1) ss.commit();
    ss.close();
    return result;
  }

  @Override
  public int updateBoard(BoardDTO board) {
    SqlSession ss = factory.openSession(false);  // autoCommit = false
    int result = ss.update(NS + "updateBoard", board);
    if(result > 0) ss.commit();
    ss.close();
    return result;
  }

  @Override
  public int deleteBoard(int boardNo) {
    SqlSession ss = factory.openSession(false);  // autoCommit = false
    int result = ss.delete(NS + "deleteBoard", boardNo);
    if(result > 0) ss.commit();
    ss.close();
    return result;
  }

}