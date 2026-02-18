package com.example.demo.models;

import java.time.LocalDateTime;

import com.example.StaffMemberProfile;
import com.example.ProfProfile;
import com.example.TAProfile;

import jakarta.persistence.*;

@Entity
@Table(name="staff")
public class StaffRating {
    // The table used in my database with a function displayTitle() that makes it easier to work with in controller and can make polymorphic call in controller
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;
    private int clarity;
    private int niceness;
    private int knowledgeableScore;
    private String comment;
    private LocalDateTime createdAt;

    public StaffRating(String name, String email, RoleType role, int clarity, int niceness, int knowledgeableScore, String comment) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.clarity = clarity;
        this.niceness = niceness;
        this.knowledgeableScore = knowledgeableScore;
        this.comment = comment;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public StaffRating(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClarity() {
        return clarity;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public int getNiceness() {
        return niceness;
    }

    public void setNiceness(int niceness) {
        this.niceness = niceness;
    }

    public int getKnowledgeableScore() {
        return knowledgeableScore;
    }

    public void setKnowledgeableScore(int knowledgeableScore) {
        this.knowledgeableScore = knowledgeableScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Transient // Going forward probably would have used this for calculating average
    public String getDisplayTitle() {
        StaffMemberProfile p = 
        (this.role == RoleType.TA) ? new TAProfile():
        (this.role == RoleType.PROF) ? new ProfProfile() :
        () -> this.role.name();
        return p.displayTitle();
    }
}
