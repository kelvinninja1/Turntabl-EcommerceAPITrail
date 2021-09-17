package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/techstacks")
public class TechStackController {

    private final TechStackService techStackService;

    @Autowired
    public TechStackController(TechStackService techStackService) {
        this.techStackService = techStackService;
    }

    @GetMapping
    public List<TechStack> listTechStacks(){
        return techStackService.getTechStack();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> createStack(@RequestBody TechStack techStack){
        techStackService.addTechStack(techStack);
        return List.of("Success");
    }

}
