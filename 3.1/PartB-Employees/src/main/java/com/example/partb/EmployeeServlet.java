package com.example.partb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idParam = request.getParameter("id");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Employees</title>");
            out.println("<style>table{border-collapse:collapse}td,th{border:1px solid #ccc;padding:8px}body{font-family:Arial,Helvetica,sans-serif;margin:24px}</style>");
            out.println("</head><body>");
            out.println("<h2>Employees</h2>");
            out.println("<form method='get' action='employees'>Search by ID: <input name='id' type='number'/> <button type='submit'>Search</button></form><br/>");

            if (idParam != null && !idParam.isEmpty()) {
                renderEmployeeById(out, idParam);
                out.println("<p><a href='employees'>Back to list</a></p>");
            } else {
                renderAllEmployees(out);
            }

            out.println("</body></html>");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void renderAllEmployees(PrintWriter out) throws SQLException {
        String sql = "SELECT id, name, department, salary FROM employees ORDER BY id";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            out.println("<table><thead><tr><th>ID</th><th>Name</th><th>Department</th><th>Salary</th></tr></thead><tbody>");
            while (rs.next()) {
                out.println("<tr>" +
                        "<td>" + rs.getInt("id") + "</td>" +
                        "<td>" + escape(rs.getString("name")) + "</td>" +
                        "<td>" + escape(rs.getString("department")) + "</td>" +
                        "<td>" + rs.getBigDecimal("salary") + "</td>" +
                        "</tr>");
            }
            out.println("</tbody></table>");
        }
    }

    private void renderEmployeeById(PrintWriter out, String idParam) throws SQLException {
        String sql = "SELECT id, name, department, salary FROM employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idParam));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    out.println("<h3>Result</h3>");
                    out.println("<ul>" +
                            "<li>ID: " + rs.getInt("id") + "</li>" +
                            "<li>Name: " + escape(rs.getString("name")) + "</li>" +
                            "<li>Department: " + escape(rs.getString("department")) + "</li>" +
                            "<li>Salary: " + rs.getBigDecimal("salary") + "</li>" +
                            "</ul>");
                } else {
                    out.println("<p>No employee found with ID " + idParam + "</p>");
                }
            }
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}


