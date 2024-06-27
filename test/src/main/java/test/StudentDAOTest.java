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
        .name("정준하") // 테스트 이름
        .kor(57)
        .eng(42)
        .math(95)
        .ave((57 + 42 + 95) / 3.0)
        .mark(calculateMark((57 + 42 + 95) / 3.0))
        .build();
     
    // studentDAO.insertStudent(student) 결과가 1이면 테스트를 성공한다.
    assertEquals(1, studentDAO.insertStudent(student));
  }

  @Test
  public void 학생수정테스트() {
    StudentDTO student = new StudentDTO.Builder()
        .stu_no(3)
        .name("권지용")
        .kor(85)
        .eng(100)
        .math(80)
        .ave((85 + 100 + 80) / 3.0)
        .mark(calculateMark((85 + 100 + 80) / 3.0))
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
