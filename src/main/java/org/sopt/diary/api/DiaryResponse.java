package org.sopt.diary.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.diary.repository.Category;

import java.time.LocalDateTime;

public class DiaryResponse {
    private long id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime date;
    private Category category;

    public DiaryResponse(long id, String title, String body, LocalDateTime date, Category category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.category = category;
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
    public void setCategory(Category category) {
        this.category = category;
    }
}
