<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>TO-DO List</title>

    <!-- Bootstrap CSS (v5.3) -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
    >
</head>

<body class="bg-light text-dark">
    <!-- log out button -->
    <div class="d-flex justify-content-end p-3 bg-dark text-white">
        <span class="me-3"><c:out value="${username}" /></span>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">Logout</a>
    </div>
    <div class="container mt-5">

        <h1 class="text-center mb-4"> Your to-do list</h1>

        <table class="table  align-middle" border="1" style="table-layout: auto; width: 100%">
            <thead class="light-secondary">
            <tr>
                <td style="width: 85%">Title</td>
                <td style="width: 15%">Status</td>
            </tr>
            </thead>
            <!-- Loop through the todos list -->
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td><c:out value="${todo.title}" /></td>
                    <td>
                        <!-- A button to toggle the done status -->
                        <form action="${pageContext.request.contextPath}/toggle" method="post" style="display:inline;">
                            <input type="hidden" name="title" value="<c:out value='${todo.title}' />">
                            <span class="me-2"><c:out value="${todo.done ? '✅' : '❌'}"/></span>
                            <button class="btn btn-secondary btn-sm" type="submit">
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
        <a class="btn btn-primary me-2" href="${pageContext.request.contextPath}/add" style="text-decoration: none; border: black">
            Add
        </a>
        <a class="btn btn-primary me-2" href="${pageContext.request.contextPath}/edit" style="text-decoration: none; border: black">
            Edit
        </a>
        <a class="btn btn-primary me-2" href="${pageContext.request.contextPath}/delete" style="text-decoration: none; border: black">
            Delete
        </a>
    </div>
</body>
</html>