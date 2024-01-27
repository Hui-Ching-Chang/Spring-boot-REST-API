package com.monica.travelersnotebook.traveler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TravelerService {

    private final TravelerRepository travelerRepository;
    @Autowired
    public TravelerService(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }
    public List<Traveler> getTravelers(){
        return travelerRepository.findAll();
    }

    public void addNewTraveler(Traveler traveler) {
        Optional<Traveler> travelerOptional = travelerRepository.findTravelerByEmail(traveler.getEmail());

        if(travelerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        travelerRepository.save(traveler);
    }

    public void deleteTraveler(Long travelerId) {
        boolean exists = travelerRepository.existsById(travelerId);

        if (!exists) {
            throw new IllegalStateException("traveler with " + travelerId + " does not exists");
        }

        travelerRepository.deleteById(travelerId);
    }

    @Transactional
    public void updateTraveler(Long travelerId, String name, String email) {
        Traveler traveler = travelerRepository.findById(travelerId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id" + travelerId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(traveler.getName(), name)) {
            traveler.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(traveler.getEmail(), email)) {
            Optional<Traveler> travelerOptional = travelerRepository.findTravelerByEmail(email);

            if(travelerOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            traveler.setEmail(email);
        }
    }
}
