package edu.tcu.cs.frogcrewbackend.availability;

import edu.tcu.cs.frogcrewbackend.crewmember.CrewMember;
import edu.tcu.cs.frogcrewbackend.crewmember.CrewMemberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final CrewMemberRepository crewMemberRepository;

    public AvailabilityController(AvailabilityService availabilityService, CrewMemberRepository crewMemberRepository) {
        this.availabilityService = availabilityService;
        this.crewMemberRepository = crewMemberRepository;
    }

    @GetMapping
    public List<Availability> findAll() {
        return availabilityService.findAll();
    }

    @GetMapping("/{id}")
    public Availability findById(@PathVariable Integer id) {
        return availabilityService.findById(id);
    }

    @PostMapping
    public Availability save(@RequestBody Availability availability) {
        Integer crewId = availability.getCrewMember().getId();

        // Fetch the full crew member from the DB
        CrewMember fullCrewMember = crewMemberRepository.findById(crewId)
                .orElseThrow(() -> new RuntimeException("CrewMember not found"));

        availability.setCrewMember(fullCrewMember);
        return availabilityService.save(availability);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        availabilityService.deleteById(id);
    }
}
