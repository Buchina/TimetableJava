package myexceptions;

public class BadAnswerException extends Exception {
    private String s = "В моем дипазоне нет такой цифры... Попробуйте ещё раз.";

    @Override
    public String getMessage() {
        return s;
    }
}
