package com.marantle.controller;

import com.marantle.entity.Product;
import com.marantle.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marko on 19.6.2016.
 */
@Controller
public class RegisterController {
    @RequestMapping("register")
    public String Register(Model model){
        model.addAttribute("user", new Users());
        return "registerform";
    }
}
