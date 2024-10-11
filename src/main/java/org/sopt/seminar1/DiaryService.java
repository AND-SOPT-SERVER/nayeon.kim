package org.sopt.seminar1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    void writeDiary(String body){
        final Diary diary = new Diary(null, body);
        diaryRepository.save(diary);
    }
    List<Diary> getDiaryList(){
        return diaryRepository.findAll();
    }
    void deleteDiary(long id){
        diaryRepository.delete(id);
    }
    void updateDiary(long id, String body){
        diaryRepository.update(id ,body);
    }
    void restoreDiary(long id) { // 복구 기능 추가
        diaryRepository.restore(id);
    }
}
