package com.booking.controller;

import com.booking.model.entity.Theater;
import com.booking.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TheaterController {

    private TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/theater/{id}")
    public Theater getTheater(@PathVariable(value = "id") Long id) {
        return theaterService.findById(id);
    }

    @GetMapping("/theater")
    public List<Theater> getAllTheater() {
        return theaterService.findAll();
    }

    @GetMapping(value = "/theater", params = {"city"})
    public Page<Theater> findByCity(@RequestParam String city, Pageable pageable) {
        return theaterService.findByCity(city, pageable);
    }


    @PostMapping("/theater")
    public Theater createTheater(@Valid @RequestBody Theater theater) {
        return theaterService.createTheater(theater);
    }

    @DeleteMapping("/theater/{id}")
    public void deleteTheater(@PathVariable(value = "id") Long id) {
        theaterService.deleteTheater(id);
    }

    @PutMapping("/theater/{id}")
    public Theater updateTheater(@PathVariable(value = "id") Long id, @Valid @RequestBody Theater theater) {
        return theaterService.updateTheater(id, theater);
    }
}
