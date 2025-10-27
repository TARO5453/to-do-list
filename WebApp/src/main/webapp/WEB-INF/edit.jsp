<html>
<body>
<div align="center">
    <h2>Edit to-do</h2>
    <p>${error}</p>
    <form action="/edit" method="post">
        Current title:<br/>
        <input type="text" name="oldTitle"/>
        <br/>
        New title:<br/>
        <input type="text" name="newTitle"/>
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