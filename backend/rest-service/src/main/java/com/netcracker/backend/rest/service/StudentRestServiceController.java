package com.netcracker.backend.rest.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.StudentService;
import com.netcracker.entities.Student;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by ilkh0715 on 27.10.2016.
 */

@RestController
@RequestMapping(value = "/api")
public class StudentRestServiceController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public Collection getStudents() throws ServException {

        return studentService.getAllStudent();
    }

    @GetMapping(value = "/student/{id}")
    public Student getStudentById(@PathVariable Integer id) throws ServException {
        return studentService.getStudentById(id);
    }

    @PostMapping(value = "/save-student")
    ResponseEntity<Student> addStudent(@RequestBody Student student) throws ServException {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity(savedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("No Student found for ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return new ResponseEntity(updatedStudent, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not update student with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
