package com.hotelbooking.service;

import com.hotelbooking.domain.Booking;
import com.hotelbooking.domain.BookingStatus;
import com.hotelbooking.domain.Guest;
import com.hotelbooking.domain.Room;
import com.hotelbooking.repository.BookingRepository;
import com.hotelbooking.repository.GuestRepository;
import com.hotelbooking.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.EnumSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;

    public BookingService(RoomRepository roomRepository, GuestRepository guestRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(Long roomId, String email, String firstName, String lastName, int guests, LocalDate checkIn, LocalDate checkOut) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        if (bookingRepository.existsByRoomIdAndStatusInAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
                roomId,
                EnumSet.of(BookingStatus.CONFIRMED, BookingStatus.PENDING),
                checkOut.minusDays(1),
                checkIn.plusDays(1))) {
            throw new IllegalStateException("Room already booked for selected dates");
        }

        Guest guest = guestRepository
                .findByEmail(email)
                .orElseGet(() -> guestRepository.save(new Guest(firstName, lastName, email, null)));

        Booking booking = new Booking(room, guest, checkIn, checkOut, guests);
        booking.setStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
    }
}
