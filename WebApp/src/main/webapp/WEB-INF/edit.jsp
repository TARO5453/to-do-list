<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit to-do</title>

    <!-- Bootstrap CSS (v5.3) -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
    >
</head>
<body class="bg-dark d-flex justify-content-center align-items-center vh-100">
<div class="card shadow-sm p-4 text-dark" style="width: 400px; height: 400px">

    <h2>Edit to-do</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center p-2">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/edit" method="post">
        <div class="mb-3">
            <label for="oldTitle" class="form-label">Current title</label>
            <input
                    type="text"
                    id="oldTitle"
                    name="oldTitle"
                    class="form-control"
                    placeholder="Enter current title name"
                    required
            >
        </div>
        <div class="mb-3">
            <label for="newTitle" class="form-label">New title</label>
            <input
                    type="text"
                    id="newTitle"
                    name="newTitle"
                    class="form-control"
                    placeholder="Enter current new title"
                    required
            >
        </div>
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Edit</button>
        </div>
    </form>
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/todolist" class="btn btn-outline-secondary btn-sm w-100">
            Cancel
        </a>
    </div>
</div>
</body>
</html>