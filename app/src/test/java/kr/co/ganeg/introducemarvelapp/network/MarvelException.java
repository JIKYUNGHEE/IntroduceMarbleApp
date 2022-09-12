package kr.co.ganeg.introducemarbleapp.network;

public class MarvelException extends Exception {

    private final int mCode;
    MarvelException(int code, String message) {
        super(message);
        mCode = code;
    }
}
