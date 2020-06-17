package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.MealService;

@Controller
public class MealRestController {

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }
}