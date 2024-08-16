package com.project.scm.Controller;

import com.project.scm.Helpers.Helper;
import com.project.scm.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //user dashboard
    @GetMapping ("dashboard")
    public String fetchDashboard(){
        return "dashboard";
    }

    @PostMapping("dashboard")
    public String getDashboard(){
        return "dashboard";
    }

    // user profile page
    @GetMapping ("profile")
    public String getProfile(Authentication authentication){
        String userName= Helper.getEmailOfLoggedInUser(authentication);
        System.out.println(userName);
        logger.info("User logged in",userName);
        userService.getUserByEmail(userName);
        return "profile";
    }
    //user contact view
    //user edit contact
    //user delete contact
}
