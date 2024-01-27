package com.monica.travelersnotebook.traveler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {

    @Query("SELECT t FROM Traveler t WHERE t.email = ?1")
    Optional<Traveler> findTravelerByEmail(String email);
}
