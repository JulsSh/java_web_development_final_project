package ru.eventify.eventify_backend.exception;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(Long id){
        super("Booking not found :" + id);
    }
}
