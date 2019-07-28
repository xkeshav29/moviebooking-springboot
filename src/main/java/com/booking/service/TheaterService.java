package com.booking.service;

import com.booking.model.entity.Theater;
import com.booking.exception.ResourceNotFoundException;
import com.booking.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Theater findById(Long id) {
        return theaterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Theater", "id", id));
    }

    public void deleteTheater(Long id) {
        theaterRepository.findById(id)
                .ifPresent(theater -> theaterRepository.delete(theater));
    }

    public Theater updateTheater(Long id, Theater theater) {
        return theaterRepository.findById(id).map(t -> {
            t.setName(theater.getName());
            t.setCity(theater.getCity());
            t.setLocality(theater.getLocality());
            return theaterRepository.save(t);
        }).orElseThrow(() -> new ResourceNotFoundException("Theater", "id", id));
    }

    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    public Page<Theater> findByCity(String city, Pageable pageable) {
        return theaterRepository.findByCity(city, pageable);
    }
}
