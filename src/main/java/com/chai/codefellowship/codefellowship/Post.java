package com.chai.codefellowship.codefellowship;

import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

   public String body;
    LocalDateTime createdAt;

    @ManyToOne
    AppUser creator;



    public Post(){}
    public Post(String body, AppUser creator){
        this.body = body;
        this.createdAt = LocalDateTime.now();
        this.creator = creator;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public void setCreator(AppUser creator) {
        this.creator = creator;
    }
}