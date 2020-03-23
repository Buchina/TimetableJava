package myexceptions;

public class BadWeekdayException extends Exception {
    private String s = "Не знаю такой день недели...";

    @Override
    public String getMessage() {
        return s;
    }
}
