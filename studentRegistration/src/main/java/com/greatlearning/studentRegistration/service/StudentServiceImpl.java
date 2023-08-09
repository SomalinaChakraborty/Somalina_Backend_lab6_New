package com.greatlearning.studentRegistration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.studentRegistration.entity.Student;
import com.greatlearning.studentRegistration.repository.StudentsRepository;
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentsRepository repository;

	@Override
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	@Override
	public void save(Student student) {
		repository.save(student);
		
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Student findById(int id) {
		Optional<Student>  optStu=repository.findById(id);
		if(optStu.isPresent()) {
			return optStu.get();
		}else {
			throw new RuntimeException("Student not Present for id" +id);
		}
	
	}

}
