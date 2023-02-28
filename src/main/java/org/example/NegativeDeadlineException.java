package org.example;

public class NegativeDeadlineException extends Exception {

    private int value;

    public NegativeDeadlineException(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String msg = " EXCEPTION: Значение времени " + value + " отрицательное";
        return msg;
    }
}
