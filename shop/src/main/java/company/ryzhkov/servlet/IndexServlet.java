package company.ryzhkov.servlet;

import company.ryzhkov.repository.CategoryRepository;
import company.ryzhkov.repository.ProductRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        req.setAttribute("username", username);
        req.setAttribute("products", productRepository.getAll());
        req.setAttribute("categories", categoryRepository.getAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
