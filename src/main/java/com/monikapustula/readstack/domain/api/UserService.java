package com.monikapustula.readstack.domain.api;

import com.monikapustula.readstack.domain.user.User;
import com.monikapustula.readstack.domain.user.UserDao;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class UserService {

    private final UserDao userDao = new UserDao();

    public void register(UserRegistration userRegistration) {
        User userToRegister = userRegistrationMapper(userRegistration);
        try {
            hashPasswordWithSha256(userToRegister);
            userDao.save(userToRegister);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void hashPasswordWithSha256(User user) throws NoSuchAlgorithmException {
        String sha256Password = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256Password);
    }

    private static User userRegistrationMapper(UserRegistration userRegistration) {
        return new User(
                userRegistration.getUsername(),
                userRegistration.getEmail(),
                userRegistration.getPassword(),
                LocalDateTime.now());
    }
}
