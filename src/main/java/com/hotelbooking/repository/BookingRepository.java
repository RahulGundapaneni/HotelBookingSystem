package com.hotelbooking.repository;

import com.hotelbooking.domain.Booking;
import com.hotelbooking.domain.BookingStatus;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByRoomIdAndStatusInAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
            Long roomId,
            Collection<BookingStatus> statuses,
            LocalDate checkOut,
            LocalDate checkIn);
}
