package com.example.partc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AttendanceServlet", urlPatterns = {"/submit-attendance"})
public class AttendanceServlet extends HttpServlet {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jdbcUrl = config.getServletContext().getInitParameter("jdbc.url");
        jdbcUsername = config.getServletContext().getInitParameter("jdbc.username");
        jdbcPassword = config.getServletContext().getInitParameter("jdbc.password");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (studentId == null || studentId.isEmpty() || date == null || date.isEmpty() || status == null || status.isEmpty()) {
                out.println("<!DOCTYPE html><html><body>");
                out.println("<h3>All fields are required.</h3>");
                out.println("<a href='attendance.jsp'>Back</a>");
                out.println("</body></html>");
                return;
            }

            String sql = "INSERT INTO attendance (student_id, class_date, status) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, studentId);
                ps.setString(2, date);
                ps.setString(3, status);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException("Failed to save attendance", e);
            }

            out.println("<!DOCTYPE html><html><body>");
            out.println("<h3>Attendance recorded for student " + escape(studentId) + " on " + escape(date) + "</h3>");
            out.println("<a href='attendance.jsp'>Record another</a>");
            out.println("</body></html>");
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}


