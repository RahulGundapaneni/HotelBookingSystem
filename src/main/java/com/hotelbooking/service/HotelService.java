package com.hotelbooking.service;

import com.hotelbooking.domain.Hotel;
import com.hotelbooking.repository.HotelRepository;
import com.hotelbooking.service.dto.HotelSummary;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional(readOnly = true)
    public List<HotelSummary> listHotels(String city) {
        Stream<Hotel> hotelsStream;
        if (city != null && !city.isBlank()) {
            hotelsStream = hotelRepository.findByCityIgnoreCaseOrderByRatingDesc(city).stream();
        } else {
            hotelsStream = hotelRepository.findAll().stream();
        }
        return hotelsStream
                .map(hotel -> new HotelSummary(
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getCity(),
                        hotel.getRating(),
                        hotel.getRooms().size()))
                .toList();
    }
}
