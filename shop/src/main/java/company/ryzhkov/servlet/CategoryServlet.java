package company.ryzhkov.servlet;

import company.ryzhkov.api.ProductRepository;
import company.ryzhkov.entity.Category;
import company.ryzhkov.entity.Product;
import company.ryzhkov.repository.CategoryRepositoryBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepositoryBean categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("category_name");
        if (categoryName == null) {
            resp.sendError(404);
            return;
        }
        List<Product> products = productRepository.filterByCategoryName(categoryName);
        if (products.isEmpty()) {
            resp.sendError(404);
            return;
        }
        List<Category> categories = categoryRepository.getAll();
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("products", products);
        req.setAttribute("categories", categories);
        req.setAttribute("username", username);
        req.setAttribute("categoryName", categoryName);
        req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
    }
}
