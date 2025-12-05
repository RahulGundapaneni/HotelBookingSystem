package com.hotelbooking.service;

import com.hotelbooking.domain.BookingStatus;
import com.hotelbooking.domain.Room;
import com.hotelbooking.repository.BookingRepository;
import com.hotelbooking.repository.HotelRepository;
import com.hotelbooking.repository.RoomRepository;
import com.hotelbooking.service.dto.AvailableRoom;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvailabilityService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public AvailabilityService(
            HotelRepository hotelRepository,
            RoomRepository roomRepository,
            BookingRepository bookingRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(readOnly = true)
    public List<AvailableRoom> search(String city, LocalDate checkIn, LocalDate checkOut, int guests) {
        var hotels = hotelRepository.findByCityIgnoreCaseOrderByRatingDesc(city);
        return hotels.stream()
                .flatMap(hotel -> roomRepository.findByHotelId(hotel.getId()).stream()
                        .filter(room -> room.getCapacity() >= guests)
                        .filter(room -> isRoomAvailable(room.getId(), checkIn, checkOut))
                        .map(room -> new AvailableRoom(
                                hotel.getId(),
                                hotel.getName(),
                                hotel.getRating(),
                                room.getId(),
                                room.getRoomNumber(),
                                room.getType(),
                                room.getCapacity(),
                                room.getBaseRate())))
                .collect(Collectors.toList());
    }

    private boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return !bookingRepository.existsByRoomIdAndStatusInAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(
                roomId,
                EnumSet.of(BookingStatus.CONFIRMED, BookingStatus.PENDING),
                checkOut.minusDays(1),
                checkIn.plusDays(1));
    }
}
