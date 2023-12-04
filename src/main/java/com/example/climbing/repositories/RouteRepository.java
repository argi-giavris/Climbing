package com.example.climbing.repositories;


import com.example.climbing.models.Route;
import com.example.climbing.models.GymRoute;
import com.example.climbing.configuration.DatabaseConnection;
import com.example.climbing.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteRepository {

    public Route createRoute(Route route){

        String query = "INSERT INTO routes (grade, setter_email) VALUES (?, ?)";

        try(Connection dbConnection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, route.getGrade().getValue());
            statement.setString(2, route.getSetterEmail());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    route.setId(keys.getInt(1));

                } else {
                    throw new SQLException("Create route failed");
                }
            }


        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return route;
    }


    public boolean routeExistsById(Integer routeId){
        String query = "SELECT id FROM routes WHERE id = ?";

        try (Connection dbConnection = DatabaseConnection.initializeDatabase();
             PreparedStatement statement = dbConnection.prepareStatement(query)
        ){
            statement.setInt(1,routeId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return true;
            }
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return false;
    }

    public List<Route> getRoutesByUserEmail(User loggedUser) throws SQLException, ClassNotFoundException {
        String query = "Select * from routes where setter_email = ?";
        List<Route> userRoutes = new ArrayList<>();

        try(Connection dbConnection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = dbConnection.prepareStatement(query))
        {
            statement.setString(1, loggedUser.getEmail());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Route route = new Route(resultSet);
                    userRoutes.add(route);
                }
            }
        }
        return userRoutes;
    }

    public  List<Route> getRoutesByGrade(String grade) throws SQLException, ClassNotFoundException {
        List<Route> routes = new ArrayList<>();
        String query = "Select * from routes where grade = ?";

        try(Connection dbConnection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = dbConnection.prepareStatement(query))
        {
            statement.setString(1, grade);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Route route = new Route(resultSet);
                    routes.add(route);
                }
            }
        }
        return routes;
    }
}
