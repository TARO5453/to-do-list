<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete to-do</title>

    <!-- Bootstrap CSS (v5.3) -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
    >
</head>
<body class="bg-dark d-flex justify-content-center align-items-center vh-100">
<div class="card shadow-sm p-4 text-dark" style="width: 400px; height: 320px">

    <h2 class="text-center mb-3">Delete to-do</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center p-2">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/delete" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Title name</label>
            <input
                    type="text"
                    id="title"
                    name="title"
                    class="form-control"
                    placeholder="Enter title name"
                    required
            >
        </div>
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Delete</button>
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