package com.fighter.molonplanner.domain.dto;

public record ErrorResponse (
        int status ,
        String message ,
        String details
) {
}
