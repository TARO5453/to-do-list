<html>
<body>
<div align="center">
    <h2>Delete to-do</h2>
    <p>${error}</p>
    <form action="/delete" method="post">
        To-do title:<br/>
        <input type="text" name="title"/>
        <br/>
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <button>
        <a href="${pageContext.request.contextPath}/todolist" style="text-decoration: none; color: black;">
            Cancel
        </a>
    </button>
</div>
</body>
</html>