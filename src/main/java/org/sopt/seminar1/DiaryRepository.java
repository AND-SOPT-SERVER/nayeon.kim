package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    void save(final Diary diary){
        final long id = numbering.addAndGet(1);

        storage.put(id, diary.getBody());
    }

    List<Diary> findAll() {
        final List<Diary> diaryList = new ArrayList<>();
        for (long index = 1; index <= numbering.longValue(); index++) {
            final String body = storage.get(index);
            diaryList.add(new Diary(index, body));
        }
        return diaryList;
    }

    void delete(final long id) {
        storage.remove(id);
    }
    void update(final long id,final Diary diary) {
        storage.put(id, diary.getBody());
    }
}
