<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TO-DO List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 40%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px 12px;
        }
        td:last-child {
            text-align: right;
        }
        button {
            margin: 4px;
        }
    </style>
</head>
<div align="right">
    <span style="margin-right: 10px;">${username}</span>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
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
                    <td>
                        <!-- A button to toggle the done status -->
                        <form action="${pageContext.request.contextPath}/toggle" method="post" style="display:inline;">
                            <input type="hidden" name="title" value="${todo.title}">
                            <span><c:out value="${todo.done ? '✅' : '❌'}"/></span>
                            <button type="submit">
                                <c:choose>
                                    <c:when test="${todo.done}">Undone</c:when>
                                    <c:otherwise>Done</c:otherwise>
                                </c:choose>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button>
            <a href="${pageContext.request.contextPath}/add" style="text-decoration: none; color: black;">
                Add
            </a>
        </button>
        <button>
            <a href="${pageContext.request.contextPath}/edit" style="text-decoration: none; color: black;">
                Edit
            </a>
        </button>
        <button>
            <a href="${pageContext.request.contextPath}/delete" style="text-decoration: none; color: black;">
                Delete
            </a>
        </button>
    </div>
</body>
</html>