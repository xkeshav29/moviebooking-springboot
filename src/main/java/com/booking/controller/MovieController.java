package com.booking.controller;

import com.booking.model.entity.Movie;
import com.booking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable(value = "id") Long id) {
        return movieService.findById(id);
    }

    @PostMapping("/movie")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable(value = "id") Long id) {
        movieService.deleteMovie(id);
    }

    @PutMapping("/movie/{id}")
    public Movie updateMovie(@PathVariable(value = "id") Long id, @Valid @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

}
