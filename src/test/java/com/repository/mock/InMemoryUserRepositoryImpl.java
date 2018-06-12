package com.repository.mock;

import model.User;
import repository.UserRepository;
import com.UserTestData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUserRepositoryImpl implements UserRepository{

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(UserTestData.USER0.getId(), UserTestData.USER0);
        repository.put(UserTestData.USER1.getId(), UserTestData.USER1);
        repository.put(UserTestData.USER2.getId(), UserTestData.USER2);
        repository.put(UserTestData.USER3.getId(), UserTestData.USER3);
        repository.put(UserTestData.USER4.getId(), UserTestData.USER4);
        repository.put(UserTestData.USER5.getId(), UserTestData.USER5);
        repository.put(UserTestData.USER6.getId(), UserTestData.USER6);
        repository.put(UserTestData.USER7.getId(), UserTestData.USER7);
        repository.put(UserTestData.USER8.getId(), UserTestData.USER8);
        repository.put(UserTestData.USER9.getId(), UserTestData.USER9);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getFirstName).thenComparing(User::getLastName))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }
}
