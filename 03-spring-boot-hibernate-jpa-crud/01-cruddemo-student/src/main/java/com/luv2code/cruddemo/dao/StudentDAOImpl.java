package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entityManager
    private EntityManager entityManager;

    //inject entityManager using Constructor injection
    @Autowired //@Autowired annotation is optional if we have only one constructor but recommeded to use it
    public void StudentDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student); //saves the student to the database
    }

    @Override
    public Student findById(Integer id) {
        //find a Student with specific id
        return entityManager.find(Student.class,id); //id is the primary key
    }

    @Override
    public List<Student> findAll() {
        //create query
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery
                //("FROM Student order by lastName",Student.class);//first parameter is Entity class name
                        ("FROM Student", Student.class);
        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class
        );
        //JPQL Named parameters are prefixed with a colon :
        //:theData is the placeholder that is filled in later

        //set query parameters
        theQuery.setParameter("theData",theLastName);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve the student
        Student theStudent = entityManager.find(Student.class,id);

        //delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student")
                                          .executeUpdate();
        return numRowsDeleted;
    }
}
