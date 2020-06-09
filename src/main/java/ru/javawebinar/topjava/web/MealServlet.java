package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String MEALS = "/meals.jsp";
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private static final MealService mealService = new MealService.MealServiceImpl();


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");
        if (nonNull(action)) {
            if (action.equalsIgnoreCase("delete")) {
                String mealId = req.getParameter("mealId");
                if (nonNull(mealId)) {
                    mealService.deleteMeal(Long.parseLong(mealId));
                    forward = MEALS;
                    req.setAttribute("mealTos", mealService.findAll());
                }
            } else if (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("insert")) {
                forward = INSERT_OR_EDIT;
                String mealId = req.getParameter("mealId");
                if (nonNull(mealId)) {
                    MealTo mealTo = mealService.find(Long.parseLong(mealId));
                    if (nonNull(mealTo)) {
                        req.setAttribute("mealTo", mealTo);
                    }
                }
            }
        } else {
            forward = MEALS;
            req.setAttribute("mealTos", mealService.findAll());
        }
        getServletContext().getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String mealId = req.getParameter("mealId");
        Meal meal = new Meal(!isEmpty(mealId) ? Long.parseLong(mealId) : null,
                TimeUtil.parse(req.getParameter("datetime-local")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        mealService.update(meal);
        req.setAttribute("mealTos", mealService.findAll());
        getServletContext().getRequestDispatcher(MEALS).forward(req, resp);
    }

    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
