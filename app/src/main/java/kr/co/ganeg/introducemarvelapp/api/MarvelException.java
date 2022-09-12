package kr.co.ganeg.introducemarbleapp.api;

public class MarvelException extends Exception {

    private final int mCode;
    MarvelException(int code, String message) {
        super(message);
        mCode = code;
    }
}
