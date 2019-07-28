package com.booking.service;

import com.booking.exception.ResourceNotFoundException;
import com.booking.model.entity.Movie;
import com.booking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }

    public void deleteMovie(Long id) {
        movieRepository.findById(id)
                .ifPresent(theater -> movieRepository.delete(theater));
    }

    public Movie updateMovie(Long id, Movie movie) {
        return movieRepository.findById(id).map(m -> {
            m.setName(movie.getName());
            m.setGenre(movie.getGenre());
            m.setLanguage(movie.getLanguage());
            return movieRepository.save(m);
        }).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }
}
