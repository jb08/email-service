package persistence;

import java.util.Optional;

import dto.User;

public interface UserDao {

    Optional<User> find(String name);
}
