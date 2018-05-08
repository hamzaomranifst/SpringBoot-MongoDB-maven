package com.hamzaomranifst.springbootmongodbrestapi.controllers;

import java.util.List;

import javax.validation.Valid;

import com.hamzaomranifst.springbootmongodbrestapi.models.Student;
import com.hamzaomranifst.springbootmongodbrestapi.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        Sort sortByFirstNameDesc = new Sort(Sort.Direction.DESC, "firstName");
        return studentRepository.findAll(sortByFirstNameDesc);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping(value="/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Student student = studentRepository.findOne(id);
        if(student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else 
            return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping(value="/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id,
                                           @Valid @RequestBody Student student) {
        Student studentFound = studentRepository.findOne(id);
        if(studentFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentFound.setFirstName(student.getFirstName());
        studentFound.setLastName(student.getLastName());
        studentFound.setEmail(student.getEmail());
        studentFound.setAddress(student.getAddress());
        studentFound.setPhone(student.getPhone());

        Student updatedStudent = studentRepository.save(studentFound);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping(value="/students/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        studentRepository.delete(id);
    }
    
}