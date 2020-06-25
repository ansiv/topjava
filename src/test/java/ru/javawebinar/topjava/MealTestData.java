package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class MealTestData {
    public static final int M1_ID = 100010;

    public static final Meal M1 = new Meal(100010, LocalDateTime.of(2020, 5, 30, 10, 0), "Завтрак", 500);
    public static final Meal M2 = new Meal(100011, LocalDateTime.of(2020, 5, 30, 13, 0), "Обед", 1000);
    public static final Meal M3 = new Meal(100012, LocalDateTime.of(2020, 5, 30, 20, 0), "Ужин", 500);
    public static final Meal M4 = new Meal(100013, LocalDateTime.of(2020, 5, 31, 10, 0), "Завтрак", 1000);
    public static final Meal M5 = new Meal(100014, LocalDateTime.of(2020, 5, 31, 13, 0), "Обед", 500);
    public static final Meal M6 = new Meal(100015, LocalDateTime.of(2020, 5, 31, 20, 0), "Ужин", 410);
    public static final Meal A1 = new Meal(100016, LocalDateTime.of(2020, 6, 1, 14, 0), "Админ ланч", 510);
    public static final Meal A2 = new Meal(100017, LocalDateTime.of(2020, 6, 1, 21, 0), "Админ ужин", 1500);

    public static void eq(Meal m1, Meal m2) {
        assertEquals(m1.getId(), m2.getId());
        assertEquals(m1.getDateTime(), m2.getDateTime());
        assertEquals(m1.getCalories(), m2.getCalories());
        assertEquals(m1.getDescription(), m2.getDescription());
    }
}
