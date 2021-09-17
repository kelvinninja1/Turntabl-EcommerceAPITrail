package io.turntabl.ecommerceapitrail.techstack;

import javax.persistence.*;

@Entity
@Table
public class TechStack {
    @Id
    @SequenceGenerator(
            name = "techstack_sequence",
            sequenceName = "techstack_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "techstack_sequence"
    )
    private Long id;
    private String name;
    private Boolean isTested;

    public TechStack(String name, Boolean isTested) {
        this.name = name;
        this.isTested = isTested;
    }

    public TechStack(Long id, String name, Boolean isTested) {
        id = id;
        this.name = name;
        this.isTested = isTested;
    }

    public TechStack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTested() {
        return isTested;
    }

    public void setTested(Boolean tested) {
        isTested = tested;
    }

    @Override
    public String toString() {
        return "TechStack{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", isTested=" + isTested +
                '}';
    }
}
