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

import com.example.demo.dtos.CreateAuthorRequest;
import com.example.demo.dtos.SearchAuthorRequest;
import com.example.demo.entities.Author;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@PostMapping("")
	public Author createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest){
		return authorService.createAuthor(createAuthorRequest);
	}
	
	@GetMapping("/all")
	public List<Author> getAllAuthors(){
		return authorService.getAllAuthors();
	}
	
	@GetMapping("/search")
	public List<Author> search(@RequestBody SearchAuthorRequest searchAuthorRequest) throws Exception{
		return authorService.search(searchAuthorRequest);
	}
	
	@PutMapping("/{authorEmail}")
	public String updateAuthorDetails(@PathVariable String authorEmail, @RequestBody SearchAuthorRequest searchAuthorRequest) throws Exception {
		boolean have = authorService.updateAuthorDeatil(authorEmail,searchAuthorRequest);
		
		if(have == false) {
			return "THERE IS NO AUTHOR PRESENT WITH THIS Email -> " + authorEmail;
		}
		
		return authorEmail + " -> UPDATE THE AUTHOR SUCCESFULLY";
	}
	
	@DeleteMapping("")
	public String deleteAuthor(@RequestParam String authorEmail) {
		boolean haveAuthor = authorService.deleteAuthor(authorEmail);
		
		if(haveAuthor == false) {
			return "THERE IS NO AUTHOR PRESENT WITH THIS Email -> " + authorEmail;
		}
		
		return authorEmail + " -> DELETE THE AUTHOR SUCCESFULLY";
	}
	
}
