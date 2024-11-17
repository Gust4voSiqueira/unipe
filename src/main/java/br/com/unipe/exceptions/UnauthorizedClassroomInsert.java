package br.com.unipe.exceptions;

public class UnauthorizedClassroomInsert extends RuntimeException {
    public UnauthorizedClassroomInsert(String message) {
        super(message);
    }
}