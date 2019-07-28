package com.booking.controller;

import com.booking.model.entity.Movieshow;
import com.booking.service.MovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class MovieShowController {

    private MovieShowService movieShowService;

    @Autowired
    public MovieShowController(MovieShowService movieShowService) {
        this.movieShowService = movieShowService;
    }

    @GetMapping("/show/{id}")
    public Movieshow getShow(@PathVariable(value = "id") Long id) {
        return movieShowService.findById(id);
    }

    @GetMapping("/show")
    public List<Movieshow> getAllShows() {
        return movieShowService.findAll();
    }

    @PostMapping("/show")
    public Movieshow createShow(@Valid @RequestBody Movieshow show) {
        return movieShowService.createShow(show);
    }

    @DeleteMapping("/show/{id}")
    public void deleteShow(@PathVariable(value = "id") Long id) {
        movieShowService.deleteShow(id);
    }

    @GetMapping(value = "/show", params = {"fromTime", "toTime", "language"})
    public Page<Movieshow> findShowByLanguage(@RequestParam(required = false) Date fromTime,
                                              @RequestParam(required = false) Date toTime,
                                              @RequestParam String language,
                                              Pageable pageable) {
        return movieShowService.findShowByLanguage(fromTime, toTime, language, pageable);
    }

    @GetMapping(value = "/show", params = {"fromTime", "toTime", "locality"})
    public Page<Movieshow> findShowByLocality(@RequestParam(required = false) Date fromTime,
                                              @RequestParam(required = false) Date toTime,
                                              @RequestParam String locality,
                                              Pageable pageable) {
        return movieShowService.findShowByLocality(fromTime, toTime, locality, pageable);
    }

    @GetMapping(value = "/show", params = {"fromTime", "toTime", "theater_id"})
    public Page<Movieshow> findShowByTheater(@RequestParam(required = false) Date fromTime,
                                             @RequestParam(required = false) Date toTime,
                                             @RequestParam Long theater_id,
                                             Pageable pageable) {
        return movieShowService.findShowByTheater(fromTime, toTime, theater_id, pageable);
    }

    @GetMapping(value = "/show", params = {"fromTime", "toTime", "movie_id"})
    public Page<Movieshow> findShowByMovie(@RequestParam(required = false) Date fromTime,
                                           @RequestParam(required = false) Date toTime,
                                           @RequestParam Long movie_id,
                                           Pageable pageable) {
        return movieShowService.findShowByMovie(fromTime, toTime, movie_id,pageable);
    }

    @GetMapping(value = "/show", params = {"fromTime", "toTime", "theater_id", "movie_id"})
    public Page<Movieshow> findShowByTheaterAndMovie(@RequestParam(required = false) Date fromTime,
                                                     @RequestParam(required = false) Date toTime,
                                                     @RequestParam Long theater_id,
                                                     @RequestParam Long movie_id,
                                                     Pageable pageable) {
        return movieShowService.findShowByTheaterAndMovie(fromTime, toTime, theater_id, movie_id, pageable);
    }
}
