package com.app.rccl.shop.controller;


import com.app.rccl.shop.exception.UserNotFoundException;
import com.app.rccl.shop.model.User;
import com.app.rccl.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final String USER = "USER";

    @Autowired
    private UserService userService;

    /**
     * Logging controller
     * @param request
     * @return
     * @throws ServletException
     */
    @PostMapping("/login")
    private String login(HttpServletRequest request) throws ServletException {
        String msg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.findUser(username, password);
            request.getSession().setAttribute(USER, user);
        } catch (UserNotFoundException e) {
            msg = "?msg=404";
            e.printStackTrace();
        }

        return "redirect:/" + msg;
    }

    /**
     *
     * @param request
     * @return
     * @throws ServletException
     */
    @GetMapping("/logout")
    private String logout(HttpServletRequest request) throws ServletException {
        request.getSession().removeAttribute(USER);
        return "redirect:/";
    }

}
