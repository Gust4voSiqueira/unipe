package br.com.unipe.exceptions;

public class UnauthorizedClassroomDelete extends RuntimeException {
    public UnauthorizedClassroomDelete(String message) {
        super(message);
    }
}