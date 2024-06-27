package service;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface StudentService {
  ActionForward getStudents(HttpServletRequest request);
  ActionForward getStudentsByNo(HttpServletRequest request);
  ActionForward registerStudent(HttpServletRequest request);
  ActionForward removeStudent(HttpServletRequest request);
  ActionForward editStudent(HttpServletRequest request);
  ActionForward modifyStudent(HttpServletRequest request);
  ActionForward getStudentsByAverageRange(HttpServletRequest request);
  ActionForward getTop3Students(HttpServletRequest request);

  /*
   * ActionForward insertStudent(HttpServletRequest request); ActionForward
   * updateStudent(HttpServletRequest request); ActionForward
   * deleteStudent(HttpServletRequest request); ActionForward
   * getStudentCount(HttpServletRequest request); ActionForward
   * getAverageScore(HttpServletRequest request); ActionForward
   * getStudentCountInRange(HttpServletRequest request); ActionForward
   * getAverageScoreInRange(HttpServletRequest request);
   */
  
}
