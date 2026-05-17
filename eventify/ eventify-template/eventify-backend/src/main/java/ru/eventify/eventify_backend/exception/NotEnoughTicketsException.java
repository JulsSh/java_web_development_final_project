package ru.eventify.eventify_backend.exception;

public class NotEnoughTicketsException extends RuntimeException {
    public NotEnoughTicketsException(int requestedSlots, int availableSlots){
        super("Not enough available tickets: "+ "requested "+requestedSlots +", available "+ availableSlots);
    }
}
