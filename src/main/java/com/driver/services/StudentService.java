package com.driver.services;

import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    public Student getDetailsByEmail(String email){
        Student student = null;
        List<Student> studentList = studentRepository4.findAll();
        for(Student student1:studentList){
            if(student1.getEmailId() == email){
                student = student1;
                break;
            }
        }

        return student;
    }

    public Student getDetailsById(int id){
        Student student = null;
        student = studentRepository4.findById(id).get();
        return student;
    }

    public void createStudent(Student student){
        Card newCard = cardService4.createAndReturn(student);

    }

    public void updateStudent(Student student){
//        int id = student.getId();
//        Student updateStudent = studentRepository4.findById(id).get();
//        updateStudent = student;
//        studentRepository4.up
        studentRepository4.updateStudentDetails(student);


    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
        Student student = studentRepository4.findById(id).get();
        Card card = student.getCard();
        card.setCardStatus(CardStatus.DEACTIVATED);
        studentRepository4.deleteById(id);
    }
}
