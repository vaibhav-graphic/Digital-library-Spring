package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CreateAuthorRequest;
import com.example.demo.dtos.SearchAuthorRequest;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@Service
public class AuthorService {
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	public Author createOrGet(Author author) {
		Author authorFromDb = authorRepository.findByEmail(author.getEmail());
		
		if(authorFromDb != null) {
			return authorFromDb;
		}
		
		return authorRepository.save(author);
	}
	
	public List<Author> getAllAuthors(){
		return authorRepository.findAll();
	}
	
	//create 
	public Author createAuthor(CreateAuthorRequest createAuthorRequest) {
		Author author =  createAuthorRequest.to();
		Author check =  authorRepository.findByEmail(author.getEmail());
		
		if(check != null) {
			return author;
		}
		authorRepository.save(author);
		return author;
	}
	
	//reading for name,email,country
	public List<Author> search(SearchAuthorRequest searchAuthorRequest) throws Exception{
		boolean isVaild = searchAuthorRequest.isValid();
		
		if(!isVaild) {
		throw new Exception("INVALID AUTHOR DETAILS");
		}
		
		switch (searchAuthorRequest.getSearchKey()) {
		case "name": {
			return authorRepository.findByName(searchAuthorRequest.getSearchValue());
		}
		case "country" : {
			return authorRepository.findByCountry(searchAuthorRequest.getSearchValue());
		}
		case "email" : {
			Author author =  authorRepository.findByEmail(searchAuthorRequest.getSearchValue());
			return Arrays.asList(author);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + searchAuthorRequest.getSearchKey());
		}
	}
	
	//update name,country
	
	public boolean updateAuthorDeatil(String authorEmail,SearchAuthorRequest searchAuthorRequest)throws Exception {
		
		Author author = authorRepository.findByEmail(authorEmail);
		
		if(author == null) {
			return false;
		}
		
		boolean isValid = searchAuthorRequest.isValid();
		
		if(isValid == false) {
			return false;
		}
		
		boolean check = false;
		
		switch (searchAuthorRequest.getSearchKey()) {
		case "name": {
			int num = authorRepository.updateNameByEmail(authorEmail, searchAuthorRequest.getSearchValue());
			if(num > 0) {
				check = true;
			}
		}
		case "country" : {
			int num = authorRepository.updateCountryByEmail(authorEmail, searchAuthorRequest.getSearchValue());
			if(num > 0) {
				check = true;
			}
		}
		default:
			if(check == true) {
				return true;
			}
			throw new IllegalArgumentException("Unexpected value: " + searchAuthorRequest.getSearchKey());
		}
	}
	
	//delete author
	public boolean deleteAuthor(String authorEmail){
		
		Author author = authorRepository.findByEmail(authorEmail);
		
		if(author == null) {
			return false;
		}
		
		List<Book> bookCollectionOfAuthor = author.getBookList();
		
		for(Book book : bookCollectionOfAuthor) {
			book.setMy_author(null);
			int num = bookRepository.deleteByName(book.getName()); 
			num--;
		}
		
		int deleteCount =  authorRepository.deleteByEmail(authorEmail);
		
		if(deleteCount > 0) {
			return true;
		}
		return false;
	}
}