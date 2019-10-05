<%@ page import="java.util.Collection" %>
<%@ page import="company.ryzhkov.entity.Product" %>
<%
    Object o = request.getAttribute("products");
    Object username = (String) request.getAttribute("username");
    Collection<Product> products = (Collection<Product>) o;
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
    <div>
        <ul>
            <li>
            <% if (username != null) { %>
                <%=username%>
            <% } %>
            </li>
            <li>ГЛАВНАЯ</li>
            <li>КОНТАКТЫ</li>
            <li>
                <a href="authenticate">ВХОД</a>
            </li>
        </ul>
    </div>
    <div>
        <% for (Product product : products) { %>
        <p><%=product.getName()%></p>
        <p><%=product.getPrice()%></p>
        <p><%=product.getVendorCode()%></p>
        <br>
        <% } %>
    </div>
</body>
</html>
