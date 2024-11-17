package tech.silva.linkup.backend.exception;

public class InvalidCredencialException extends RuntimeException {
    public InvalidCredencialException(String message) {
        super(message);
    }
}
