<%@ page import="company.ryzhkov.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="company.ryzhkov.entity.Product" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String username = (String) request.getAttribute("username");
    Set<Map.Entry<Product, Integer>> cartItems = (Set<Map.Entry<Product, Integer>>) request.getAttribute("cartItems");
    boolean isEmpty = (boolean) request.getAttribute("isEmpty");
    int totalPrice = (int) request.getAttribute("totalPrice");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru-ru">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Корзина"/>
</jsp:include>
    <body>
        <div class="wrap">
            <%@include file="nav.jsp"%>
            <section class="py-5">
                <div class="container">
                    <div class="row">
                        <% if (isEmpty) { %>
                        <h1>Корзина пуста</h1>
                        <% } else { %>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Артикул</th>
                                <th scope="col">Категория</th>
                                <th scope="col">Количество</th>
                                <th scope="col">Цена</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for(Map.Entry<Product, Integer> cartItem : cartItems) { %>
                            <tr>
                                <td><%=cartItem.getKey().getVendorCode()%></td>
                                <td><%=cartItem.getKey().getCategory().getName()%></td>
                                <td><%=cartItem.getValue()%></td>
                                <td><%=cartItem.getKey().getPrice()%></td>
                            </tr>
                            <% } %>
                            <tr>
                                <td>Итого, цена заказа:</td>
                                <td></td>
                                <td></td>
                                <td><%=totalPrice%></td>
                            </tr>
                            </tbody>
                        </table>
                        <% } %>
                    </div>
                </div>
            </section>
        </div>
        <footer></footer>
        <jsp:include page="script.jsp">
            <jsp:param name="" value=""/>
        </jsp:include>
    </body>
</html>
