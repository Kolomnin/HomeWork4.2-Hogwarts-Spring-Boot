package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;}


    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty ) {
        return facultyService.creatFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty getFaculties = facultyService.getFaculty(id);
        if (getFaculties == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getFaculties);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(faculty);
        if (updateFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.deleteFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/filter/{color}")
    public List<Faculty> getColor(@PathVariable String color) {
        return facultyService.getColor(color);
    }
}
