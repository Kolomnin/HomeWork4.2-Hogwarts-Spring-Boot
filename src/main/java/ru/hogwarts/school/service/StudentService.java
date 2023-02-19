package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    public final Object flag = new Object();
    int count = 0;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Request to create student {}", student);
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Request to getting student {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Request to edit student {}", student);
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(long id) {
        logger.info("Request to delete student {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Request to getting students");
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age){
        logger.info("Request to getting student by id {}", age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getStudentsByAgeBetween(int ageMin, int ageMax) {
        logger.info("Request to getting age students between from {}, to {}", ageMin, ageMax);
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Collection<Student> getStudents(Long id) {
        logger.info("Request to getting students by id {}", id);
        return studentRepository.findStudentsById(id);
    }

    public Integer getAllStudents() {
        logger.info("Request to getting number of students");
        return studentRepository.getAllStudents();
    }

    public Double getAverageAge() {
        logger.info("Request to getting average age of students");
        return studentRepository.getAverageAge( );
    }

    public List<Student> getLastStudents() {
        logger.info("Request to getting last 5 students");
        return studentRepository.getLastStudent();
    }

    public List<Student> getStudentsByName(String name) {
        logger.info("Request to getting student by name {}", name);
        return studentRepository.findStudentsByNameIgnoreCase(name);
    }

    public List<String> filterStudentsByNameBeginsWithLetterA() {
        logger.info("Request to getting all students whose name begins with a letter A");
        return studentRepository.findAll()
                .stream()
                .filter(name -> (name.getName().startsWith("А")))
                .map(name -> name.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Integer timeRequest() {
        long start = System.currentTimeMillis();
        int num = 1_000_000;
        int result = IntStream
                .range(1, num + 1)
                .sum();

        long timeRequest = System.currentTimeMillis() - start;
        logger.info("Request to getting time request: " + timeRequest + "ms");
        return result;
    }

    public void getStudentsNameThreadOne() {
        logger.info("Request to getting test thread");
        List<String> studentList = getList();

        System.out.println(Thread.currentThread().getName() + " " + studentList.get(0));
        System.out.println(Thread.currentThread().getName() + " " + studentList.get(1));

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(2));
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(3));
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(4));
            System.out.println(Thread.currentThread().getName() + " " + studentList.get(5));
        });
        t1.start();
        t2.start();
    }

    public void getStudentsNameThreadTwo() {
        logger.info("Request to getting test synchronized thread");
        List<String> studentList = getList();

        printStudentName(studentList.get(0));
        printStudentName(studentList.get(1));

        Thread t1 = new Thread(() -> {
            printStudentName(studentList.get(2));
            printStudentName(studentList.get(3));
        });

        Thread t2 = new Thread(() -> {
            printStudentName(studentList.get(4));
            printStudentName(studentList.get(5));
        });

        t1.start();
        t2.start();
    }

    public void printStudentName(String student) {
        synchronized (flag) {
            System.out.println(Thread.currentThread().getName() + ": " + student + " - count: " + count);
            count++;
        }
    }

    private List<String> getList() {
        return studentRepository
                .findAll()
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }
}
