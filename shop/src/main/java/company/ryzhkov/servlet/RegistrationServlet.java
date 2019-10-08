package company.ryzhkov.servlet;

import company.ryzhkov.api.AccountRepository;
import company.ryzhkov.api.CategoryRepository;
import company.ryzhkov.entity.User;
import company.ryzhkov.repository.UserRepositoryBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    @Inject
    private UserRepositoryBean userRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryRepository.getAll());
        List<String> errors = new ArrayList<>();
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        if (username == null || username.isEmpty()) {
            errors.add("Поле имя пользователя пустое");
        }
        if (email == null || email.isEmpty()) {
            errors.add("Поле email пустое");
        }
        if (password1 == null || password1.isEmpty()) {
            errors.add("Поле пароль пустое");
        }
        if (password1 != null && !password1.equals(password2)) {
            errors.add("Пароли не совпадают");
        }
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            return;
        }
        if (!userRepository.isUsernameUnique(username)) {
            errors.add("Пользователь с таким именем существует");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            return;
        }
        if (!userRepository.isEmailUnique(email)) {
            errors.add("Пользователь с таким email существует");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            return;
        }
        userRepository.createUser(username, email, password1);
        User user = userRepository.findByUsername(username);
        accountRepository.createAccount(user);
        resp.sendRedirect("/authenticate");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryRepository.getAll());
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }
}
