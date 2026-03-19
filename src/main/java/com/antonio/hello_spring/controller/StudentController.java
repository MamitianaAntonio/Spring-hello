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
    public ResponseEntity<?> getStudents(
            @RequestHeader(value = "Accept", required = false) String acceptHeader) {
        if (acceptHeader == null || acceptHeader.isBlank()) {
            return ResponseEntity.badRequest().body("Header 'Accept' est obligatoire !");
        }

        String header = acceptHeader.toLowerCase().trim();

        // acceptHeader = text/plain
        if (header.contains("text/plain")) {
            String result = students.stream()
                    .map(s -> s.getFirstname() + " " + s.getLastName())
                    .collect(Collectors.joining("\n"));
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(result);
        }

        // acceptHeader = application/json
        if (header.contains("application/json")) {
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(students);
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body("Format non supporté : " + acceptHeader);
    }
}
