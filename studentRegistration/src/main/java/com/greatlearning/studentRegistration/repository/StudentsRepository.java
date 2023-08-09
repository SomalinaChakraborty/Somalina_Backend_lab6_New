package com.greatlearning.studentRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.studentRegistration.entity.Student;

public interface StudentsRepository extends JpaRepository<Student, Integer> {

}
