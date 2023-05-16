package org.example;

public class GameBuildFailException extends Exception {

    public GameBuildFailException(String errorMessage,Throwable cause) {
        super(errorMessage,cause);
    }
}
