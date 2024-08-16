package com.project.scm.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    private Boolean favourite;
    private String websiteLink;
    private String Linkedin;
    @Column(length = 1024)
    private String description;

    private String cloudinaryImagePublicId;
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SocialLink> socialLinks = new ArrayList<>();
    @ManyToOne
    @ToString.Exclude
    private User user;

}
