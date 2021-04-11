package com.vedang.smrt.exception;

public class SmrtException extends Exception {

    private static final long serialVersionUID = 1L;

    public SmrtException(String errorMessage) {
        super(errorMessage);
    }
}
