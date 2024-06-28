package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.StudentDAO;
import dto.StudentDTO;

public class StudentDAOTest {

  private StudentDAO studentDAO = StudentDAO.getInstance();

  @Test
  public void 학생등록테스트() {
    StudentDTO student = new StudentDTO.Builder()
        .name("이민형") // 테스트 이름
        .kor(51)
        .eng(45)
        .math(78)
        .ave((51 + 45 + 78) / 3.0)  // 평균 점수 계산
        .mark(calculateMark((51 + 45 + 78) / 3.0)) // 평균 점수에 따른 등급 계산
        .build();
     
    // studentDAO.insertStudent(student) 메소드가 1을 반환하면 테스트 성공
    assertEquals(1, studentDAO.insertStudent(student));
  }

  @Test
  public void 학생수정테스트() {
    StudentDTO student = new StudentDTO.Builder()
        .stu_no(3) // 수정할 학생의 번호
        .name("박철민")
        .kor(85)
        .eng(39)
        .math(60)
        .ave((85 + 39 + 60) / 3.0) // 평균 점수 계산
        .mark(calculateMark((85 + 39 + 60) / 3.0)) // 평균 점수에 따른 등급 계산
        .build();
    
    assertEquals(1, studentDAO.updateStudent(student));
  }

  @Test
  public void 학생삭제테스트() {
    int stu_no = 2;
    assertEquals(1, studentDAO.deleteStudent(stu_no));
  }
  
  @Test
  public void 모든학생조회테스트() {
    assertEquals(4, studentDAO.getStudents().size());
  }
  
  @Test
  public void 특정학생조회테스트() {
    int stu_no = 1;
    StudentDTO student = studentDAO.getStudentsByNo(stu_no);
    assertNotNull(student);
    assertEquals(stu_no, student.getStu_no());
  }

  private String calculateMark(double ave) {
    if (ave >= 90) {
      return "A";
    } else if (ave >= 80) {
      return "B";
    } else if (ave >= 70) {
      return "C";
    } else if (ave >= 60) {
      return "D";
    } else {
      return "F";
    }
  }
}
