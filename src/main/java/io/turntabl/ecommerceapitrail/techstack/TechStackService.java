package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechStackService {

    private final TechStackRepository techStackRepository;

    @Autowired
    public TechStackService(TechStackRepository techStackRepository) {
        this.techStackRepository = techStackRepository;
    }

    public List<TechStack> getTechStack(){
        return  techStackRepository.findAll();
    }

    public void addTechStack(TechStack techStack) {
        Optional<TechStack> techStackByName = techStackRepository.findTechStackByName(techStack.getName());
        if (techStackByName.isPresent()){
            throw new IllegalStateException("TechStack Already Exists");
        }
        techStackRepository.save(techStack);
    }
}
