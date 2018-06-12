package repository.mock;

import model.User;
import repository.UserRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepositoryImpl implements UserRepository{

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
