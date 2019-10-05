package company.ryzhkov.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@NoArgsConstructor
@ApplicationScoped
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
    }

    @Override
    public boolean decode(String rawPassword, String passwordHash) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), passwordHash);
        return result.verified;
    }
}
