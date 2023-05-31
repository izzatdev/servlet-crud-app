package com.example.demo1;

import com.example.demo1.entity.Student;
import com.example.demo1.services.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Student Info Servlet", value = "/student-info-servlet")
public class StudentInfoServlet extends HttpServlet {
    private final StudentService studentService;

    public StudentInfoServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentInfoServlet() {
        this.studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
   resp.sendRedirect("show-info.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 0;
            int size = req.getParameter("size") != null ? Integer.parseInt(req.getParameter("size")) : 10;
            String filterText =req.getParameter("filter");
            List<Student> students = studentService.getAll(page, size , filterText);
            req.getSession().setAttribute("list", students);
            req.getRequestDispatcher("show-info.jsp").forward(req, resp);
        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            writer.write(e.getMessage());
        }
    }
}
