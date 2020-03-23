package myexceptions;

public class BadMenuAnswerException extends Exception {
    private String s = "Не могу понять ДА это или НЕТ...";

    @Override
    public String getMessage() {
        return s;
    }
}
