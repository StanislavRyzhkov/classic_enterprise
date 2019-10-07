package company.ryzhkov.repository;

import company.ryzhkov.entity.Role;

import javax.ejb.Stateless;

@Stateless
public class RoleRepositoryBean extends AbstractRepositoryBean {

    public Role findByName(String name) {
        return em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}