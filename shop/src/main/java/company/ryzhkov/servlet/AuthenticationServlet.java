package company.ryzhkov.servlet;

import company.ryzhkov.api.CartService;
import company.ryzhkov.api.CategoryRepository;
import company.ryzhkov.entity.User;
import company.ryzhkov.repository.UserRepositoryBean;
import company.ryzhkov.util.PasswordEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/authenticate")
public class AuthenticationServlet extends HttpServlet {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepositoryBean userRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CartService cartService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String username = req.getParameter("username");
        String rawPassword = req.getParameter("password");
        req.setAttribute("totalPrice", cartService.totalPrice());
        req.setAttribute("categories", categoryRepository.getAll());
        if (username == null || username.isEmpty()) {
            errors.add("Поле username пустое");
            req.setAttribute("errors", errors);
            return;
        }
        if (rawPassword == null || rawPassword.isEmpty()) {
            errors.add("Поле password пустое");
            req.setAttribute("errors", errors);
            return;
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            errors.add("Неправильное имя пользователя или пароль");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
            return;
        }
        if (!passwordEncoder.decode(rawPassword, user.getPassword())) {
            errors.add("Неправильное имя пользователя или пароль");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("username", user.getUsername());
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("totalPrice", cartService.totalPrice());
        req.setAttribute("categories", categoryRepository.getAll());
        req.setAttribute("username", username);
        req.getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
    }
}
