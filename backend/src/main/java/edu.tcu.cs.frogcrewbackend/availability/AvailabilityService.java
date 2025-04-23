package edu.tcu.cs.frogcrewbackend.availability;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    public Availability findById(Integer id) {
        return availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found with id: " + id));
    }

    public Availability save(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public void deleteById(Integer id) {
        availabilityRepository.deleteById(id);
    }
}
