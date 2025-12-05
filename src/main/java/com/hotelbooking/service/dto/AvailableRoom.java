package com.hotelbooking.service.dto;

import com.hotelbooking.domain.RoomType;
import java.math.BigDecimal;

public record AvailableRoom(
        Long hotelId,
        String hotelName,
        double hotelRating,
        Long roomId,
        String roomNumber,
        RoomType roomType,
        int capacity,
        BigDecimal baseRate) {
}
