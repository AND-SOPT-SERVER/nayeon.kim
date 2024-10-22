package org.sopt.diary.api;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DiaryResponse {
    private long id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;

    public DiaryResponse(long id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
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
    public String getDate() {
        return date;
    }
}
