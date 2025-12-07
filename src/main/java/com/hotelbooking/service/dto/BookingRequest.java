package com.hotelbooking.service.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BookingRequest(
        @NotNull Long roomId,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        @Min(1) int guests,
        @NotNull @Future LocalDate checkIn,
        @NotNull @Future LocalDate checkOut) {
}
