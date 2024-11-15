package org.sopt.diary.repository;

import jakarta.persistence.*;
import org.sopt.diary.member.MemberEntity;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    public MemberEntity member;

    @Column(length = 10, nullable = false, updatable = true)
    public String title;

    @Column(length = 30, nullable = false)
    public String body;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Category category;

    public DiaryEntity() {

    }
    public DiaryEntity(MemberEntity member, String title, String body, Category category) {
        this.member = member;
        this.title = title;
        this.body = body;
        this.date = LocalDateTime.now();
        this.category = category;
    }

    public MemberEntity getMember() {
        return member;
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
