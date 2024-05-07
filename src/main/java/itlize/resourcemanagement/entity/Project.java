package itlize.resourcemanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "proj_name")
    private String projName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Project(){}

    public String getProjName(){
        return this.projName;
    }

    public void setProjName(String projName){
        this.projName = projName;
    }

    public void setUser(User user) { this.user = user;}
}
