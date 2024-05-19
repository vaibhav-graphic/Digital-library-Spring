package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CreateStudentRequest;
import com.example.demo.dtos.SearchStudentRequest;
import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	//create
	public Student create(CreateStudentRequest createStudentRequest) {
		
		Student student = studentRepository.findByContact(createStudentRequest.getContact());
		
		if(student != null) {
			return student;
		}
		
		String contactLength = createStudentRequest.getContact();
		
		if(contactLength.length() < 10 || contactLength.length() > 10) {
			return null;
		}
		
		if(contactLength.matches("[0-9]+") == false) {
			return null;
		}
		
		Student newStudent = createStudentRequest.to();
		studentRepository.save(newStudent);
		return newStudent;
	}
	
	//read
	public List<Student> getStudent(SearchStudentRequest searchStudentRequest) {
		boolean isValid = searchStudentRequest.isValid();
		
		if(isValid == false) {
			return null;
		}
		
		switch (searchStudentRequest.getSearchKey()) {
		case "name": {
			return studentRepository.findByName(searchStudentRequest.getSearchValue());
		}
		case "contact": {
			Student student = studentRepository.findByContact(searchStudentRequest.getSearchValue());
			List<Student> students = new ArrayList<>();
			students.add(student);
			return students;
		}
		case "id":{
			Student student =  studentRepository.findById(Integer.parseInt(searchStudentRequest.getSearchValue())).get();
			List<Student> students = new ArrayList<>();
			students.add(student);
			return students;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + searchStudentRequest.getSearchKey());
		}
	}
	
	public List<Student> getAll(){
		return studentRepository.findAll();
	}
	
	public Student get(int studentId) {
		return studentRepository.findById(studentId).get();
	}
	
	//update - name
	public Student updateStudentName(long studentContact, SearchStudentRequest searchStudentRequest) {
		String studentConatString = Long.toString(studentContact);
		Student student = studentRepository.findByContact(studentConatString);
		
		if(student == null) {
			return null;
		}
		
		boolean isValid = searchStudentRequest.isValid();
		
		if(isValid == false) {
			return null;
		}
		
		switch ( searchStudentRequest.getSearchKey()) {
		case "name": {
			int num = studentRepository.updateByContact(studentConatString, searchStudentRequest.getSearchValue());
			if(num > 0) {
				return student;
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " +  searchStudentRequest.getSearchKey());
		}
	}
	
	//delete
	public Student delete(Long studentContact) {
		String studentContactString = Long.toString(studentContact);
		Student student = studentRepository.findByContact(studentContactString);
		if(student == null) {
			return null;
		}
		int num = studentRepository.deleteByContact(studentContactString);
		
		if(num > 1) {
			return student;
		}
		return null;
	}
	
	
}
