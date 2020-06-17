package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Map<Integer, User> repo = new ConcurrentHashMap<>();
    private AtomicInteger counter_id = new AtomicInteger(0);

    {
        IntStream.of(2)
                .forEach(value -> save(new User("userName" + value, value + "email@mail.ru", "password", Role.USER)));
        IntStream.of(2)
                .forEach(value -> save(new User("adminName" + value, value + "emailAdmin@mail.ru", "password", Role.ADMIN)));

    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repo.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter_id.incrementAndGet());
            repo.put(user.getId(), user);
            return user;
        }
        return repo.computeIfPresent(user.getId(), (integer, oldUser) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repo.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repo.values().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repo.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
