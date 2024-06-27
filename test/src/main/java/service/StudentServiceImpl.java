package service;

import common.ActionForward;
import dao.StudentDAO;
import dto.StudentDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO = StudentDAO.getInstance();
    
    // 학생 목록 조회 서비스 메소드
    @Override
    public ActionForward getStudents(HttpServletRequest request) {
        List<StudentDTO> students = studentDAO.getStudents();
        request.setAttribute("students", students);
        double averageScore = studentDAO.getAverageScore();
        request.setAttribute("averageScore", averageScore);
        
        if (request.getParameter("deleteResult") != null) {
            request.setAttribute("deleteResult", request.getParameter("deleteResult"));
        }
        
        return new ActionForward("/student/list.jsp", false);
    }

    // 학번으로 학생 조회 서비스 메소드
    @Override
    public ActionForward getStudentsByNo(HttpServletRequest request) {
        int stuNo = Integer.parseInt(request.getParameter("stuNo"));
        StudentDTO student = studentDAO.getStudentsByNo(stuNo);
        
        if (request.getParameter("modifyResult") != null) {
            int modifyResult = Integer.parseInt(request.getParameter("modifyResult"));
            request.setAttribute("modifyMessage", modifyResult == 1 ? "수정되었습니다." : "수정되지 않았습니다.");
        }
        
        request.setAttribute("student", student);
        return new ActionForward("/student/detail.jsp", false);
    }

    // 학생 등록 서비스 메소드
    @Override
    public ActionForward registerStudent(HttpServletRequest request) {
        String name = request.getParameter("name");
        int kor = Integer.parseInt(request.getParameter("kor"));
        int eng = Integer.parseInt(request.getParameter("eng"));
        int math = Integer.parseInt(request.getParameter("math"));
        double ave = (kor + eng + math) / 3.0;
        String mark;
        if (ave >= 90) {
            mark = "A";
        } else if (ave >= 80) {
            mark = "B";
        } else if (ave >= 70) {
            mark = "C";
        } else if (ave >= 60) {
            mark = "D";
        } else {
            mark = "F";
        }
        
        StudentDTO student = new StudentDTO.Builder()
            .name(name)
            .kor(kor)
            .eng(eng)
            .math(math)
            .ave(ave)
            .mark(mark)
            .build();
        
        int result = studentDAO.insertStudent(student);
        String path = request.getContextPath() + (result == 1 ? "/list.do" : "/index.do");
        return new ActionForward(path, true);
    }
    
    // 학생 삭제 서비스 메소드
    @Override
    public ActionForward removeStudent(HttpServletRequest request) {
        int stuNo = Integer.parseInt(request.getParameter("stuNo"));
        int result = studentDAO.deleteStudent(stuNo);
        return new ActionForward(request.getContextPath() + "/list.do?deleteResult=" + result, true);
    }

    // 학생 정보 수정 화면 이동 서비스 메소드
    @Override
    public ActionForward editStudent(HttpServletRequest request) {
        int stuNo = Integer.parseInt(request.getParameter("stuNo"));
        StudentDTO student = studentDAO.getStudentsByNo(stuNo);
        request.setAttribute("student", student);
        return new ActionForward("/student/edit.jsp", false);
    }
    
    // 학생 정보 수정 서비스 메소드
    @Override
    public ActionForward modifyStudent(HttpServletRequest request) {
        int stu_no = Integer.parseInt(request.getParameter("stuNo"));
        String name = request.getParameter("name");
        int kor = Integer.parseInt(request.getParameter("kor"));
        int eng = Integer.parseInt(request.getParameter("eng"));
        int math = Integer.parseInt(request.getParameter("math"));
        double ave = (kor + eng + math) / 3.0;
        String mark;
        if (ave >= 90) {
            mark = "A";
        } else if (ave >= 80) {
            mark = "B";
        } else if (ave >= 70) {
            mark = "C";
        } else if (ave >= 60) {
            mark = "D";
        } else {
            mark = "F";
        }

        StudentDTO student = new StudentDTO.Builder()
            .stu_no(stu_no)
            .name(name)
            .kor(kor)
            .eng(eng)
            .math(math)
            .ave(ave)
            .mark(mark)
            .build();
        
        int result = studentDAO.updateStudent(student);
        return new ActionForward(request.getContextPath() + "/detail.do?stuNo=" + stu_no + "&modifyResult=" + result, true);
    }

    // 특정 평균 범위의 학생 조회 서비스 메소드
    @Override
    public ActionForward getStudentsByAverageRange(HttpServletRequest request) {
        double begin = Double.parseDouble(request.getParameter("begin"));
        double end = Double.parseDouble(request.getParameter("end"));
        List<StudentDTO> students = studentDAO.getStudentsByAverageRange(begin, end);
        request.setAttribute("students", students);
        return new ActionForward("/student/list.jsp", false);
    }

    // 상위 3명의 학생 조회 서비스 메소드
    @Override
    public ActionForward getTop3Students(HttpServletRequest request) {
        List<StudentDTO> students = studentDAO.getTop3Students();
        request.setAttribute("top3Students", students);
        return new ActionForward("/student/top3.jsp", false);
    }
}
