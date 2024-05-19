package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Author;

import jakarta.transaction.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Query(value = "select a from Author a where a.email = ?1")
	Author findByEmail(String email);
	
	@Query(value = "select a from Author a where a.name = ?1")
	public List<Author> findByName(String name);
	
	@Query(value = "select a from Author a where a.country = ?1")
	public List<Author> findByCountry(String country);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Author a WHERE a.email = :email")
	int deleteByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Author a SET a.name = :newName WHERE a.email = :email")
	int updateNameByEmail(String email,String newName);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Author a SET a.country = :newCountry WHERE a.email = :email")
	int updateCountryByEmail(String email,String newCountry);
	
	
}