package com.example.demo1.dao;

import com.example.demo1.entity.Student;
import com.example.demo1.utils.ConnectionsUtils;
import com.example.demo1.utils.StatementUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.utils.StatementUtils.formatter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDao {
    public List<Student> getAll(int page, int size, String filter) throws ClassNotFoundException {
        switch (filter) {
            case "phone":
                filter = "phone_number";
                break;
            case "id":
                filter = "id DESC";
                break;
            case "birthdate":
                filter = "birthday";
                break;
            case "name":
                filter = "full_name";
                break;
            default:
                filter = "date_time";
                break;
        }
        final String FIND_ALL_STUDENTS = "select * from students" +
                " where students.is_enabled" +
                " order by students." + filter +
                " limit ? offset(?)";
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionsUtils
                .getConnection();
             PreparedStatement preparedStatement = StatementUtils.getPreparedStatement(connection, FIND_ALL_STUDENTS)
        ) {
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, page);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(Integer.parseInt(rs.getString("id")));
                student.setFullName(rs.getString("full_name"));
                student.setPassword(rs.getString("password"));
                student.setEnabled(rs.getBoolean("is_enabled"));
                LocalDateTime dateTime = LocalDateTime.parse(rs.getString("date_time"), formatter);
                student.setDateTime(dateTime);
                student.setBirthday(rs.getString("birthday"));
                student.setPhoneNumber(rs.getString("phone_number"));
                students.add(student);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    public void register(Student student) throws ClassNotFoundException {
        final String INSERT_STUDENTS_SQL = "insert into students" +
                " (full_name, is_enabled, password, date_time, birthday, phone_number)" +
                " values (?,?,?,?,?,?);";

        try (Connection connection = ConnectionsUtils.getConnection();
             PreparedStatement preparedStatement = StatementUtils.getPreparedStatement(connection, INSERT_STUDENTS_SQL)) {
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setBoolean(2, student.isEnabled());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(student.getDateTime()));
            preparedStatement.setString(5, student.getBirthday());
            preparedStatement.setString(6, student.getPhoneNumber());

            preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void update(Student student) {
        final String UPDATE_STUDENT = "update students" +
                " set birthday  = ?," +
                " password  = ?," +
                " full_name = ?," +
                " phone_number = ?" +
                " where id = ?;";

        try (Connection connection = ConnectionsUtils.getConnection();
             PreparedStatement preparedStatement = StatementUtils.getPreparedStatement(connection, UPDATE_STUDENT)) {
            preparedStatement.setString(3, student.getFullName());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(1, student.getBirthday());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setInt(5, student.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAll() throws ClassNotFoundException {
        final String FIND_ALL_STUDENTS = "select * from students" +
                " where students.is_enabled" +
                " order by students.date_time;";
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionsUtils
                .getConnection();
             PreparedStatement preparedStatement = StatementUtils.getPreparedStatement(connection, FIND_ALL_STUDENTS)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(Integer.parseInt(rs.getString("id")));
                student.setFullName(rs.getString("full_name"));
                student.setBirthday(rs.getString("birthday"));
                student.setPhoneNumber(rs.getString("phone_number"));
                students.add(student);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;

    }

    public void delete(Integer id) throws ClassNotFoundException {
        final String DELETE_STUDENT = "delete from students where id = ?";
        try (Connection connection = ConnectionsUtils
                .getConnection();
             PreparedStatement preparedStatement = StatementUtils.getPreparedStatement(connection, DELETE_STUDENT)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
