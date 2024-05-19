package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CreateStudentRequest;
import com.example.demo.dtos.SearchStudentRequest;
import com.example.demo.entities.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	
	@PostMapping("")
	public Student create(@RequestBody CreateStudentRequest createStudentRequest) {
		return studentService.create(createStudentRequest);
	}
	
	@GetMapping("/all")
	public List<Student> getAll(){
		return studentService.getAll();
	}
	@GetMapping("/search")
	public List<Student> get(@RequestBody SearchStudentRequest searchStudentRequest ) {
		
		return studentService.getStudent(searchStudentRequest);
	}
	
	@PutMapping("/{studentContact}")
	public Student upateStudentName(@PathVariable long studentContact, @RequestBody SearchStudentRequest searchStudentRequest){
		return studentService.updateStudentName(studentContact,searchStudentRequest);
	}
	
	@DeleteMapping("")
	public Student delete(@RequestParam long studentContact) {
		return studentService.delete(studentContact);
	}
}
