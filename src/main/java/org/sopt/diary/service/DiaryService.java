package org.sopt.diary.service;

import org.sopt.diary.error.ExceedCharacterLimitException;
import org.sopt.diary.error.TooManyRequestsException;
import org.sopt.diary.repository.Category;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    //일기 작성
    public void createDiary(String title, String body, Category category) {
        List<DiaryEntity> recentDiaries = diaryRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        if (!recentDiaries.isEmpty()) {
            DiaryEntity recentDiary = recentDiaries.get(0);
            LocalDateTime recentTime = recentDiary.getDate();
            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(recentTime, now);
            if (duration.toMinutes() < 5) {
                long remainingTimeSeconds = 300 - duration.getSeconds();
                throw new TooManyRequestsException("일기는 5분에 한 번만 작성할 수 있습니다.", remainingTimeSeconds);
            }
        }
        if (title.length() > 30){
            throw new ExceedCharacterLimitException("제목은 30자를 초과할 수 없습니다.",30);
        }
        if (body.length() > 30){
            throw new ExceedCharacterLimitException("일기 글자수는 30자를 초과할 수 없습니다.",30);
        }
        diaryRepository.save(
                new DiaryEntity(title,body,category)
        );
    }

    //일기 목록 조회
    public List<Diary> getList(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        List<DiaryEntity> diaryEntityList = diaryRepository.findAll(pageable).getContent();
        List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(
                    new Diary(diaryEntity.getId(), diaryEntity.getTitle(), null, null, diaryEntity.getCategory())
            );
        }
        return diaryList;
    }

    //일기 상세 조회
    public Diary getDiaryDetails(long id) {
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElse(null);
        return new Diary(diaryEntity.getId(),  diaryEntity.getTitle(), diaryEntity.getBody(), diaryEntity.getDate(), diaryEntity.getCategory());
    }

    //일기 수정
    public void updateDiary(Long id, String body) {
        if (body.length() > 30){
            throw new ExceedCharacterLimitException("일기 글자수는 30자를 초과할 수 없습니다.",30);
        }
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElse(null);
        diaryEntity.setBody(body);
        diaryRepository.save(diaryEntity);
    }

    //일기 삭제
    public void deleteDiary(long id) {
        diaryRepository.deleteById(id);
    }

    //카테고리별 일기 조회
    public List<Diary> getDiariesByCategory(Category category) {
        List<DiaryEntity> diaryEntityList = diaryRepository.findByCategory(category);
        List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle(), null, null, diaryEntity.getCategory()));
        }
        return diaryList;
    }
}
