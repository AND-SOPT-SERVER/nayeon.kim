package org.sopt.diary.error;

public class TooManyRequestsException extends RuntimeException {
    private final long remainingTimeSenconds;
    public TooManyRequestsException(String message,long remainingTimeSenconds) {
        super(message);
        this.remainingTimeSenconds = remainingTimeSenconds;
    }
    public long getRemainingTimeSenconds() {
        return remainingTimeSenconds;
    }
}
