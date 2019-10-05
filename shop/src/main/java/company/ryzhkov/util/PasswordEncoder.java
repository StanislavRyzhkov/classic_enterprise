package company.ryzhkov.util;

public interface PasswordEncoder {

    String encode(String rawPassword);

    boolean decode(String rawPassword, String passwordHash);
}
