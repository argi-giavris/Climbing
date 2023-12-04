package com.example.climbing.servlets.routes;

import com.example.climbing.models.GymRoute;
import com.example.climbing.repositories.GymRouteRepository;
import com.example.climbing.repositories.RouteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/assign-route-to-gym")
public class AssignRouteToGymServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        GymRoute gymRoutes = objectMapper.readValue(req.getReader(), GymRoute.class);

        GymRouteRepository gymRouteRepository = new GymRouteRepository();
        try {
            gymRouteRepository.assignRouteToGym(gymRoutes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
