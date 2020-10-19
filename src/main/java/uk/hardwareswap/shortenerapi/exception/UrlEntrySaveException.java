package uk.hardwareswap.shortenerapi.exception;

public class UrlEntrySaveException extends RuntimeException {

    public UrlEntrySaveException() { super(); }
    public UrlEntrySaveException(Throwable cause) { super(cause); }
    public UrlEntrySaveException(String message) { super(message); }
    public UrlEntrySaveException(String message, Throwable cause) { super(message, cause); }
}
