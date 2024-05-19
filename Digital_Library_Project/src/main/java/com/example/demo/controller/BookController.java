package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CreateBookRequest;
import com.example.demo.dtos.SearchBookRequest;
import com.example.demo.entities.Book;
import com.example.demo.service.BookService;



@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("")
	public Book createBook(@RequestBody CreateBookRequest createBookRequest ) {
		return bookService.createBook(createBookRequest);
	}

	@GetMapping("/all")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/search")
	public List<Book> getAllDetails(@RequestBody SearchBookRequest searchBookRequest) throws Exception{
		return bookService.search(searchBookRequest);
	}
	
	@DeleteMapping("")
	public String deleteBook(@RequestParam String bookName) {
		boolean have = bookService.deleteBook(bookName);
		if(have == false) {
			return "THERE IS NO BOOK WITH THIS NAME " + bookName;
		}
		return bookName + " ->  book delete succesfully ";
	}
}
