<%@ page import="java.util.List" %>
<%@ page import="company.ryzhkov.entity.Category" %><%
    Object o = request.getAttribute("errors");
    List<String> errors = (List<String>) o;
    List<Category> categories = (List<Category>) request.getAttribute("categories");
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
    <div>
        <form action="" method="post">
            <div>
                <label for="regUsername">Username</label>
                <input type="text" id="regUsername" name="username" required>
            </div>

            <div>
                <label for="regEmail">Email</label>
                <input type="email" id="regEmail" name="email" required>
            </div>

            <div>
                <label for="regPassword1">Password</label>
                <input type="password" id="regPassword1" name="password1" required>
            </div>

            <div>
                <label for="regPassword2">Password once more</label>
                <input type="password" id="regPassword2" name="password2" required>
            </div>

            <div>
                <button type="submit">Регистрация</button>
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
