package com.project.scm.Implementations;

import com.project.scm.Dao.UserDao;
import com.project.scm.Entities.User;
import com.project.scm.Helpers.AppConstants;
import com.project.scm.Helpers.ResourceNotFoundException;
import com.project.scm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        // password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
//        String emailToken = UUID.randomUUID().toString();
        return userDao.save(user);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> updateUserById(User user) {
        User user2 = userDao.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("Resource not found or not present"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());

        User saved = userDao.save(user2);
        return Optional.ofNullable(saved);

    }

    @Override
    public void deleteUser(long id) {
        User user2 = userDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found or not present"));
        userDao.delete(user2);
    }

    @Override
    public Boolean isUserExist(long id) {
        User user2 = userDao.findById(id).orElse(null);
        return user2!=null?true:false;
    }

    @Override
    public Boolean isUserExistByEmail(String email) {
        User user2 = userDao.findByEmail(email).orElse(null);
        return user2!=null?true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }
}
