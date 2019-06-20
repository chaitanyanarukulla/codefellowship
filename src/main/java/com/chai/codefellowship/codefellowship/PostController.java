package com.chai.codefellowship.codefellowship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    PostRepository postsRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/createpost")
    public String getcreatePost() {
        return "createPost";
    }

    @PostMapping("/posts")
    public RedirectView addPost(String body, Principal p) {
        AppUser me = appUserRepository.findByUsername(p.getName());
        Post newPost = new Post(body,me);
        postsRepository.save(newPost);
        return new RedirectView("/profile");
    }

}

// came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
@ResponseStatus(value = HttpStatus.FORBIDDEN)
class PostIsNotYoursException extends RuntimeException {
    public PostIsNotYoursException(String s) {
        super(s);
    }

}
