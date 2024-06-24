package com.hyun.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.hyun.app.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {

  /* Connection Pool 관리 (생성자에서 context.xml 의 내용을 읽어서 dataSource 객체를 생성한다.) */
  private DataSource dataSource;
  
  /* Singleton Pattern */
  private BoardDAOImpl() {
    try {
      Context ctx = new InitialContext();
      Context env = (Context)ctx.lookup("java:comp/env");
      dataSource = (DataSource)env.lookup("jdbc/myoracle");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static BoardDAO boardDAO = new BoardDAOImpl();
  public static BoardDAO getInstance() {
    return boardDAO;
  }
  
  /* Field */
  private Connection conn;
  private PreparedStatement ps;
  private ResultSet rs;
  
  @Override
  public List<BoardDTO> getBoardList(Map<String, Object> params) {
    
    List<BoardDTO> boardList = new ArrayList<BoardDTO>();
    
    try {
      conn = dataSource.getConnection();
      StringBuilder builder = new StringBuilder();
      builder.append("SELECT board_no, title, contents, create_dt, modify_dt");
      builder.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY board_no " + params.get("sort") + ") AS rnum, board_no, title, contents, create_dt, modify_dt");
      builder.append("          FROM board_t)");
      builder.append(" WHERE rnum BETWEEN ? AND ?");
      String sql = builder.toString();
      ps = conn.prepareStatement(sql);
      ps.setInt(1, (int)params.get("begin"));
      ps.setInt(2, (int)params.get("end"));
      rs = ps.executeQuery();
      while(rs.next()) {
        BoardDTO board = BoardDTO.builder()
            .board_no(rs.getInt(1))
            .title(rs.getString(2))
            .contents(rs.getString(3))
            .create_dt(rs.getDate(4))
            .modify_dt(rs.getDate(5))
            .build();
        boardList.add(board);
      }
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return boardList;
    
  }

  @Override
  public int getBoardCount() {
    
    int count = 0;
    
    try {
      conn = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM board_t";
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next())
        count = rs.getInt(1);
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return count;
    
  }

  @Override
  public BoardDTO getBoardByNo(int board_no) {
    
    BoardDTO board = null;
    
    try {
      conn = dataSource.getConnection();
      String sql = "SELECT board_no, title, contents, create_dt, modify_dt FROM board_t WHERE board_no = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();
      if(rs.next()) {
        board = BoardDTO.builder()
            .board_no(rs.getInt(1))
            .title(rs.getString(2))
            .contents(rs.getString(3))
            .create_dt(rs.getDate(4))
            .modify_dt(rs.getDate(5))
            .build();
      }
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return board;
  }

  @Override
  public int insertBoard(BoardDTO board) {
    
    int result = 0;
    
    try {
      conn = dataSource.getConnection();
      String sql = "INSERT INTO board_t(board_no, title, contents, create_dt, modify_dt) VALUES(board_seq.nextval, ?, ?, CURRENT_DATE, CURRENT_DATE)";
      ps = conn.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      result = ps.executeUpdate();
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
    
  }

  @Override
  public int updateBoard(BoardDTO board) {

    int result = 0;
    
    try {
      conn = dataSource.getConnection();
      String sql = "UPDATE board_t SET title = ?, contents = ?, modify_dt = CURRENT_DATE WHERE board_no = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());
      result = ps.executeUpdate();
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
    return result;
  }

  @Override
  public int deleteBoard(int board_no) {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override
  public int deleteBoardList(String board_no_list) {

    int result = 0;
    
    try {
      conn = dataSource.getConnection();
      String sql = "DELETE FROM board_t WHERE board_no IN(" + board_no_list + ")";
      ps = conn.prepareStatement(sql);
      result = ps.executeUpdate();
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
  }
  @Override
  public void close() throws Exception {
    if(conn != null) conn.close();
    if(ps != null) ps.close();
    if(rs != null) rs.close();
  }

}
