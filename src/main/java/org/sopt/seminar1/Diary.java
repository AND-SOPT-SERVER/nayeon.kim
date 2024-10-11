package org.sopt.seminar1;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Diary {
    private Long id;
    private final String body;
    private boolean isDeleted = false;
    private LocalDateTime lastEditedDateTime;

    public Diary(Long id, String body) {
        this.id = id;
        this.body = body;
        this.lastEditedDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public String getBody() {
        return body;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public LocalDateTime getLastEditedDateTime() {
        return lastEditedDateTime;
    }
    public void setLastEditedDateTime(LocalDateTime lastEditedDateTime) {
        this.lastEditedDateTime = lastEditedDateTime;
    }
}
