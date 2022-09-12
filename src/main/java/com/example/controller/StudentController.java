package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.GetStudentsByBirthYearRequest;
import com.example.request.UpdateStudentRequest;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody CreateStudentRequest createStudentRequest){
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/getStudentsByName/{name}")
    public List<Student> getStudentsByName(@PathVariable String name){
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.updateStudent(updateStudentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/getStudentByNameAndBirthYear/{name}/{birthYear}")
    public List<Student> getStudentByNameAndBirthYear(@PathVariable String name, @PathVariable Integer birthYear){
        return studentService.getStudentByNameAndBirthYear(name, birthYear);
    }

    @GetMapping("/getStudentByNameOrBirthYear/{name}/{birthYear}")
    public List<Student> getStudentByNameOrBirthYear(@PathVariable String name, @PathVariable Integer birthYear){
        return studentService.getStudentByNameOrBirthYear(name, birthYear);
    }

    @GetMapping("/getStudentsByBirthYear")
    public List<Student> getStudentsByBirthYear(@RequestBody GetStudentsByBirthYearRequest getStudentsByBirthYearRequest){
        return studentService.getStudentsByBirthYear(getStudentsByBirthYearRequest);
    }

    @GetMapping("/getStudentsWithPagination")
    public List<Student> getStudentsWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
        return studentService.getStudentsWithPagination(pageNo, pageSize);
    }

    @GetMapping("/getStudentsWithSorting")
    public List<Student> getStudentsWithSorting(){
        return studentService.getStudentsWithSorting();
    }

    @GetMapping("/getStudentsByNameLike/{name}")
    public List<Student> getStudentsByNameLike(@PathVariable String name){
        return studentService.getStudentsByNameLike(name);
    }

    @GetMapping("/getStudentsByNameStartWith/{name}")
    public List<Student> getStudentsByNameStartWith(@PathVariable String name){
        return studentService.getStudentsByNameStartWith(name);
    }
}
