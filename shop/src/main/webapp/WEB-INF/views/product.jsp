<%@ page import="java.util.List" %>
<%@ page import="company.ryzhkov.entity.Category" %>
<%@ page import="company.ryzhkov.entity.Product" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String username = (String) request.getAttribute("username");
    Product product = (Product) request.getAttribute("product");
    Long count = (Long) request.getAttribute("count");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="<%=product.getVendorCode()%>"/>
</jsp:include>
    <body>
        <div class="wrap">
            <%@include file="nav.jsp"%>
            <section class="py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-6 p-3">
                            <% String src = "../../img/" + product.getPicture(); %>
                            <img src='<%=src%>' class="img-fluid" alt="Responsive image">
                        </div>
                        <div class="col-6 p-3">
                            <h1><%=product.getCategory().getName()%></h1>
                            <h2><%=count%></h2>
                            <h3>Цена: <%=product.getPrice()%> рублей</h3>
                            <p>Артикул <%=product.getVendorCode()%></p>
                            <form action="product" method="post" id="addToCartForm">
                                <div class="form-group">
                                    <label for="exampleFormControlSelect1">Выберите количество</label>
                                    <input type="hidden" name="vendor_code" value='<%=product.getVendorCode()%>'>
                                    <select form="addToCartForm" class="form-control" id="exampleFormControlSelect1" name="number">
                                        <% for (int i = 0; i < product.getStock(); i++) { %>
                                        <option><%=i+1%></option>
                                        <% }  %>
                                    </select>
<%--                                    <input type="text">--%>
                                </div>
                                <button type="submit" class="btn btn-primary">Добавить в корзину</button>
                            </form>
                        </div>
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
