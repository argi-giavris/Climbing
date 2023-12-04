package com.example.climbing.Utils;

import com.example.climbing.models.Role;
import com.example.climbing.models.User;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Utils {

    public boolean isUserAuthenticatedWithRole(HttpSession session, Role role) {
        if (session == null) return false;

        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser.getRole().equals(role);
    }

    public boolean userIsSetter(User user){
        return Objects.equals(user.getRole(), "setter");
    }

}
