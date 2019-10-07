<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #880e4f;">
    <a class="navbar-brand" href="#">М-Ткани</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Главная</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Контакты</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Доставка</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Категории
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <% for (Category category : categories) { %>
                    <% String categoryUrl = pageContext.getServletContext().getContextPath() + "/category?category_name=" + category.getName(); %>
                    <a class="dropdown-item" href='<%=categoryUrl%>'><%=category.getName()%></a>
                    <% } %>
                </div>
            </li>
        </ul>

        <ul class="navbar-nav ml-auto">
            <% if (username != null) { %>
            <li class="nav-item">
                <a class="nav-link" href="#"><%=username%></a>
            </li>
            <li class="nav-item">
                <form action="logout" method="post">
                    <label class="nav-link mb-0" for="logoutInput">Выход</label>
                    <input class="d-none" id="logoutInput" type="submit">
                </form>
            </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="authenticate">Вход</a>
            </li>
            <% } %>
            <li class="nav-item">
                <a class="nav-link" href="cart">Корзина</a>
            </li>
        </ul>
    </div>
</nav>
