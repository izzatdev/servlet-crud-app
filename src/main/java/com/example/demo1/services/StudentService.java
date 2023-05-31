package com.example.demo1.services;

import com.example.demo1.dao.StudentDao;
import com.example.demo1.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo1.utils.StatementUtils.formatter;

@Getter
@Setter
public class StudentService {
    private final StudentDao studentDao;

    public StudentService() {
        this.studentDao = new StudentDao();
    }


    public void register(HttpServletRequest req) throws ClassNotFoundException {
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        if (rePassword.equals(password)) {
            Student student = new Student();
            student.setBirthday(req.getParameter("birthday"));
            student.setEnabled(true);
            student.setDateTime(LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));
            student.setPassword(password);
            student.setPhoneNumber(req.getParameter("phoneNumber"));
            student.setFullName(req.getParameter("fullName"));
            studentDao.register(student);
        } else {
            throw new IllegalArgumentException("passwords are not equal");
        }
    }

    public void deleteStudent(Integer id) throws ClassNotFoundException {
        studentDao.delete(id);
    }

    public List<Student> getAll() throws ClassNotFoundException {
        return studentDao.getAll();
    }

    public List<Student> getAll(int page, int size, String filterTxt) throws ClassNotFoundException {
        return studentDao.getAll(page, size, filterTxt);
    }

    public void update(int id, HttpServletRequest req) {
        Student student = new Student();
        student.setId(id);
        student.setBirthday(req.getParameter("birthday"));
        student.setPassword(req.getParameter("password"));
        student.setPhoneNumber(req.getParameter("phoneNumber"));
        student.setFullName(req.getParameter("fullName"));
        studentDao.update(student);
    }
}
