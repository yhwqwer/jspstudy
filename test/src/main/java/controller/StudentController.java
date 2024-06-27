package controller;

import java.io.IOException;

import common.ActionForward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import service.StudentServiceImpl;

public class StudentController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        int beginIndex = requestURI.indexOf(contextPath) + contextPath.length() + 1;
        String urlMapping = requestURI.substring(beginIndex);

        StudentService studentService = new StudentServiceImpl();

        ActionForward actionForward = null;

        switch(urlMapping) {
            case "write.do":
                actionForward = new ActionForward("/student/write.jsp", false);
                break;
            case "index.do":
                actionForward = new ActionForward("/index.jsp", false);
                break;
            case "list.do":
                actionForward = studentService.getStudents(request);
                break;
            case "detail.do":
                actionForward = studentService.getStudentsByNo(request);
                break;
            case "register.do":
                actionForward = studentService.registerStudent(request);
                break;
            case "delete.do":
                actionForward = studentService.removeStudent(request);
                break;
            case "edit.do":
                actionForward = studentService.editStudent(request);
                break;
            case "modify.do":
                actionForward = studentService.modifyStudent(request);
                break;
            case "getStudentsByAverageRange.do":
                actionForward = studentService.getStudentsByAverageRange(request);
                break;
            case "getTop3Students.do":
                actionForward = studentService.getTop3Students(request);
                break;
        }

        if(actionForward != null) {
            if(actionForward.isRedirect()) {
                response.sendRedirect(actionForward.getPath());
            } else {
                request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
