package com.example.climbing.servlets.user;

import com.example.climbing.models.User;
import com.example.climbing.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected  void  doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        User newUserTest = objectMapper.readValue(request.getReader(), User.class);


        UserRepository userRepository = new UserRepository();
        User authentecatedUser = userRepository.loginUser(new User(newUserTest.getEmail(), newUserTest.getPassword()));

        if(authentecatedUser != null){

            HttpSession session = request.getSession();
            session.setAttribute("currentUser", authentecatedUser);
        }

    }
}
