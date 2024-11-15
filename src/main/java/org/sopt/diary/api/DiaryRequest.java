package org.sopt.diary.api;

import org.sopt.diary.repository.Category;

import java.util.Calendar;

public class DiaryRequest {
    private String title;
    private String body;
    private Category category;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Category getCategory() {
        return category;
    }
}
