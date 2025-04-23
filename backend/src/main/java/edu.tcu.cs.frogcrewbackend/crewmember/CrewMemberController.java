package edu.tcu.cs.frogcrewbackend.crewmember;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/crewmembers")
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @GetMapping
    public List<CrewMember> findAll() {
        return crewMemberService.findAll();
    }

    @GetMapping("/{id}")
    public CrewMember findById(@PathVariable Integer id) {
        return crewMemberService.findById(id);
    }

    @PostMapping
    public CrewMember save(@RequestBody CrewMember crewMember) {
        return crewMemberService.save(crewMember);
    }

    @PutMapping("/{id}")
    public CrewMember update(@PathVariable Integer id, @RequestBody CrewMember updatedCrewMember) {
        CrewMember existingCrewMember = crewMemberService.findById(id);

        existingCrewMember.setFirstName(updatedCrewMember.getFirstName());
        existingCrewMember.setLastName(updatedCrewMember.getLastName());
        existingCrewMember.setEmail(updatedCrewMember.getEmail());
        existingCrewMember.setPhoneNumber(updatedCrewMember.getPhoneNumber());
        existingCrewMember.setRole(updatedCrewMember.getRole());
        existingCrewMember.setQualifiedPosition(updatedCrewMember.getQualifiedPosition());

        return crewMemberService.save(existingCrewMember);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        crewMemberService.deleteById(id);
    }
}
