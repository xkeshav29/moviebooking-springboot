package com.booking.controller;

import com.booking.service.ActorService;
import com.booking.model.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actor/{id}")
    public Actor getActor(@PathVariable(value = "id") Long id) {
        return actorService.findById(id);
    }

    @PostMapping("/actor")
    public Actor createBooking(@Valid @RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @DeleteMapping("/actor/{id}")
    public void deleteBooking(@PathVariable(value = "id") Long id) {
        actorService.deleteActor(id);
    }

    @PutMapping("/actor/{id}")
    public Actor updateActor(@PathVariable(value = "id") Long id, @Valid @RequestBody Actor actor) {
        return actorService.updateActor(id, actor);
    }

}
