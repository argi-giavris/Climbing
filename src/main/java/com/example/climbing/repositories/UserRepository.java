package com.example.climbing.repositories;

import com.example.climbing.configuration.HikariDatabaseConnection;
import com.example.climbing.models.Role;
import com.example.climbing.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserRepository {
    public User CreateUser(User user) {
        String query = "INSERT INTO users (first_name, last_name, password, email, role) VALUES(?, ?, ?, ?, ?)";

        try {
            Connection connection = HikariDatabaseConnection.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setString(4, user.getEmail());
            statement.setString(5, String.valueOf(user. getRole()));

            int rowsAffected = statement.executeUpdate();
            System.out.println("Insert executed");
            if (rowsAffected == 0) {
                throw new SQLException("Creating user failed");
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Create user failed");
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean userExistsByEmail(String email) {
        String query = "SELECT id FROM users where email = ?";

        try (Connection dbConnection = HikariDatabaseConnection.getDataSource().getConnection();
             PreparedStatement statement = dbConnection.prepareStatement(query)
        ) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new RuntimeException("User already exists");
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public User loginUser(User user){
        String query = "SELECT * FROM users WHERE email = ?";

        User loggedUser = null;

        try (Connection dbConnection = HikariDatabaseConnection.getDataSource().getConnection();
             PreparedStatement statement = dbConnection.prepareStatement(query)
        ){
            statement.setString(1,user.getEmail());

            ResultSet resultSet = statement.executeQuery();
             if(resultSet.next() && verifyPassword(user.getPassword(),resultSet.getString("password"))){
                 loggedUser = new User();
                 loggedUser.setId(resultSet.getInt("id"));
                 loggedUser.setFirstName(resultSet.getString("first_name"));
                 loggedUser.setLastName(resultSet.getString("last_name"));
                 loggedUser.setEmail(resultSet.getString("email"));
                 loggedUser.setRole(Role.valueOf(resultSet.getString("role")));

             }else {
                 throw  new RuntimeException("Wrong email or password");
             }


        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return loggedUser;
    }


    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
