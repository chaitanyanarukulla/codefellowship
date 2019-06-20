package com.chai.codefellowship.codefellowship;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class codefellowshipController {
    // Home page
    @GetMapping("/")
    public RedirectView goLogin(){
        return new RedirectView("/login");
    }
}