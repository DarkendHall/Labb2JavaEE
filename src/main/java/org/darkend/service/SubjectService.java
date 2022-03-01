package org.darkend.service;


import org.darkend.entity.Student;
import org.darkend.entity.Subject;
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
public class SubjectService {

    @PersistenceContext(unitName = "labb1")
    private EntityManager entityManager;

    @Inject
    private Validator validator;

    public SubjectService() {
    }

    public SubjectService(EntityManager entityManager, Validator validator) {
        this.entityManager = entityManager;
        this.validator = validator;
    }

    public Subject add(Subject subject) {
        validateSubject(subject);

        entityManager.persist(subject);
        return subject;
    }

    public Subject get(Long id) {
        return Optional.ofNullable(entityManager.find(Subject.class, id))
                .orElseThrow(() -> new EntityNotFoundException("No subject in DB with ID: " + id));
    }

    public Subject update(Long id, Subject subject) {
        validateSubject(subject);

        try {
            if (!Objects.equals(id, subject.getId())) {
                throw new IllegalActionException("Provided subject IDs does not match");
            }
            get(subject.getId());
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("No subject in DB to update with ID: " + subject.getId());
        }
        return entityManager.merge(subject);
    }

    public Subject remove(Subject subject) {
        get(subject.getId());
        entityManager.remove(subject);
        return subject;
    }

    public Subject remove(Long id) {
        Subject subjectToRemove = get(id);
        remove(subjectToRemove);
        return subjectToRemove;
    }

    public List<Subject> getAll() {

        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class)
                .getResultList();
    }

    public List<Student> getAllStudents(Long id) {
        return entityManager.createQuery("SELECT su.students FROM Subject su WHERE su.id = :id", Student.class)
                .setParameter("id", id)
                .getResultList();
    }

    private void validSubject(Subject subject) {
        if (subject.getId() == null &&
                subject.getName() == null)

            throw new BadRequestException("Provided subject has no valid data");
    }

    private void validateSubject(Subject subject) {
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);

        if (violations.size() > 0)
            throw new BadRequestException("Provided subject is not valid");
    }
}
