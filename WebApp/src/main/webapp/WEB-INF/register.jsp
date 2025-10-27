<html>
<body>
<div align="center">
    <h2>Sign in</h2>
    <p>${error}</p>
    <form action="/register" method="post">
        Username:<br/>
        <input type="text" name="username"/>
        <br/>
        Password:<br/>
        <input type="password" name="password">
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <button>
        <a href="${pageContext.request.contextPath}/login" style="text-decoration: none; color: black;">
            Cancel
        </a>
    </button>
</div>
</body>
</html>