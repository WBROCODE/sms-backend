package net.javaguides.sms.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.sms.aspect.LogMe;
import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.exception.ResourceNotFoundException;
import net.javaguides.sms.mapper.StudentMapper;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    // constructor injection, Lombok makes it
    private StudentRepository studentRepository;

//    public StudentServiceImpl(StudentRepository studentRepository){
//        this.studentRepository = studentRepository;
//    }

    // setter DI
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

//    // filed DI
//    @Autowired
//    private StudentRepository studentRepository;
    // treat it as a transction
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }
    @Transactional
    @Override
    public List<StudentDto> createStudents(List<StudentDto> studentDtos) {
        List<StudentDto> saved = new ArrayList<>();
        for (StudentDto dto : studentDtos) {
            Student student = studentRepository.save(StudentMapper.mapToStudent(dto));
            saved.add(StudentMapper.mapToStudentDto(student));
        }
        return saved;
    }

    @LogMe
    @Override
    public StudentDto getStudentById(Long studentId) {
        // if id not found, throw exception
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student is not exists with given id : " + studentId));
        return StudentMapper.mapToStudentDto(student);
    }



    @Override
    public List<StudentDto> getAllStudents(String sortBy) {
        List<Student> students = studentRepository.findAll(Sort.by(sortBy));
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student is not exists with given id : " + studentId));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        Student updatedStudentObj = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student is not exists with given id : " + studentId));
        studentRepository.deleteById(studentId);
    }



    @PostConstruct
    public void init() {
        System.out.println(">>> StudentServiceImpl ready. Repository = " + studentRepository);
    }

    @PreDestroy
    public void cleanup() {
        System.out.println(">>> StudentServiceImpl is being destroyed");
    }
}
