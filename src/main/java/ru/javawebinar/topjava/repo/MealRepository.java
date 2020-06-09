package ru.javawebinar.topjava.repo;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

public interface MealRepository {
    List<Meal> findAll();

    void deleteMeal(Long id);

    Meal find(Long id);

    void update(Meal meal);

    class MealRepositoryImpl implements MealRepository {
        private static final Logger log = getLogger(MealRepositoryImpl.class);
        private final static List<Meal> repo = new CopyOnWriteArrayList<>();

        static {
            Collections.addAll(repo,
                    new Meal(1L, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                    new Meal(2L, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                    new Meal(3L, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                    new Meal(4L, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                    new Meal(5L, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                    new Meal(6L, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                    new Meal(7L, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
        }

        @Override
        public List<Meal> findAll() {
            return repo;
        }

        @Override
        public void deleteMeal(Long id) {
            log.info("delete meal, id = {} ", id);
            repo.removeIf(meal -> meal.getId().equals(id));
        }

        @Override
        public Meal find(Long id) {
            return repo.stream()
                    .filter(meal -> meal.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public synchronized void update(Meal meal) {
            if (nonNull(meal.getId())) {
                repo.remove(meal);
                repo.add(meal);
            } else {
                Long id = repo.stream()
                        .map(Meal::getId)
                        .max(Long::compareTo)
                        .orElse(null);
                meal.setId(id + 1);
                repo.add(meal);
            }

        }
    }
}
