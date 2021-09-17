package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
