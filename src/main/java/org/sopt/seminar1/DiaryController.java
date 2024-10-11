package org.sopt.seminar1;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        diaryService.writeDiary(body);
    }

    final void delete(final String id) {
        long longId = validateAndConvertId(id);
        diaryService.deleteDiary(longId);
    }

    final void patch(final String id, final String body) {
        long longId = validateAndConvertId(id);
        diaryService.updateDiary(longId, body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }

    private long validateAndConvertId(final String id) {
        if (!id.matches("^[1-9][0-9]*$")) {
            throw new Main.UI.InvalidInputException();
        }

        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효한 long 형식의 ID여야 합니다.");
        }
    }
}
