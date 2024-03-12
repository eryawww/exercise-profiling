package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        return studentCourseRepository.findAll();
    }

    public Optional<Student> findStudentWithHighestGpa() {
        List<Sort.Order> sort = new ArrayList<>();
        sort.add(new Sort.Order(Sort.Direction.DESC, "gpa"));
        // direct sort from the database
        List<Student> students = studentRepository.findAll(Sort.by(sort));
//        List<Student> students = studentRepository.findAll();
//        Student highestGpaStudent = null;
//        double highestGpa = 0.0;
//        for (Student student : students) {
//            if (student.getGpa() > highestGpa) {
//                highestGpa = student.getGpa();
//                highestGpaStudent = student;
//            }
//        }
//        return Optional.ofNullable(highestGpaStudent);
        Student highestGpa = students.get(0);
        return Optional.ofNullable(highestGpa);
    }

    public String joinStudentNames() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
//        String result = "";
//        for (Student student : students) {
//            result += student.getName() + ", ";
//        }
//        return result.substring(0, result.length() - 2);
    }
}

