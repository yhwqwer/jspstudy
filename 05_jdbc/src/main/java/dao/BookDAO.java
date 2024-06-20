package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BookDTO;

/* DAO : Database Access Object (Singleton Pattern) */

public class BookDAO {

  /* Singleton Pattern */
  private BookDAO() { }  // BookDAO 내부에서만 new BookDAO() 호출 가능함
  private static BookDAO bookDAO = new BookDAO();  // BookDAO 객체를 하나 만들어 둠
  public static BookDAO getInstance() {
    return bookDAO;  // 만들어 둔 BookDAO 객체를 반환함
  }
  
  /* Field : 모든 메소드가 사용할 공통 요소 */
  private Connection conn;       // 접속 담당
  private PreparedStatement ps;  // 쿼리 실행
  private ResultSet rs;          // SELECT 문 결과 담당
  
  /* Database Connection */
  private void connection() throws Exception {
    
    // ojdbc6.jar 에 포함되어 있는 오라클 드라이버 클래스 실행
    Class.forName("oracle.jdbc.OracleDriver");
    
    // 접속 정보
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "GREEN";
    String password = "GREEN";
    
    // 접속 (접속되면 java.sql.Connection 객체 반환됨)
    conn = DriverManager.getConnection(url, user, password);
    
  }
  
  /* Database Resource Close */
  private void close() throws Exception {
    if(conn != null) conn.close();
    if(ps != null) ps.close();
    if(rs != null) rs.close();
  }
  
  /* 여기서부터 실제 Database 를 처리하는 메소드 */
  
  /* 모든 책 조회하기 */
  // 매개변수 : 없음
  // 반환타입 : List<BookDTO>
  public List<BookDTO> getBooks() {
    
    List<BookDTO> books = new ArrayList<BookDTO>();
    
    try {
      
      connection();
      
      String sql = "SELECT book_no, title, author, price FROM book_t ORDER BY book_no DESC";
      
      ps = conn.prepareStatement(sql);
      
      rs = ps.executeQuery();
      
      while(rs.next()) {        
        BookDTO book = BookDTO.builder()
            .book_no(rs.getInt(1))
            .title(rs.getString(2))
            .author(rs.getString(3))
            .price(rs.getInt(4))
            .build();
        books.add(book);
      }
      
      close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return books;
    
  }
  
  /* 특정 책 조회하기 */
  // 매개변수 : int book_no (조회할 책의 번호이다.)
  // 반환타입 : BookDTO
  public BookDTO getBookByNo(int book_no) {
    
    BookDTO book = null;
    
    try {
      
      connection();
      
      String sql = "SELECT book_no, title, author, price FROM book_t WHERE book_no = ?";
      
      ps = conn.prepareStatement(sql);
      
      ps.setInt(1, book_no);
      
      rs = ps.executeQuery();
      
      if(rs.next()) {
        book = BookDTO.builder()
            .book_no(rs.getInt(1))
            .title(rs.getString(2))
            .author(rs.getString(3))
            .price(rs.getInt(4))
            .build();
      }
      
      close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return book;
    
  }
  
  /* 신규 책 추가하기 */
  // 매개변수 : BookDTO book (신규 책 정보가 저장되어 있다.)
  // 반환타입 : int (테이블에 추가된 행의 개수가 반환된다. 즉 1은 성공, 0은 실패를 의미한다.)
  public int insertBook(BookDTO book) {
    
    int result = 0;
    
    try {
      
      connection();
      
      String sql = "INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, ?, ?, ?)";  // ? 자리에 변수 값이 들어갈 예정
      
      ps = conn.prepareStatement(sql);   // 미리 준비한 statement(쿼리문) 전달
      
      ps.setString(1, book.getTitle());  // 1번째 ?에 String title 넣기
      ps.setString(2, book.getAuthor()); // 2번째 ?에 String author 넣기
      ps.setInt(3, book.getPrice());     // 3번째 ?에 int price 넣기
      
      result = ps.executeUpdate();       // 쿼리 실행 후 실행 결과(1 또는 0) 반환
      
      close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
    
  }
  
  /* 기존 책 수정하기 */
  // 매개변수 : BookDTO book (수정할 책의 정보가 저장되어 있다.)
  // 반환타입 : int (테이블에서 수정된 행의 개수가 반환된다.)
  public int updateBook(BookDTO book) {
    
    int result = 0;
    
    try {
      
      connection();
      
      String sql = "UPDATE book_t SET title = ?, author = ?, price = ? WHERE book_no = ?";
      
      ps = conn.prepareStatement(sql);
      
      ps.setString(1, book.getTitle());
      ps.setString(2, book.getAuthor());
      ps.setInt(3, book.getPrice());
      ps.setInt(4, book.getBook_no());
      
      result = ps.executeUpdate();
      
      close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
    
  }
  
  /* 기존 책 삭제하기 */
  // 매개변수 : int book_no (삭제할 책의 번호이다.)
  // 반환타입 : int (테이블에서 삭제된 행의 개수가 반환된다.)
  public int deleteBook(int book_no) {
    
    int result = 0;
    
    try {
      
      connection();
      
      String sql = "DELETE FROM book_t WHERE book_no = ?";
      
      ps = conn.prepareStatement(sql);
      
      ps.setInt(1, book_no);
      
      result = ps.executeUpdate();
      
      close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
    
  }
  
}