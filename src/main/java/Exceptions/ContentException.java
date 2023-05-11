package Exceptions;

public class ContentException extends Exception{
    public ContentException(String message){
        super(message);
    }

    public ContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
