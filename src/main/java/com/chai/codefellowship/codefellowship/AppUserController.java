package com.chai.codefellowship.codefellowship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    // bulding up user info and saving into db

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String fname, String lname, Date dob, String bio) {
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password),fname,lname,dob,bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }

  //Login page
    @GetMapping("/login")
    public String getLoginPage() { return "login"; }

  //users page
    @GetMapping("/users")
    public String getAllUsers(Model m) {
        Iterable<AppUser> user = appUserRepository.findAll();
        m.addAttribute("user", user);
        return "users.html";
    }
  //sing up page
    @GetMapping("/signup")
    public String getAddSignUpForm() {
        return "signup";
    }

  //Profile page
    @GetMapping("/profile")
    public String getMyProfileInfo(Principal p, Model m) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value="details/{id}",method = RequestMethod.GET)
    public String getOneUserDetail(@PathVariable Long id, Model m){
        AppUser user = appUserRepository.findById(id).get();
        m.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logout";
    }

    @GetMapping("/feed")
    public String getFeed(Principal p, Model m){
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("user", user);
        return "feed";

    }

    @PostMapping("/follow/{id}")
    public RedirectView getFollow(@PathVariable Long id,Principal p){
        AppUser loginuser = appUserRepository.findByUsername(p.getName());
        AppUser newfollower = appUserRepository.findById(id).get();
        loginuser.followers.add(newfollower);
        appUserRepository.save(loginuser);
        return new RedirectView("/profile");
    }

}
