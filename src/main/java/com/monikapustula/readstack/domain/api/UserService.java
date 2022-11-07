package com.monikapustula.readstack.domain.api;

import com.monikapustula.readstack.domain.user.User;
import com.monikapustula.readstack.domain.user.UserDao;

import java.time.LocalDateTime;

public class UserService {

    private final UserDao userDao = new UserDao();

    public void register(UserRegistration userRegistration) {
        User userToRegister = userRegistrationMapper(userRegistration);
        userDao.save(userToRegister);
    }

    private static User userRegistrationMapper(UserRegistration userRegistration) {
        return new User(
                userRegistration.getUsername(),
                userRegistration.getEmail(),
                userRegistration.getPassword(),
                LocalDateTime.now());
    }
}
