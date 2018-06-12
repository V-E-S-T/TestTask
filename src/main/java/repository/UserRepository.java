package repository;

import model.User;

public interface UserRepository {

    User save(User user);
    User get(int id);
    boolean delete(int id);
}
