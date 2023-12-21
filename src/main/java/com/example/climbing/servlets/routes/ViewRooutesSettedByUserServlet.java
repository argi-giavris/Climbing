package com.example.climbing.servlets.routes;

import com.example.climbing.utils.Utils;
import com.example.climbing.models.Role;
import com.example.climbing.models.Route;
import com.example.climbing.models.User;
import com.example.climbing.repositories.RouteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/user-routes")
public class ViewRooutesSettedByUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Utils util = new Utils();
        HttpSession session = req.getSession();
        User loggedUser = (User) session.getAttribute("currentUser");
        List<Route> userRoutes;

        RouteRepository routeRepository = new RouteRepository();
        if (util.isUserAuthenticatedWithRole(session, Role.SETTER)) {
            try {
                userRoutes = routeRepository.getRoutesByUserEmail(loggedUser);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String routesJson = objectMapper.writeValueAsString(userRoutes);

            resp.setContentType("application/json");
            resp.getWriter().write(routesJson);
        } else {
            throw  new RuntimeException("User must have a setter role");
        }
    }

}

