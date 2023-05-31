package com.example.demo1;

import com.example.demo1.entity.Student;
import com.example.demo1.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Basic Servlet", value = "/basic-servlet")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService;

    public StudentServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentServlet() {
        this.studentService = new StudentService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            if (id != null) {
                studentService.deleteStudent(Integer.parseInt(id));
            }
            List<Student> students = studentService.getAll();
            req.getSession().setAttribute("list", students);
            req.getRequestDispatcher("show.jsp").forward(req, resp);
        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            writer.write(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            if (id == null) {
                studentService.register(req);
            } else {
                studentService.update(Integer.parseInt(id), req);
            }
            resp.sendRedirect("/basic-servlet");
        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            writer.write(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}