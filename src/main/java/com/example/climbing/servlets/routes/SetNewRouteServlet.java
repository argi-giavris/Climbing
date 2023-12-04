package com.example.climbing.servlets.routes;

import com.example.climbing.Utils.Utils;
import com.example.climbing.models.Route;
import com.example.climbing.models.User;
import com.example.climbing.repositories.RouteRepository;
import com.example.climbing.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/new-route")
public class SetNewRouteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utils utils = new Utils();
        HttpSession session = req.getSession();

        User loggedUser = (User) session.getAttribute("currentUser");

        if(utils.userIsSetter(loggedUser)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Route newRoute = objectMapper.readValue(req.getReader(), Route.class);

            RouteRepository routeRepository = new RouteRepository();
            routeRepository.createRoute(newRoute);
        } else {
            throw  new RuntimeException("User must have a setter role");
        }
    }
}
