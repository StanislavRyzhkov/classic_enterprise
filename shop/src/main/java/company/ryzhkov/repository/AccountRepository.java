package company.ryzhkov.repository;

import company.ryzhkov.entity.Account;
import company.ryzhkov.entity.User;

import javax.ejb.Stateless;

@Stateless
public class AccountRepository extends AbstractRepository {

    public void createAccount(User user) {
        Account account = new Account();
        account.setUser(user);
        em.persist(account);
    }
}
