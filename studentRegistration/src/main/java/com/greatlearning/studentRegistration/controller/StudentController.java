package com.greatlearning.studentRegistration.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentRegistration.entity.Student;
import com.greatlearning.studentRegistration.service.StudentService;


@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@RequestMapping("/list")
	public String getAllStudents(Model model) {
		List<Student> students=service.getAllStudents();
		model.addAttribute("students",students);
		return "stu_List";
	}
	
	@GetMapping("/new")
	public String addStudent(Model model) {
		Student student=new Student();
		model.addAttribute("student",student);
		return "add_stu";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editStudent(Model model ,@PathVariable(name="id") Integer id) {
		model.addAttribute("student",service.findById(id));
		return "edit_stu";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") int studentId) {
		service.deleteById(studentId);
		return "redirect:/students/list";
		
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("student") Student student ){
		service.save(student);
		return "redirect:/students/list";
		
	}
	@PostMapping("/save/{id}")
	public String registerStudent(@PathVariable(name="id") Integer id ,@ModelAttribute(name="student") Student student ) {
		student.setStudentId(id);
		service.save(student);
		return "redirect:/students/list";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("students/403");
		return model;

	}
}
