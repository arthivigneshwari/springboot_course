package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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

	//this will be executed after spring beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDao){
		return runner -> {
			//System.out.println("Hello World");
			//createStudent(studentDao);
			createMultipleStudents(studentDao);
			//readStudent(studentDao);
			//queryForStudents(studentDao);
			//queryForStudentsByLastName(studentDao);
            //updateStudent(studentDao);
            //deleteStudent(studentDao);
            //deleteAllStudent(studentDao);
		};
	}

    private void deleteAllStudent(StudentDAO studentDao) {
        System.out.println("Deleting all students...");
        int numRowsDeleted = studentDao.deleteAll();
        System.out.println("Deleted row count: "+numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDao) {
        int studentId = 3;
        System.out.println("Deleting student id..."+studentId);

        Student studentToDelete = studentDao.findById(3);
        if(studentToDelete != null) {
            System.out.println("Found student: "+studentToDelete);
            studentDao.delete(studentId);
            System.out.println("Student deleted...");
        }else{
            System.out.println("No student found with id: "+studentId);
        }
    }

    private void updateStudent(StudentDAO studentDao) {
            //retrieve the student id.. primary key
            Integer studentId=1;
            System.out.println("Getting student with id " +studentId);
            Student myStudent = studentDao.findById(studentId);

            //update first name
            System.out.println("Updating Student...");
            myStudent.setFirstName("Leo");

           //update the student
            studentDao.update(myStudent);

           //display the updated student
            System.out.println("Updated student: " +myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDao) {
	      //get a list of students with lastName

		List<Student> theStudents = studentDao.findByLastName("Tiger");

		  //display the list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDao) {
		//get a list of students
		List<Student> theStudents = studentDao.findAll();

		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}

	}

	private void readStudent(StudentDAO studentDao) {
		//create Student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy","Duck","daffy@luv2code.com");

		//save the student
		System.out.println("Saving the student");
		studentDao.save(tempStudent);

		//display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " +theId);

		//retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: "+theId);
		Student myStudent = studentDao.findById(theId);

		//display student
		System.out.println("Found the student: "+myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDao) {
		//create multiple students
		System.out.println("Creating multiple students...");
		Student student1 = new Student("Leo","Tiger","leo@luv2code.com");
		Student student2 = new Student("Bonita","Applebrum","bonita@luv2code.com");
		Student student3 = new Student("Paul","Deo","paul@luv2code.com");

		//save the student objects
		System.out.println("Saving the student objects...");
		studentDao.save(student1);
		studentDao.save(student2);
		studentDao.save(student3);
	}

	private void createStudent(StudentDAO studentDao) {

		//create Student object
		System.out.println(" Creating new student object... ");
		Student student = new Student("John","Doe","john@luv2code.com");

		//save the student object
		System.out.println(" Saving student object... ");
		studentDao.save(student);

		//display id of the saved student
		System.out.println(" Fetching saved student's student id " +student.getId());
	}

}
