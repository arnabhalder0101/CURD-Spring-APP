package com.arnab.cruddemo.dao;

import com.arnab.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for utility manager
    private EntityManager entityManager;

    //insert entity manager using constructor injection
    @Autowired
    public  StudentDAOImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;

    }

    @Override
    @Transactional          // needed as we are doing create operation (C-U-D --> needed)
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    // @Transactional --> not needed as only reading operation is here!
    // only at a time of db modification it's needed
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // creating the query --
        TypedQuery<Student> quryStudent = entityManager.createQuery("FROM Student", Student.class);
        // return results --
        return quryStudent.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastname) {
        // query for student list
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName=:lastname", Student.class);

        query.setParameter("lastname", lastname);

        return  query.getResultList();
    }

    @Override
    public List<Student> orderByLastname() {
        // query
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName desc", Student.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        // update the student entity --
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public int updateMultiple(String selector, int limit) {
        int numberOfRows = entityManager.createQuery("UPDATE Student s SET s.lastName= :lastname WHERE s.id<= :num").setParameter("lastname", selector).setParameter("num", limit).executeUpdate();
        return numberOfRows;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // find the student by id--
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);

    }


}
