package org.darkend.service;


import org.darkend.entity.Teacher;
import org.darkend.exception.IllegalActionException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Transactional
public class TeacherService {

    @PersistenceContext(unitName = "labb1")
    private EntityManager entityManager;

    @Inject
    private Validator validator;

    public TeacherService() {
    }

    public TeacherService(EntityManager entityManager, Validator validator) {
        this.entityManager = entityManager;
        this.validator = validator;
    }

    public Teacher add(Teacher teacher) {
        validateTeacher(teacher);

        entityManager.persist(teacher);
        return teacher;
    }

    public Teacher get(Long id) {
        return Optional.ofNullable(entityManager.find(Teacher.class, id))
                .orElseThrow(() -> new EntityNotFoundException("No teacher in DB with ID: " + id));
    }

    public Teacher update(Long id, Teacher teacher) {
        validateTeacher(teacher);

        try {
            if (!Objects.equals(id, teacher.getId())) {
                throw new IllegalActionException("Provided teacher IDs does not match");
            }
            get(teacher.getId());
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("No teacher in DB to update with ID: " + teacher.getId());
        }
        return entityManager.merge(teacher);
    }

    public Teacher patch(Long id, Teacher teacher) {
        Teacher teacherToPatch = get(id);

        validTeacher(teacher);
        if (teacher.getFirstName() != null)
            teacherToPatch.setFirstName(teacher.getFirstName());
        if (teacher.getLastName() != null)
            teacherToPatch.setLastName(teacher.getLastName());

        return teacherToPatch;
    }

    public Teacher remove(Teacher teacher) {
        get(teacher.getId());
        entityManager.remove(teacher);
        return teacher;
    }

    public Teacher remove(Long id) {
        Teacher teacherToRemove = get(id);
        remove(teacherToRemove);
        return teacherToRemove;
    }

    public List<Teacher> getAll() {

        return entityManager.createQuery("SELECT s FROM Teacher s", Teacher.class)
                .getResultList();
    }

    private void validTeacher(Teacher teacher) {
        if (teacher.getId() == null &&
                teacher.getFirstName() == null &&
                teacher.getLastName() == null)

            throw new BadRequestException("Provided teacher has no valid data");
    }

    private void validateTeacher(Teacher teacher) {
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);

        if (violations.size() > 0)
            throw new BadRequestException("Provided teacher is not valid");
    }
}
