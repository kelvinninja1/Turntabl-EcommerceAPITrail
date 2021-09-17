package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Object> createStack(@RequestBody TechStack techStack){
        techStackService.addTechStack(techStack);
        return List.of("Success", techStack);
    }

    @DeleteMapping(path = "{techStackId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> removeStack(@PathVariable("techStackId") Long techStackId){
        techStackService.deleteTechStack(techStackId);
        return List.of("Success");
    }

    @PutMapping(path = "{techStackId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> modifyStack(@PathVariable("techStackId") Long techStackId, @RequestBody Map<String, Object> change){
        techStackService.updateTechStack(techStackId, change);
        return List.of("Success",
                change);

    }


}
