package company.ryzhkov.servlet;

import company.ryzhkov.cart.CartService;
import company.ryzhkov.entity.OrderItem;
import company.ryzhkov.entity.Product;
import company.ryzhkov.repository.CategoryRepository;
import company.ryzhkov.repository.ProductRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Inject
    private CartService cartService;

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
        Map<Product, Integer> map = cartService.getMap();
        long count = map.size();
        req.setAttribute("count", count);
        req.setAttribute("categories", categoryRepository.getAll());
        req.setAttribute("username", username);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberVal;
        String vendorCode = req.getParameter("vendor_code");
        String number = req.getParameter("number");
        System.out.println(vendorCode);
        System.out.println(number);
        try {
            numberVal = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            resp.sendError(400);
            return;
        }
        Product product = productRepository.getByVendorCode(vendorCode);
        if (product == null) {
            resp.sendError(400);
            return;
        }
        if (numberVal < 1 || numberVal > product.getStock()) {
            resp.sendError(400);
            return;
        }
        cartService.addToCart(product, numberVal);
        resp.sendRedirect(String.format("/product?vendor_code=%s", vendorCode));
    }
}
