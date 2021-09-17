package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public void deleteTechStack(Long techStackId) {
        boolean exists = techStackRepository.existsById(techStackId);
        if (!exists) {
            throw new IllegalStateException("TechStack with ID:" + techStackId + " does exist");
        }
        techStackRepository.deleteById(techStackId);
    }

    @Transactional
    public void updateTechStack(Long techStackId, Map<String, Object> change) {
        TechStack techStack = techStackRepository.findById(techStackId).orElseThrow(() -> new IllegalStateException("TechStack with ID:" + techStackId + " does exist"));

        String name = change.get("name").toString();
        if (name != null && name.length() > 0 && !Objects.equals(name, techStack.getName())) {
            techStack.setName(name);
        }

        boolean tested = Boolean.parseBoolean(change.get("tested").toString());
        if (!Objects.equals(tested, techStack.getTested())) {
            techStack.setTested(tested);
        }
    }
}
