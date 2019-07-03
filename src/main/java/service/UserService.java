package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.User;
import exception.BadRequest;
import persistence.UserDao;

@Component
public class UserService {

    @Autowired
    UserDao userDao;

    void findAndValidateUser(String userName) {
        Optional<User> optUser = userDao.find(userName);

        if (!optUser.isPresent()) {
            throw new BadRequest(String.format("User not found with name %s", userName));
        }
    }

}
