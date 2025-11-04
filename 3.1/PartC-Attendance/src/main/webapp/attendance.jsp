<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Part C - Student Attendance</title>
    <style>
        body{font-family:Arial,Helvetica,sans-serif;margin:40px}
        .card{max-width:420px;margin:auto;border:1px solid #ddd;border-radius:8px;padding:24px;box-shadow:0 2px 6px rgba(0,0,0,.06)}
        label{display:block;margin:12px 0 6px}
        input,select{width:100%;padding:10px;border:1px solid #ccc;border-radius:4px}
        button{margin-top:16px;padding:10px 14px;border:0;background:#2e7d32;color:#fff;border-radius:4px;cursor:pointer}
    </style>
</head>
<body>
<div class="card">
    <h2>Student Attendance</h2>
    <form action="submit-attendance" method="post">
        <label for="studentId">Student ID</label>
        <input id="studentId" name="studentId" type="text" required />

        <label for="date">Date</label>
        <input id="date" name="date" type="date" required />

        <label for="status">Status</label>
        <select id="status" name="status" required>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
            <option value="Late">Late</option>
        </select>

        <button type="submit">Save Attendance</button>
    </form>
</div>
</body>
</html>


