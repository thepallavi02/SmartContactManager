package com.project.scm.Services;

import com.project.scm.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User>getUserById(long id);
    Optional<User>updateUserById(User user);
    void deleteUser(long id);
    Boolean isUserExist(long id);
    Boolean isUserExistByEmail(String email);
    List<User>getAllUsers();

    User getUserByEmail(String userName);
}
