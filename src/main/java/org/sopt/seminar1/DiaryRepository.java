package org.sopt.seminar1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private int updateCount = 0;

    void save(Diary diary) {
        final long id = numbering.addAndGet(1);
        diary.setDeleted(false);
        diary = new Diary(id, diary.getBody());
        storage.put(id, diary);
    }

    List<Diary> findAll() {
        final List<Diary> diaryList = new ArrayList<>();
        for (long index = 1; index <= numbering.longValue(); index++) {
            final Diary diary = storage.get(index);
            if (diary != null && !diary.isDeleted()) {
                diaryList.add(diary);
            }
        }
        return diaryList;
    }

    void delete(final long id) {
        Diary diary = storage.get(id);
        if (diary != null) {
            diary.setDeleted(true);
        }
    }

    void update(final long id, final String newBody) {
        Diary diary = storage.get(id);
        if (diary != null && !diary.isDeleted()) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime lastEditedDateTime = diary.getLastEditedDateTime();

            if (!(lastEditedDateTime.toLocalDate().equals(now.toLocalDate()))) {
                updateCount = 0;
                diary.setLastEditedDateTime(now);
            }

            if (updateCount <2){
                updateCount++;
                diary.setDeleted(false);
                storage.replace(id, new Diary(id, newBody));
            } else {
                throw new Main.UI.InvalidInputException();
            }
        }
    }

    void restore(final long id) {
        Diary diary = storage.get(id);
        if (diary != null && diary.isDeleted()) {
            diary.setDeleted(false);
        }
    }
}

