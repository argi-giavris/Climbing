package com.example.climbing.repositories;

import com.example.climbing.configuration.HikariDatabaseConnection;
import com.example.climbing.models.GymRoute;

import java.sql.*;

public class GymRouteRepository {

    public void assignRouteToGym(GymRoute gymRoute) throws SQLException {
        String query = "INSERT INTO gym_routes (gym_id, route_id) VALUES (?, ?)";

        RouteRepository routeRepository = new RouteRepository();
        GymRepository gymRepository = new GymRepository();

        if (routeRepository.routeExistsById(gymRoute.getRouteId()) && gymRepository.gymExistsById(gymRoute.getGymId())){
            try(Connection dbConnection = HikariDatabaseConnection.getDataSource().getConnection();
                PreparedStatement statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
            {
                statement.setInt(1, gymRoute.getGymId());
                statement.setInt(2, gymRoute.getRouteId());

                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Route insertion failed");
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new SQLException("Route or gym id doesnt exists");
        }

    }
}
