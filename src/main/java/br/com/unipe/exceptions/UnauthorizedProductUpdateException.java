package br.com.unipe.exceptions;

public class UnauthorizedProductUpdateException extends RuntimeException {
    public UnauthorizedProductUpdateException(String message) {
        super(message);
    }
}
