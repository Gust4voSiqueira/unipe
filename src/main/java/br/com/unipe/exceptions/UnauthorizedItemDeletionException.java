package br.com.unipe.exceptions;

public class UnauthorizedItemDeletionException extends RuntimeException {
    public UnauthorizedItemDeletionException(String message) {
        super(message);
    }
}
