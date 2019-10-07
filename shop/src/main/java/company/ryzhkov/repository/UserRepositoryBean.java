package company.ryzhkov.repository;

import company.ryzhkov.entity.Role;
import company.ryzhkov.entity.User;
import company.ryzhkov.util.PasswordEncoder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Stateless
public class UserRepositoryBean extends AbstractRepositoryBean {

    @Inject
    private PasswordEncoder passwordEncoder;

    @EJB
    private RoleRepositoryBean roleRepository;

    public User findByUsername(String username) {
        List<User> users = em.createQuery("SELECT u from User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        if (users.size() < 1) return null;
        return users.get(0);
    }

    public boolean isUsernameUnique(String username) {
        if (username == null || username.isEmpty()) return false;
        return em.createQuery("SELECT COUNT(e.id) FROM User e WHERE e.username = :username", Long.class)
                .setParameter("username", username)
                .setMaxResults(1)
                .getSingleResult().equals(0L);
    }

    public boolean isEmailUnique(String email) {
        if (email == null || email.isEmpty()) return false;
        return em.createQuery("SELECT count(u.id) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getSingleResult().equals(0L);
    }

    public void createUser(String username, String email, String password) {
        User user = new User();
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = Collections.singletonList(role);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus("ACTIVE");
        user.setRoles(roles);
        em.persist(user);
    }
}
