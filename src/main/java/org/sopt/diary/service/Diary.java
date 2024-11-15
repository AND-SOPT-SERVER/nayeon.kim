package org.sopt.diary.service;

import org.sopt.diary.repository.Category;

import java.time.LocalDateTime;

public class Diary {
    private final long id;
    private final String title;
    private String body;
    private final LocalDateTime date;
    private Category category;
    private String nickname;

    public Diary(long id, String title, String body, LocalDateTime date, Category category, String nickname) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.category = category;
        this.nickname = nickname;
    }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public Category getCategory() {
        return category;
    }
    public String getNickname() { return nickname; }
}
