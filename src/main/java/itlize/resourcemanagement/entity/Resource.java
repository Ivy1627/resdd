package itlize.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "resources")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Add this annotation
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "res_name", unique = true)
    private String resName;

    @Column(name = "res_code", unique = true)
    private int resCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "resource_project",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "res", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("res")
    private List<ResourceAttributes> resAttributes;

    public Resource(){}

    public void setResName(String resName) {
        this.resName = resName;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResName(){
        return this.resName;
    }

    public int getResCode() { return this.resCode; }

    public Long getId() {
        return this.id;
    }

    public List<ResourceAttributes> getResAttributes() {
        return this.resAttributes;
    }

    public void setResAttributes(List<ResourceAttributes> resAttributes) {
        this.resAttributes = resAttributes;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
