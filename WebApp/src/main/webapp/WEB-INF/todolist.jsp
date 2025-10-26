<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> TO-DO List </title>
</head>
<body>
    <h1 align="center"> Your to-do list</h1>
    <div align="center">
        <table border="1">
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Status</td>
                </tr>
            </thead>
            <!-- Loop through the todos list -->
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.title}</td>
                    <td><c:out value="${todo.done ? '✅ Done' : '❌ Not done'}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>