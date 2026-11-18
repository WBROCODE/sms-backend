package net.javaguides.sms.repository;

import net.javaguides.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository already has save, findById, findAll, deleteById
// no @Repository here, Spring already knows it
public interface StudentRepository extends JpaRepository<Student, Long> {

}
