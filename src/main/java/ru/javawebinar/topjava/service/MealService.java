package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repo.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

public class MealService {
    private MealRepository mealRepository;

    public MealService() {
        mealRepository = new MealRepository();
    }

    private List<MealTo> findAll() {
        return MealsUtil.filteredByStreams(mealRepository.findAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }
}
