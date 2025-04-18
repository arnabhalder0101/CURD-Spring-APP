package com.arnab.cruddemo.dao;

import com.arnab.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String lastname);
    List<Student> orderByLastname();
    void update(Student theStudent);
    int updateMultiple(String selector, int limit);
    void delete(Integer id);
    int deleteAll();

}
