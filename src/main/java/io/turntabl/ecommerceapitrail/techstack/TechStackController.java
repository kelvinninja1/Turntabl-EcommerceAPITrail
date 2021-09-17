package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "v1/techstacks")
public class TechStackController {

    @GetMapping
    public List<TechStack> listTechStacks(){
        return List.of(new TechStack(
                1L,
                "Java",
                Boolean.TRUE
        ));
    }

}
