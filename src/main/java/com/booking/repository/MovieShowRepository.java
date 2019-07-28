package com.booking.repository;

import com.booking.model.entity.Movieshow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MovieShowRepository extends JpaRepository<Movieshow, Long> {

    @Query("SELECT s FROM Movieshow s INNER JOIN Movie m ON s.movie.id = m.id WHERE s.showTime >= :fromTime AND " +
            "s.showTime <= :toTime AND m.language = :language")
    Page<Movieshow> findByLanguage(Date fromTime, Date toTime, String language, Pageable pageable);

    @Query("SELECT s FROM Movieshow s INNER JOIN Movie m ON s.movie.id = m.id WHERE s.showTime >= :fromTime AND " +
            "s.showTime <= :toTime AND m.theater.locality=:locality")
    Page<Movieshow> findByLocality(Date fromTime, Date toTime, String locality, Pageable pageable);

    @Query("SELECT s FROM Movieshow s INNER JOIN Movie m ON s.movie.id = m.id WHERE s.showTime >= :fromTime AND " +
            "s.showTime <= :toTime AND m.theater.id=:theater_id")
    Page<Movieshow> findByTheater(Date fromTime, Date toTime, Long theater_id, Pageable pageable);

    @Query("SELECT s FROM Movieshow s INNER JOIN Movie m ON s.movie.id = m.id WHERE s.showTime >= :fromTime AND " +
            "s.showTime <= :toTime AND m.id=:movie_id")
    Page<Movieshow> findByMovie(Date fromTime, Date toTime, Long movie_id, Pageable pageable);

    @Query("SELECT s FROM Movieshow s INNER JOIN Movie m ON s.movie.id = m.id WHERE s.showTime >= :fromTime AND " +
            "s.showTime <= :toTime AND m.theater.id=:theater_id AND m.id=:movie_id")
    Page<Movieshow> findByTheaterAndMovie(Date fromTime, Date toTime, Long movie_id, Long theater_id, Pageable pageable);



}
