package company.ryzhkov.servlet;

import company.ryzhkov.entity.User;
import company.ryzhkov.repository.CategoryRepositoryBean;
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
    private CategoryRepositoryBean categoryRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryRepository.getAll());
        List<String> errors = new ArrayList<>();
        String username = req.getParameter("username");
        String rawPassword = req.getParameter("password");
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
        req.setAttribute("categories", categoryRepository.getAll());
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        req.setAttribute("username", username);
        req.getRequestDispatcher("/WEB-INF/views/authentication.jsp").forward(req, resp);
    }
}
