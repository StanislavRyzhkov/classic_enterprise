<%@ page import="java.util.List" %><%
    Object o = request.getAttribute("errors");
    List<String> errors = (List<String>) o;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru-ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>
        <a href="/">ГЛАВНАЯ</a>
    </h1>
    <h4>
        <a href="register">Регистрация</a>
    </h4>
    <div>
        <form action="" method="post">
            <div>
                <label for="authUsername">Username</label>
                <input type="text" id="authUsername" name="username" required>
            </div>

            <div>
                <label for="authPassword">Password</label>
                <input type="password" id="authPassword" name="password" required>
            </div>

            <div>
                <button type="submit">Вход</button>
            </div>
        </form>
    </div>
    <div>
        <% if (errors != null && !errors.isEmpty()) { %>
        <p><%=errors.get(0)%></p>
        <% } %>
    </div>

</body>
</html>
