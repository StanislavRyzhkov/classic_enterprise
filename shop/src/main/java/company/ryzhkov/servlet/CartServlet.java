package company.ryzhkov.servlet;

import company.ryzhkov.api.CartService;
import company.ryzhkov.api.CategoryRepository;
import company.ryzhkov.entity.Product;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    @Inject
    private CartService cartService;

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Map.Entry<Product, Integer>> cartItems = cartService.getCart().entrySet();
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("username", username);
        req.setAttribute("categories", categoryRepository.getAll());
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("totalPrice", cartService.totalPrice());
        req.setAttribute("isEmpty", cartItems.isEmpty());
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }
}
