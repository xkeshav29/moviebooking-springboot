package com.booking.service;

import com.booking.exception.ResourceNotFoundException;
import com.booking.model.entity.Actor;
import com.booking.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor findById(Long id) {
        return actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor", "id", id));
    }

    public void deleteActor(Long id) {
         actorRepository.findById(id)
                .ifPresent(actor -> actorRepository.delete(actor));
    }

    public Actor updateActor(Long id, Actor actor) {
        return actorRepository.findById(id).map(a -> {
            a.setName(actor.getName());
            return actorRepository.save(a);
        }).orElseThrow(() -> new ResourceNotFoundException("Actor", "id", id));
    }
}
