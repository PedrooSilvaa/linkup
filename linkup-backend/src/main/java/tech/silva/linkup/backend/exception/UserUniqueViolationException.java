package tech.silva.linkup.backend.exception;

public class UserUniqueViolationException extends RuntimeException {
    public UserUniqueViolationException(String message) {
        super(message);
    }
}
