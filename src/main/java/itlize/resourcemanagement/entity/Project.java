package itlize.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "proj_name")
    private String projName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("projects")
    private Set<Resource> resources;

    public Project(){}

    public String getProjName(){
        return this.projName;
    }

    public void setProjName(String projName){
        this.projName = projName;
    }

    public void setUser(User user) { this.user = user;}

    public User getUser() {
        return this.user;
    }

    public Set<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Set<Resource> resources){
        this.resources = resources;
    }
}
