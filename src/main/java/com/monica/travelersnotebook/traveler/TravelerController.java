package com.monica.travelersnotebook.traveler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/traveler")
@CrossOrigin
public class TravelerController {

    private final TravelerService travelerService;

    @Autowired
    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }
    @GetMapping
    public List<Traveler> getTravelers() {
        return travelerService.getTravelers();
    }

    @PostMapping
    public void registerNewTraveler(@RequestBody Traveler traveler) {
        travelerService.addNewTraveler(traveler);
    }

    @DeleteMapping(path = "{travelerId}")
    public void deleteTraveler(@PathVariable("travelerId") Long id) {
        travelerService.deleteTraveler(id);
    }

    @PutMapping(path = "{travelerId}")
    public void updateTraveler(
            @PathVariable("travelerId") Long travelerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        travelerService.updateTraveler(travelerId, name, email);
    }

}
