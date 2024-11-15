package org.sopt.diary.api;

import org.sopt.diary.member.MemberRequest;
import org.sopt.diary.member.MemberService;
import org.sopt.diary.repository.Category;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;
    private final MemberService memberService;

    public DiaryController(DiaryService diaryService, MemberService memberService) {
        this.diaryService = diaryService;
        this.memberService = memberService;
    }

    //일기 작성
    @PostMapping("/diaries")
    void post(@RequestHeader("memberId") Long memberId,@RequestBody DiaryRequest diaryRequest) {
        diaryService.createDiary(memberId, diaryRequest.getTitle(), diaryRequest.getBody(), diaryRequest.getCategory());
    }

    //일기 목록 조회
    /*
    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> get(){
        List<Diary> diaryList = diaryService.getList();
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(),diary.getTitle(),null,null, diary.getCategory()));
        }
        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }
     */

    //일기 상세 조회
    @GetMapping("/diaries/{id}")
    ResponseEntity<DiaryResponse> getDetail(@PathVariable("id") Long id) {
        Diary diary = diaryService.getDiaryDetails(id);
        return ResponseEntity.ok(new DiaryResponse(diary.getId(), diary.getTitle(), diary.getBody(), diary.getDate(), diary.getCategory()));
    }

    //일기 수정
    @PatchMapping("/diaries/{id}")
    void update(@PathVariable("id") Long id, @RequestBody String body) {
        diaryService.updateDiary(id, body);
    }

    //일기 삭제
    @DeleteMapping("/diaries/{id}")
    void delete(@PathVariable("id") Long id) {
        diaryService.deleteDiary(id);
    }

    //카테고리별 일기 조회와 상세목록 조회
    @GetMapping("/diaries")
    public ResponseEntity<DiaryListResponse> get(
            @RequestParam(value = "category", required = false) Category category) {
        List<Diary> diaryList;
        if (category != null) {
            diaryList = diaryService.getDiariesByCategory(category);
        } else {
            diaryList = diaryService.getList();
        }
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            diaryResponseList.add(new DiaryResponse(diary.getId(),diary.getTitle(),null, null,diary.getCategory()));
        }
        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    //회원가입
    @PostMapping("/members/signup")
    public ResponseEntity<String> signup(@RequestBody MemberRequest memberRequest) {
        memberService.signupMember(memberRequest.getUsername(), memberRequest.getPassword(), memberRequest.getNickname());
        return ResponseEntity.ok("회원가입 성공");
    }
    @PostMapping("/members/signin")
    public ResponseEntity<String> signin(@RequestBody MemberRequest memberRequest) {
        memberService.loginMember(memberRequest.getUsername(), memberRequest.getPassword());
        return ResponseEntity.ok("로그인 성공");
    }
}
