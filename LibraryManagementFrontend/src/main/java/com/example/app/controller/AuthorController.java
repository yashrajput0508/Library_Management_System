package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.app.model.Author;
import com.example.app.model.Book;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;
import com.example.app.service.AuthorService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class AuthorController {
	
	private AuthorService authorService;
	private User user;
	
	private AuthorController(AuthorService authorService,User user) {
		this.user = user;
		this.authorService = authorService;
	}
	
	@RequestMapping("/authorLists")
	public String authorLists(@RequestParam(name = "page", defaultValue = "1") int page,ModelMap map) {
		
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		
		int pageSize = 3; // Number of records to display per page

        PaginationResult<Author> paginationResult = authorService.getPaginatedAuthors(page, pageSize);

		map.put("paginationResult", paginationResult);
		
		return "authors/allAuthors";
	}
	
	@RequestMapping(value="/addauthor",method = RequestMethod.GET)
	public String addAuthorGet(ModelMap map) {
		
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		
		Author author = new Author();
		
		map.addAttribute("author",author);
		
		return "authors/addAuthor";
	}
	
	@RequestMapping(value="/addauthor",method = RequestMethod.POST)
	public String addAuthorPost(ModelMap map,@Valid Author author, BindingResult bindingResult) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		
		if(bindingResult.hasErrors()) {
			return "authors/addAuthor";
		}
		
		boolean isSuccess = this.authorService.addAuthor(author);
		
		if(!isSuccess) {
			map.addAttribute("customError", "Author Id Already Exist");
            return "authors/addAuthor";
		}
		
		return "redirect:/authorLists";
	}
	
	@RequestMapping(value="/editauthor", method = RequestMethod.GET)
	public String editAuthorGet(ModelMap map,@RequestParam int authorId) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		Author author = this.authorService.getAuthorById(authorId);
		
		map.addAttribute("author",author);
		
		return "authors/editAuthor";
	}
	
	@RequestMapping(value="/editauthor", method = RequestMethod.POST)
	public String editAuthorPost(@RequestParam int authorId,@Valid Author author,BindingResult bindingResult,ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		
		if(bindingResult.hasErrors()) {
			return "authors/editAuthor";
		}
		
		this.authorService.updateAuthor(authorId,author);
		
		return "redirect:/authorLists";
	}
	
	@RequestMapping(value="/deleteauthor", method = RequestMethod.GET)
	public String deleteAuthor(@RequestParam int authorId,ModelMap map) {
		
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		
		this.authorService.deleteAuthor(authorId);
		
		return "redirect:/authorLists"; 
	}
	
	public boolean isAuthenticated(ModelMap map) {
		if(this.user.getUsername() != null) {
			return true;
		}
		
		return false;
	}
}


