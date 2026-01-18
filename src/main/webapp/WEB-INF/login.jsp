<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- Bootstrap CSS (v5.3) -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
    >
</head>
<body class="bg-dark d-flex justify-content-center align-items-center vh-100 text-dark">
<div class="card shadow-sm p-4" style="width: 400px; height: 450px">
    <h2 class="text-center mb-3">Login</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center p-2">
            <c:out value="${error}" />
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input
                    type="text"
                    id="username"
                    name="username"
                    class="form-control"
                    placeholder="Enter username"
                    value="<c:out value='${username}' />"
                    required
            >
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input
                    type="password"
                    id="password"
                    name="password"
                    class="form-control"
                    placeholder="Enter password"
                    required
            >
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>
    <hr>
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/register" class="btn btn-outline-secondary btn-sm">
            Register a new account
        </a>
    </div>
</body>
</html>
