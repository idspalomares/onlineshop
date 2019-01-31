package com.app.rccl.shop.controller;

import com.app.rccl.shop.exception.AccessForbiddenException;
import com.app.rccl.shop.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    /**
     * Intercepts between home and login, checks if there's logged user or not
     * @param request
     * @return
     */
    @GetMapping("/")
    public String home(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER");

        if (user == null) {
            return "login";
        } else {
            return "index";
        }
    }

    /**
     * Intercept /api based APIs, basically you have to be logged before you can access tem
     * @param request
     */
    @RequestMapping("/api/**")
    @ResponseBody
    public void accessCheck(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER");

        if (user == null) {
            throw new AccessForbiddenException();
        }
    }

}
