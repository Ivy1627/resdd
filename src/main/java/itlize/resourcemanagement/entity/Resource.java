package itlize.resourcemanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "res_name")
    private String resName;

    @Column(name = "res_code")
    private int resCode;

    //how to handle the resources being in a list?
//    @Column(name = "res_folder")
//    private List<Resource> resList;

    @ManyToMany
    @JoinTable(name = "project_resource",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Project> projects = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
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
}
