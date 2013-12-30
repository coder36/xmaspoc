package org.coder36.pdf.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Student {

    @Id
    @SequenceGenerator(name="seq", initialValue=898676761, allocationSize=100)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @NotEmpty(message = "Name is required.")
    @Column
    private String name;

    @NotEmpty(message = "University is required.")
    @Column
    private String university;

    @Range( min = 1, max=9000, message = "Enter a value between 1 and 9000")
    @NotNull(message = "Tuition fee is required.")
    @Column
    private Long tuitionFee;

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

    public Long getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(Long tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
