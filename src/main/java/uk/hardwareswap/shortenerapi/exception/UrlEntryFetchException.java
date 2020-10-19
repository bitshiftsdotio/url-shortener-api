package uk.hardwareswap.shortenerapi.exception;

public class UrlEntryFetchException extends RuntimeException {

    public UrlEntryFetchException() { super(); }
    public UrlEntryFetchException(Throwable cause) { super(cause); }
    public UrlEntryFetchException(String message) { super(message); }
    public UrlEntryFetchException(String message, Throwable cause) { super(message, cause); }
}
