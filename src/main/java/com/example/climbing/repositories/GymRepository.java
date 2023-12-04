package com.example.climbing.repositories;

import com.example.climbing.configuration.DatabaseConnection;
import com.example.climbing.models.Gym;

import java.sql.*;

public class GymRepository {

    public Gym createGym(Gym gym){

        String query = "INSERT INTO gyms (name, location, owner_email) VALUES (?, ?, ?)";

        try(Connection dbConnection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, gym.getName());
            statement.setString(2, gym.getLocation());
            statement.setString(3, gym.getOwnerEmail());


            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    gym.setGymId(keys.getInt(1));

                } else {
                    throw new SQLException("Create gym failed");
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return gym;
    }

    public boolean gymExistsById(Integer gymId){
        String query = "SELECT id FROM gyms WHERE id = ?";

        try (Connection dbConnection = DatabaseConnection.initializeDatabase();
             PreparedStatement statement = dbConnection.prepareStatement(query)
        ){
            statement.setInt(1,gymId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return true;
            }
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return false;
    }
}
