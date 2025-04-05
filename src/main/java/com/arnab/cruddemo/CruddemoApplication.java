package com.arnab.cruddemo;

import com.arnab.cruddemo.dao.StudentDAO;
import com.arnab.cruddemo.dao.StudentDAOImpl;
import com.arnab.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            // createStudent(studentDAO);
            // createMultipleStudents(studentDAO);
            // readStudentData(studentDAO);
            // findAllStudents(studentDAO);
            findStdByLastName(studentDAO);
        };
    }

    private void findStdByLastName(StudentDAO studentDAO) {
        List<Student> studentList = studentDAO.findByLastName("Ghosh");
        for (Student s: studentList){
            System.out.println(s);
        }
    }

    private void findAllStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void readStudentData(StudentDAO studentDAO) {

        // create a student obj
        System.out.println("Creating the student...");
        Student s = new Student("Sanchari", "Sadhu", "ss@gmail.com");

        // save it
        System.out.println("Saving the student...");
        studentDAO.save(s);

        // display the id
        int s_id = s.getId();
        System.out.println("Student: " + s_id);

        // retrieve the student
        System.out.println("Retrieveing the student with id: ");
        Student stu = studentDAO.findById(s_id);

        // display the student
        System.out.println("Found the student: " + stu);

    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        //create multiple students
        System.out.println("Creating students ....");
        Student st1 = new Student("Kunal", "Ghosh", "kunal.g@tcs.com");
        Student st2 = new Student("Shreyami", "Chaudhuri", "shreyami.c@tcs.com");

        System.out.println("saving students...");
        studentDAO.save(st1);
        studentDAO.save(st2);

        // display the ids


    }

    private void createStudent(StudentDAO studentDAO) {
        // create the student object
        System.out.println("Creating student obj: ");
        Student st = new Student("Arnab", "Halder", "halder.arnab2@tcs.com");

        // save the student object
        System.out.println("Saving the student: ");
        studentDAO.save(st);

        // display id of the student
        System.out.println("Saved Student generated Id: " + st.getId());


    }


}
