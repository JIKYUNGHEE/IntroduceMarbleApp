package kr.co.ganeg.introducemarvelapp.network;

public class MarvelException extends Exception {

    private final int mCode;
    MarvelException(int code, String message) {
        super(message);
        mCode = code;
    }
}
