package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query(value = "SELECT s FROM Student s WHERE s.contact = ?1")
	public Student findByContact(String contact);
	
	@Query(value = "SELECT s FROM Student s WHERE s.name = ?1")
	public List<Student> findByName(String name);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Student s WHERE s.contact = :contact")
	public int deleteByContact(String contact);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Student s SET s.name = :newName WHERE s.contact = :contact")
	public int updateByContact(String contact, String newName);

}
