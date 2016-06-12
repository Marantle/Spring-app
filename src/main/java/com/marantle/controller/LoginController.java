package com.marantle.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Marko on 10.6.2016.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response) {
        System.out.println("jou1" + request.toString());
        System.out.println("jou2" + request.getHeaderNames());
        System.out.println("jou3" + request.getMethod());
        System.out.println("jou4" + response.toString());
        return "login";
    }
}
