package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechStackService {

    public List<TechStack> getTechStack(){
        return  List.of(new TechStack(
                1L,
                "Java",
                Boolean.TRUE
        ));
    }
}
