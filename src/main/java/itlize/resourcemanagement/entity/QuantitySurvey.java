package itlize.resourcemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
@Table(name = "quantity_surveys")
public class QuantitySurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    public enum Type {
        NUMBER,
        TEXT,
        FORMULA;
//        @Column(name = "formula")
//        private String formula;
//
//        Type(String s) {
//            this.formula = s;
//        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "col_name")
    private String colName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proj_id")
    private Project proj;

    @Column(name = "formula")
    private String formula;

    public void setColName(String colName){
        this.colName = colName;
    }

    public void setProj(Project proj) {
        this.proj = proj;
    }

    public void setFormula(String formula){
        this.formula = formula;
    }

    public String getColName(){ return this.colName;}

    public String getFormula(){ return this.formula;}

    public Type getType() {
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    public Project getProject() {
        return this.proj;
    }
}
