package myexceptions;

public class BadNumOfPairException extends Exception {
    private String s = "Такого номера пары не может быть...";

    @Override
    public String getMessage() {
        return s;
    }
}
