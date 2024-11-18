package tech.silva.linkup.backend.exception;

public class LikeAlreadyMadeException extends RuntimeException {
    public LikeAlreadyMadeException(String message) {
        super(message);
    }
}
