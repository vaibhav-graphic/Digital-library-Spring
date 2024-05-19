package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CreateBookRequest;
import com.example.demo.dtos.SearchBookRequest;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.Student;
import com.example.demo.entities.enums.Genre;
import com.example.demo.repository.BookRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorService authorService;
	
	public Book createBook(CreateBookRequest createBookRequest) {
		Book book = createBookRequest.to();
		Author author = authorService.createOrGet(book.getMy_author());
		book.setMy_author(author);
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	//for name/authorName genre page id
	
	public List<Book> search(SearchBookRequest searchBookRequest) throws Exception{
		boolean isValidRequest = searchBookRequest.validate();
		
		if(!isValidRequest) {
			throw new Exception("Invalid Request");
		}
		
		switch (searchBookRequest.getSearchKey()) {
		case "name": 
			return bookRepository.findByName(searchBookRequest.getSearchValue());
		case "genre": 
			return bookRepository.findByGenre(Genre.valueOf(searchBookRequest.getSearchValue()));
		case "id":
			Book book = bookRepository.findById(Integer.parseInt(searchBookRequest.getSearchValue())).orElse(null);
            return Arrays.asList(book);
		default:
			throw new Exception("invalid search key");
		}
	}
	
	public boolean deleteBook(String bookName) {
		int deleteCount =  bookRepository.deleteByName(bookName);
		return deleteCount > 0;
	}
	
	public void assignBookToStudent(Book book,Student student) {
		bookRepository.assignBookToStudent(book.getId(), student);
	}
	
	public void unassignBookFromStudent(Book book) {
		bookRepository.unassignBook(book.getId());
	}
}
