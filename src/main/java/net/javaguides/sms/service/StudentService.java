package net.javaguides.sms.service;

import net.javaguides.sms.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    List<StudentDto> createStudents(List<StudentDto> studentDtos);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents(String sortBy);

    StudentDto updateStudent(Long studentId, StudentDto updatedStudent);

    void deleteStudent(Long studentId);
}
