package ru.eventify.eventify_backend.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String event){
        super("Event not found "+ event);
    }

}
