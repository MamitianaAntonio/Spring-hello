package com.antonio.hello_spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.hello_spring.model.Student;

@RestController
@RequestMapping("/student")
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @PostMapping
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> newStudents) {
        students.addAll(newStudents);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(students);
    }

    @GetMapping
    public String getStudents(@RequestHeader(value = "Accept", defaultValue = "text/plain") String acceptHeader) {
        if (acceptHeader.equals("text/plain")) {
            return students.stream()
                    .map(s -> s.getFirstname() + " " + s.getLastName())
                    .collect(Collectors.joining("\n"));
        } else {
            return "Format non supporté";
        }
    }
}
