package com.example.climbing.servlets.routes;

import com.example.climbing.models.Route;
import com.example.climbing.repositories.RouteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/routes-graded")
public class ViewRoutesByGradeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String grade = req.getParameter("grade");
        RouteRepository routeRepository = new RouteRepository();
        List<Route> routes;

        try {
            routes = routeRepository.getRoutesByGrade(grade);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(!routes.isEmpty()){
            ObjectMapper objectMapper = new ObjectMapper();
            String routesJson = objectMapper.writeValueAsString(routes);

            resp.setContentType("application/json");
            resp.getWriter().write(routesJson);
        } else {
            throw new RuntimeException("Routes graded as " + grade + " not exists");
        }


    }
}
