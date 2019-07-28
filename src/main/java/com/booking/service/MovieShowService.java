package com.booking.service;

import com.booking.exception.ResourceNotFoundException;
import com.booking.model.entity.Movieshow;
import com.booking.repository.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MovieShowService {

    private MovieShowRepository movieShowRepository;

    @Autowired
    public MovieShowService(MovieShowRepository movieShowRepository) {
        this.movieShowRepository = movieShowRepository;
    }

    public Movieshow createShow(Movieshow show) {
        return movieShowRepository.save(show);
    }

    public Movieshow findById(Long id) {
        return movieShowRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show", "id", id));
    }

    public void deleteShow(Long id) {
        movieShowRepository.findById(id)
                .ifPresent(theater -> movieShowRepository.delete(theater));
    }

    public Page<Movieshow> findShowByLanguage(Date fromTime, Date toTime, String language, Pageable pageable) {
        if(fromTime == null && toTime == null) {
            Calendar calendar = Calendar.getInstance();
            fromTime = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            toTime = calendar.getTime();
        }
        return movieShowRepository.findByLanguage(fromTime, toTime, language, pageable);
    }

    public Page<Movieshow> findShowByLocality(Date fromTime, Date toTime, String locality, Pageable pageable) {
        if(fromTime == null && toTime == null) {
            Calendar calendar = Calendar.getInstance();
            fromTime = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            toTime = calendar.getTime();
        }
        return movieShowRepository.findByLocality(fromTime, toTime, locality, pageable);
    }

    public Page<Movieshow> findShowByTheater(Date fromTime, Date toTime, Long theater_id, Pageable pageable) {
        if(fromTime == null && toTime == null) {
            Calendar calendar = Calendar.getInstance();
            fromTime = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            toTime = calendar.getTime();
        }
        return movieShowRepository.findByTheater(fromTime, toTime, theater_id, pageable);
    }

    public Page<Movieshow> findShowByMovie(Date fromTime, Date toTime, Long movie_id, Pageable pageable) {
        if(fromTime == null && toTime == null) {
            Calendar calendar = Calendar.getInstance();
            fromTime = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            toTime = calendar.getTime();
        }
        return movieShowRepository.findByMovie(fromTime, toTime, movie_id, pageable);
    }


    public Page<Movieshow> findShowByTheaterAndMovie(Date fromTime, Date toTime, Long theater_id, Long movie_id,
                                                     Pageable pageable) {
        if(fromTime == null && toTime == null) {
            Calendar calendar = Calendar.getInstance();
            fromTime = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            toTime = calendar.getTime();
        }
        return movieShowRepository.findByTheaterAndMovie(fromTime, toTime, theater_id, movie_id, pageable);
    }

    public List<Movieshow> findAll() {
        return movieShowRepository.findAll();
    }
}
