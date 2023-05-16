package org.example;

public class CantSaveException extends Exception {
    public CantSaveException(String errorMessage) {
        super(errorMessage);
    }
}
