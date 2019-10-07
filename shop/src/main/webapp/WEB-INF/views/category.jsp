<%@ page import="company.ryzhkov.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="company.ryzhkov.entity.Product" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String username = (String) request.getAttribute("username");
    String categoryName = (String) request.getAttribute("categoryName");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="<%=categoryName%>"/>
</jsp:include>
    <body>
        <div class="wrap">
            <%@include file="nav.jsp"%>
<%--            <h1 class="m-auto text-center"><%=categoryName%></h1>--%>
            <section class="py-5">
                <div class="container">
                    <div class="row">
                        <% for (Product product : products) { %>
                        <% String productUrl = pageContext.getServletContext().getContextPath() + "/product?vendor_code=" + product.getVendorCode(); %>
                        <a href='<%=productUrl%>' class="p-3 green col-md-4" style="display: block; text-decoration: none;">
                            <div class="card border-secondary mb-3">
                                <div class="card-header"><%=product.getCategory().getName()%></div>
                                <div class="card-body text-secondary">
                                    <% String src = "../../img/" + product.getPicture(); %>
                                    <img src='<%=src%>' class="img-fluid" alt="Responsive image">
                                    <h5 class="card-title mt-2"><%=product.getPrice()%> рублей</h5>
                                </div>
                            </div>
                        </a>
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
