package io.turntabl.ecommerceapitrail.techstack;

public class TechStack {
    private Long Id;
    private String name;
    private Boolean isTested;

    public TechStack(String name, Boolean isTested) {
        this.name = name;
        this.isTested = isTested;
    }

    public TechStack(Long id, String name, Boolean isTested) {
        Id = id;
        this.name = name;
        this.isTested = isTested;
    }

    public TechStack() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
                "ID=" + Id +
                ", name='" + name + '\'' +
                ", isTested=" + isTested +
                '}';
    }
}
