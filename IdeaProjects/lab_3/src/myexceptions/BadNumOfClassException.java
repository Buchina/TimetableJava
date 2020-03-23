package myexceptions;

public class BadNumOfClassException extends Exception {
    private String s = "Такой аудитории не существует...";

    @Override
    public String getMessage() {
        return s;
    }
}
