package com.example.climbing.servlets.gyms;

import com.example.climbing.utils.Utils;
import com.example.climbing.models.Gym;
import com.example.climbing.models.Role;
import com.example.climbing.models.User;
import com.example.climbing.repositories.GymRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create-new-gym")
public class CreateNewGymServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Utils utils = new Utils();
        HttpSession session = req.getSession();

        User loggedUser = (User) session.getAttribute("currentUser");

        if (utils.isUserAuthenticatedWithRole(session, Role.GYM_OWNER)){
            ObjectMapper objectMapper = new ObjectMapper();
            Gym newGym = objectMapper.readValue(req.getReader(), Gym.class);

            System.out.println(loggedUser.getEmail());
            newGym.setOwnerEmail(loggedUser.getEmail());

            GymRepository gymRepository = new GymRepository();
            gymRepository.createGym(newGym);
        } else {
            throw  new RuntimeException("User must have a gym owner role");
        }
    }
}
