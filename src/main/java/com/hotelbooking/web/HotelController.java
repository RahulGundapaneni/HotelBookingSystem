package com.hotelbooking.web;

import com.hotelbooking.service.HotelService;
import com.hotelbooking.service.dto.HotelSummary;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelSummary> list(@RequestParam(required = false) String city) {
        return hotelService.listHotels(city);
    }
}
