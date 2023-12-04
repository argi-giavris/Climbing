package com.example.climbing.servlets.user;

import com.example.climbing.models.User;
import com.example.climbing.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class UserRegisterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        User newUserTest = objectMapper.readValue(request.getReader(), User.class);

        UserRepository repo = new UserRepository();
        if(!repo.userExistsByEmail(newUserTest.getEmail())) {
            User newUser = new UserRepository().CreateUser(newUserTest);

                if(newUser.getId() != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("currentUser", newUser);

                    User user = (User) session.getAttribute("currentUser");

                    System.out.println(user.getEmail());
                    response.sendRedirect("/");

                    return;
                }
        }

        response.sendRedirect("/register");


    }

    boolean isUserAuthenticatedWithRole(HttpSession session, String role) {
        if (session == null) return false;

        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser.getRole().equals(role);
    }
}
