package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 30)
    public String title;

    @Column(length = 30)
    public String body;

    @Column
    public LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    public Category category;

    public DiaryEntity() {

    }
    public DiaryEntity(String title, String body, Category category) {
        this.title = title;
        this.body = body;
        this.date = LocalDateTime.now();
        this.category = category;
    }

    public  String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody(){
        return body;
    }
    public void setBody(String body){
        this.body = body;
    }

    public long getId(){
        return id;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public Category getCategory(){
        return category;
    }

}
