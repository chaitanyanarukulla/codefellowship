package com.chai.codefellowship.codefellowship;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public long getId() {
        return id;
    }

    public AppUser getCreator() {
        return creator;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @ManyToOne
    AppUser creator;
    String body;
    Date createdAt;


}