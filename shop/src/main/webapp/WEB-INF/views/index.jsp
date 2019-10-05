<%@ page import="java.util.Collection" %>
<%@ page import="company.ryzhkov.entity.Product" %>
<%@ page import="company.ryzhkov.entity.Category" %>
<%@ page import="java.util.List" %>
<%
    Object o = request.getAttribute("products");
    Object username = (String) request.getAttribute("username");
    Collection<Product> products = (Collection<Product>) o;
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
        <section>
            <div class="bd-example">
                <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="../../image/b1x.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>First slide label</h5>
                                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="../../image/b2x.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Second slide label</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="../../image/b4x.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Third slide label</h5>
                                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </section>

        <section class="py-5">
            <div class="container">
                <div class="row">
                    <% for (Product product : products) { %>
                    <a href="authenticate" class="p-3 green col-md-4" style="display: block; text-decoration: none;">
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
            <jsp:param name="" value="" />
        </jsp:include>
    </body>
</html>
