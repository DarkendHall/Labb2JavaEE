package org.darkend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2)
    private String name;

    @NotEmpty
    @ManyToMany(targetEntity = Student.class)
    private Set<Student> students;

    @NotNull
    @ManyToOne(targetEntity = Teacher.class)
    private Teacher teacher;


    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Subject setStudents(Set<Student> student) {
        this.students = student;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }
}
