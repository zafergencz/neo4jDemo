package com.example.entity;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node(labels = {"Student"})
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String country;

    //1-1 relation, use List when 1-> many relation
    @Relationship(type = "BELONGS_TO" , direction = Relationship.Direction.OUTGOING)
    private Department department;

    @Relationship(type = "IS_LEARNING" , direction = Relationship.Direction.OUTGOING)
    private List<IsLearningRelation> isLearningRelations;


    @Property(name="birth_year")
    private Integer birthYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<IsLearningRelation> getIsLearningRelations() {
        return isLearningRelations;
    }

    public void setIsLearningRelations(List<IsLearningRelation> isLearningRelations) {
        this.isLearningRelations = isLearningRelations;
    }
}
