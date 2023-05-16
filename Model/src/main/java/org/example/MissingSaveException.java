package org.example;

public class MissingSaveException extends Exception {
    public MissingSaveException(String errorMessage,Throwable cause) {

        super(errorMessage,cause);
    }

    public MissingSaveException(String errorMessage) {
        super(errorMessage);
    }

}
