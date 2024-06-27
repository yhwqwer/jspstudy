package dao;

import dto.StudentDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private static StudentDAO instance = new StudentDAO();

    private StudentDAO() {}

    public static StudentDAO getInstance() {
        return instance;
    }

    // 데이터베이스 연결 메서드
    private void connection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "GREEN";
        String password = "GREEN";
        conn = DriverManager.getConnection(url, user, password);
    }

    // 데이터베이스 리소스 해제 메서드
    private void close() throws Exception {
        if (conn != null) conn.close();
        if (ps != null) ps.close();
        if (rs != null) rs.close();
    }

    // 학생 목록 조회 메서드
    public List<StudentDTO> getStudents() {
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT stu_no, name, kor, eng, math, ave, mark FROM student_t ORDER BY stu_no";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentDTO student = new StudentDTO.Builder()
                    .stu_no(rs.getInt("stu_no"))
                    .name(rs.getString("name"))
                    .kor(rs.getInt("kor"))
                    .eng(rs.getInt("eng"))
                    .math(rs.getInt("math"))
                    .ave(rs.getDouble("ave"))
                    .mark(rs.getString("mark"))
                    .build();
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    // 학번으로 학생 조회 메서드
    public StudentDTO getStudentsByNo(int stuNo) {
        StudentDTO student = null;
        String sql = "SELECT stu_no, name, kor, eng, math, ave, mark FROM student_t WHERE stu_no = ?";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stuNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new StudentDTO.Builder()
                    .stu_no(rs.getInt("stu_no"))
                    .name(rs.getString("name"))
                    .kor(rs.getInt("kor"))
                    .eng(rs.getInt("eng"))
                    .math(rs.getInt("math"))
                    .ave(rs.getDouble("ave"))
                    .mark(rs.getString("mark"))
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    // 학생 등록 메서드
    public int insertStudent(StudentDTO student) {
        int result = 0;
        String sql = "INSERT INTO student_t (stu_no, name, kor, eng, math, ave, mark) VALUES (student_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getKor());
            ps.setInt(3, student.getEng());
            ps.setInt(4, student.getMath());
            ps.setDouble(5, student.getAve());
            ps.setString(6, student.getMark());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 학생 정보 수정 메서드
    public int updateStudent(StudentDTO student) {
        int result = 0;
        String sql = "UPDATE student_t SET name = ?, kor = ?, eng = ?, math = ?, ave = ?, mark = ? WHERE stu_no = ?";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getKor());
            ps.setInt(3, student.getEng());
            ps.setInt(4, student.getMath());
            ps.setDouble(5, student.getAve());
            ps.setString(6, student.getMark());
            ps.setInt(7, student.getStu_no());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 학생 삭제 메서드
    public int deleteStudent(int stuNo) {
        int result = 0;
        String sql = "DELETE FROM student_t WHERE stu_no = ?";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stuNo);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 특정 평균 범위의 학생 목록 조회 메서드
    public List<StudentDTO> getStudentsByAverageRange(double begin, double end) {
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT stu_no, name, kor, eng, math, ave, mark FROM student_t WHERE ave BETWEEN ? AND ?";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, begin);
            ps.setDouble(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentDTO student = new StudentDTO.Builder()
                    .stu_no(rs.getInt("stu_no"))
                    .name(rs.getString("name"))
                    .kor(rs.getInt("kor"))
                    .eng(rs.getInt("eng"))
                    .math(rs.getInt("math"))
                    .ave(rs.getDouble("ave"))
                    .mark(rs.getString("mark"))
                    .build();
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    // 전체 학생 평균 점수 조회 메서드
    public double getAverageScore() {
        double average = 0.0;
        String sql = "SELECT NVL(AVG(ave), 0) FROM student_t";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                average = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return average;
    }

    // 상위 3명의 학생 조회 메서드
    public List<StudentDTO> getTop3Students() {
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT stu_no, name, kor, eng, math, ave, mark FROM (SELECT stu_no, name, kor, eng, math, ave, mark, RANK() OVER(ORDER BY ave DESC) AS rnk FROM student_t) WHERE rnk <= 3";
        try {
            connection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentDTO student = new StudentDTO.Builder()
                    .stu_no(rs.getInt("stu_no"))
                    .name(rs.getString("name"))
                    .kor(rs.getInt("kor"))
                    .eng(rs.getInt("eng"))
                    .math(rs.getInt("math"))
                    .ave(rs.getDouble("ave"))
                    .mark(rs.getString("mark"))
                    .build();
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
    }
}
