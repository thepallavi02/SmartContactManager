package com.project.scm.Controller;

import com.project.scm.Entities.User;
import com.project.scm.Forms.UserRegisterForm;
import com.project.scm.Helpers.MessageType;
import com.project.scm.Helpers.Messages;
import com.project.scm.Services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("scm")
public class PageController {
    @Autowired
   private UserService userService;

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/scm/home";
    }
    @GetMapping("home")
    public String getHome(Model model){
        System.out.println("This is home");
        model.addAttribute("name","Pallavi");
        return "home";
    }
    @GetMapping("about")
    public String getAbout(Model model){
        model.addAttribute("name","Priya");
        model.addAttribute("place","Delhi");
        System.out.println("This is about page");
        return "about";
    }
    @GetMapping("services")
    public String getServices(Model model){
        System.out.println("This is services page");
        return "services";
    }
    @GetMapping("contact")
    public String getContacts(Model model){
        System.out.println("This is contact page");
        return "contact";
    }
    @GetMapping("login")
    public String getLogin(Model model){
        System.out.println("This is login page");
        return "login";
    }
    @GetMapping("register")
    public String getRegister(Model model){
        System.out.println("This is register page");
        UserRegisterForm userRegisterForm = new UserRegisterForm();
        model.addAttribute("userRegisterForm",userRegisterForm);
        return "register";
    }
    @PostMapping("do-register")
    public String processRegister(@Valid @ModelAttribute UserRegisterForm userForm, HttpSession session, BindingResult bindingResult){
        System.out.println("Processing page");
        System.out.println(userForm);

        //fetch form data
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhone());
        user.setAbout(user.getAbout());
        user.setProfilePic("https://t4.ftcdn.net/jpg/08/57/42/71/240_F_857427129_SfrSAInhlOB2O67xICid53pGkoNuZCfG.jpg");
        User savedUser = userService.saveUser(user);
        System.out.println("User saved");
        Messages message = Messages.builder().content("Registration Successful").type(MessageType.Green).build();
        session.setAttribute("messageType",message.getType());
        session.setAttribute("message",message);

        //validate form data
        if(bindingResult.hasErrors()){
            return "redirect:/scm/register";
        }
        //save to database
        //message = registration successful
        //redirection to login page
        return "redirect:/scm/login";
    }
}
