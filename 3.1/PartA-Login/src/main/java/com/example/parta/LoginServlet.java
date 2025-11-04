package com.example.parta;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = username != null && password != null && !username.isEmpty() && !password.isEmpty();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (isValid && "admin".equals(username) && "admin123".equals(password)) {
                out.println("<!DOCTYPE html><html><head><title>Welcome</title></head><body>");
                out.println("<h2>Welcome, " + username + "!</h2>");
                out.println("</body></html>");
            } else {
                out.println("<!DOCTYPE html><html><head><title>Login Failed</title></head><body>");
                out.println("<h3>Invalid credentials. Please try again.</h3>");
                out.println("<a href='index.html'>Back to Login</a>");
                out.println("</body></html>");
            }
        }
    }
}


