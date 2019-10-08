package company.ryzhkov.api;

import company.ryzhkov.entity.User;

public interface AccountRepository {

    void createAccount(User user);
}
