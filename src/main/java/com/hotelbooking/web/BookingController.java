package com.hotelbooking.web;

import com.hotelbooking.domain.Booking;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.service.dto.BookingRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@Valid @RequestBody BookingRequest request) {
        // Basic controller to unblock end-to-end testing, revisit with DTOs later
        return bookingService.createBooking(
                request.roomId(),
                request.email(),
                request.firstName(),
                request.lastName(),
                request.guests(),
                request.checkIn(),
                request.checkOut());
    }

    @PostMapping("/{bookingId}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
