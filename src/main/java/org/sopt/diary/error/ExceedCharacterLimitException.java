package org.sopt.diary.error;

public class ExceedCharacterLimitException extends RuntimeException {
    private final int maxCharacters;
    public ExceedCharacterLimitException(String message,int maxCharacters) {
        super(message);
        this.maxCharacters = maxCharacters;
    }
    public int getMaxCharacters() {
        return maxCharacters;
    }
}
