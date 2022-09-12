package com.example.service;

import com.example.entity.Department;
import com.example.entity.IsLearningRelation;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.DepartmentRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.GetStudentsByBirthYearRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public Student createStudent(CreateStudentRequest createStudentRequest) {

        // Create department Node
        Department department = new Department();
        department.setDepName(createStudentRequest.getDepartment().getDepName());

        departmentRepository.save(department);

        List<IsLearningRelation> isLearningRelations = new ArrayList<>();


        // create subject nodes & fill relations
        if(createStudentRequest.getSubjectList() != null){

            for(CreateSubjectRequest createSub :
                    createStudentRequest.getSubjectList()){

                Subject subject = new Subject();
                subject.setSubName(createSub.getSubjectName());

                subjectRepository.save(subject);
                IsLearningRelation relation = new IsLearningRelation();
                relation.setMarks(createSub.getMarks());
                relation.setSubject(subject);
                isLearningRelations.add(relation);
            }
        }

        // create student node and put relations
        Student student = new Student();
        student.setName(createStudentRequest.getName());
        student.setCountry(createStudentRequest.getCountry());
        student.setBirthYear(createStudentRequest.getBirthYear());

        // student to department relation
        student.setDepartment(department);
        // student to subject relation
        student.setIsLearningRelations(isLearningRelations);

        studentRepository.save(student);
        return student;
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        student.setName(updateStudentRequest.getName());
        student.setCountry(updateStudentRequest.getCountry());
        student.setBirthYear(updateStudentRequest.getBirthYear());
        studentRepository.save(student);
        return student;
    }

    public String deleteStudent(long id) {
        studentRepository.deleteById(id);
        return "Student Deleted";
    }

    public List<Student> getStudentByNameAndBirthYear(String name, Integer birthYear) {
        return studentRepository.findByNameAndBirthYear(name, birthYear);
        //return studentRepository.getByNameAndBirthYear(name, birthYear);
    }

    public List<Student> getStudentByNameOrBirthYear(String name, Integer birthYear) {
        return studentRepository.findByNameOrBirthYear(name, birthYear);
    }

    public List<Student> getStudentsByBirthYear(GetStudentsByBirthYearRequest getStudentsByBirthYearRequest) {
        return studentRepository.findByBirthYearIn(getStudentsByBirthYearRequest.getBirthYearList());
    }

    public List<Student> getStudentsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getStudentsWithSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name", "country");

        return studentRepository.findAll(sort);
    }

    public List<Student> getStudentsByNameLike(String name) {

        return studentRepository.findByNameLike(name);
    }

    public List<Student> getStudentsByNameStartWith(String name) {

        return studentRepository.findByNameStartsWith(name);
    }
}
