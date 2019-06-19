package com.chai.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("posts")
    public String getPost(Principal p, Model m) {
        System.out.println(p.getName());
        AppUser me = appUserRepository.findByUsername(p.getName());

        m.addAttribute("loggedInUser", me);
        return "posts";
    }

    @GetMapping("/post/add")
    public String getPostCreator() {
        return "createPost";
    }

    @PostMapping("/posts")
    public RedirectView addDinosaur(String body, Date createdAt) {
        Post newPost = new Post();
        newPost.body = body;
        newPost.createdAt = createdAt;


        AppUser me = appUserRepository.findByUsername(newPost.getBody());
        newPost.creator = me;
        postRepository.save(newPost);
        return new RedirectView("/");
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model m, Principal p) {
        Post post = postRepository.findById(id).get();
        // check if that dinosaur belongs to the currently logged in user
        if (post.getCreator().username.equals(p.getName())) {
            // if so, do the nice things
            m.addAttribute("post", post);
            return "dinosaur";
        } else {
            // otherwise, tell them no
            throw new PostDoesNotBelongToYouException("That post does not belong to you.");
        }


    }
}

// came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
@ResponseStatus(value = HttpStatus.FORBIDDEN)
class PostDoesNotBelongToYouException extends RuntimeException {
    public PostDoesNotBelongToYouException(String s) {
        super(s);
    }
}
