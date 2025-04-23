package edu.tcu.cs.frogcrewbackend.crewmember;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    public List<CrewMember> findAll() {
        return crewMemberRepository.findAll();
    }

    public CrewMember findById(Integer id) {
        Optional<CrewMember> optionalCrewMember = crewMemberRepository.findById(id);
        return optionalCrewMember.orElseThrow(() -> new RuntimeException("Crew member not found."));
    }

    public CrewMember save(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }

    public void deleteById(Integer id) {
        crewMemberRepository.deleteById(id);
    }
}