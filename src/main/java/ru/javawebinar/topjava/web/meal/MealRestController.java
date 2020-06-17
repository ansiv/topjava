package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private final Logger log = getLogger(getClass());
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal, Integer userId) {
        return service.create(meal, userId);
    }

    public void update(Meal meal, Integer userId) {
        service.update(meal,userId);
    }

    public void delete(int id, Integer userId) {
        service.delete(id, userId);
    }

    public Meal get(int id, Integer userId) {
        return service.get(id,userId);
    }

    public Collection<Meal> getAll(Integer userId) {
        return service.getAll(userId);
    }
}