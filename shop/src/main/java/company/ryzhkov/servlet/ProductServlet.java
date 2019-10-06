package company.ryzhkov.servlet;

import company.ryzhkov.entity.Product;
import company.ryzhkov.repository.CategoryRepository;
import company.ryzhkov.repository.ProductRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vendorCode = req.getParameter("vendor_code");
        if (vendorCode == null) {
            resp.sendError(404);
            return;
        }
        Product product = productRepository.getByVendorCode(vendorCode);
        if (product == null) {
            resp.sendError(404);
            return;
        }
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("categories", categoryRepository.getAll());
        req.setAttribute("username", username);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
    }
}
