<%@ page import="java.util.List" %>
<%@ page import="company.ryzhkov.entity.Category" %>
<%
    Object o = request.getAttribute("products");
    Object username = (String) request.getAttribute("username");
    List<String> errors = (List<String>) request.getAttribute("errors");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Главная" />
</jsp:include>
<body>
    <div class="wrap">
        <%@ include file="nav.jsp" %>
        <section class="py-5">
            <div class="container">
                <div class="row">
                    <form action="authenticate" method="post" class="col-md-6 m-auto">
                        <div class="form-group">
                            <label for="authUsername">Имя пользователя</label>
                            <input name="username" type="text" class="form-control" id="authUsername" aria-describedby="emailHelp" placeholder="Введите имя пользователя">
                        </div>
                        <div class="form-group">
                            <label for="authPassword">Пароль</label>
                            <input name="password" type="password" class="form-control" id="authPassword" placeholder="Введите пароль">
                        </div>
                        <button type="submit" class="btn btn-secondary">Войти</button>
                        <div>
                            <% if (errors != null && !errors.isEmpty()) { %>
                            <p><%=errors.get(0)%></p>
                            <% } %>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <footer></footer>
    <jsp:include page="script.jsp">
        <jsp:param name="" value="" />
    </jsp:include>
</body>
</html>
