package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repo.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public interface MealService {
    List<MealTo> findAll();

    void deleteMeal(Long id);

    MealTo find(Long id);

    void update(Meal meal);

    class MealServiceImpl implements MealService {
        private static final Logger log = getLogger(MealServiceImpl.class);

        private MealRepository mealRepository;

        public MealServiceImpl() {
            mealRepository = new MealRepository.MealRepositoryImpl();
        }

        public List<MealTo> findAll() {
            log.info("findAll mealTo");
            return MealsUtil.filteredByStreams(mealRepository.findAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        }

        @Override
        public void deleteMeal(Long id) {
            mealRepository.deleteMeal(id);
        }

        @Override
        public MealTo find(Long id) {
           return findAll().stream()
                    .filter(mealTo -> mealTo.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public void update(Meal meal) {
            mealRepository.update(meal);
        }
    }

}
