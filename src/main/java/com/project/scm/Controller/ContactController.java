package com.project.scm.Controller;

import com.project.scm.Entities.Contact;
import com.project.scm.Entities.User;
import com.project.scm.Forms.ContactForm;
import com.project.scm.Forms.ContactSearchForm;
import com.project.scm.Helpers.AppConstants;
import com.project.scm.Helpers.Helper;
import com.project.scm.Helpers.MessageType;
import com.project.scm.Helpers.Messages;
import com.project.scm.Services.ContactService;
import com.project.scm.Services.ImageService;
import com.project.scm.Services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.ToString;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("user/contacts")
@ToString
public class ContactController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ImageService imageService;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);
    @GetMapping("add-contact")
    public String addContacts(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "add_contact";
    }

    @PostMapping("process-form")
    public String saveContacts(@Valid @ModelAttribute ContactForm contactForm, Authentication authentication, BindingResult bindingResult, HttpSession session){
        System.out.println(contactForm);

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Messages.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.Red)
                    .build());
            return "add_contact";
        }
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(userName);
        System.out.println("Contact image name found:"+ contactForm.getContactImage().getOriginalFilename());
        logger.info("file information {}:",contactForm.getContactImage().getOriginalFilename());

        //Process the contact info -> 1.add enctype=multipart to your html

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setUser(user);
        contact.setLinkedin(contactForm.getLinkedin());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagePublicId(filename);

        }
        contactService.save(contact);
        System.out.println(contactForm);
        System.out.println(contact);
        session.setAttribute("message",
                Messages.builder()
                        .content("You have successfully added a new contact")
                        .type(MessageType.Green)
                        .build());
        return "redirect:add-contact";

    }

    @GetMapping("all")
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // load all the user contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "contacts";
    }

    @GetMapping("search")
    public String searchHandler(

            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

        logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());
        System.out.println(contactSearchForm.getField());
        System.out.println(contactSearchForm.getValue());

        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phoneNumber")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                    direction, user);
        }

        logger.info("pageContact {}", pageContact);
        System.out.println("pageContact {}" + pageContact);

        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "search";
    }

}
