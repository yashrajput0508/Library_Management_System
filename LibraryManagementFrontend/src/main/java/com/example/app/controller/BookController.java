package com.example.app.controller;

import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.app.service.BookService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class BookController {

	private BookService bookService;
	private AuthorService authorService;
	private User user;

	Logger logger = LoggerFactory.getLogger(BookController.class);

	public BookController(BookService bookService, AuthorService authorService,User user) {
		this.user = user;
		this.bookService = bookService;
		this.authorService = authorService;
	}

	@RequestMapping("/bookLists")
	public String bookLists(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap map) {
		if (!isAuthenticated(map)) {
			return "redirect:/";
		}

		int pageSize = 3; // Number of records to display per page

		PaginationResult<Book> paginationResult = bookService.getPaginatedBooks(page, pageSize);

		paginationResult.getitems()
				.forEach((book) -> book.setStringImage(Base64.getEncoder().encodeToString(book.getBase64Image())));

		map.put("paginationResult", paginationResult);
		return "books/allBooks";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addBookGet(ModelMap map) {
		if (!isAuthenticated(map)) {
			return "redirect:/";
		}
		Book book = new Book();
		List<Author> authors = this.authorService.getAllAuthors();

		map.addAttribute("book", book);
		map.put("authors", authors);

		return "books/addBook";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addBookPost(ModelMap map, @Valid Book book, BindingResult bindingResult) {
		if (!isAuthenticated(map)) {
			return "redirect:/";
		}
		List<Author> authors = this.authorService.getAllAuthors();
		map.put("authors", authors);

		if (bindingResult.hasErrors()) {
			return "books/addBook";
		}

		boolean isSuccess = this.bookService.addBook(book);

		Author author = authorService.getAuthorById(book.getAuthor().getAuthorId());

		book.setAuthor(author); // Set the retrieved author to the Book object

		if (!isSuccess) {
			map.addAttribute("customError", "Book Id Already Exist");
			return "books/addBook";
		}

		return "redirect:/bookLists";
	}

	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String editBookGet(ModelMap map, @RequestParam int bookId) {
		if (!isAuthenticated(map)) {
			return "redirect:/";
		}
		Book book = this.bookService.getBookById(bookId);
		List<Author> authors = this.authorService.getAllAuthors();

		map.addAttribute("book", book);
		map.put("authors", authors);

		return "books/editBook";
	}

	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public String editBookPost(@RequestParam int bookId, @Valid Book book, BindingResult bindingResult, ModelMap map) {
		if (!isAuthenticated(map)) {
			return "redirect:/";
		}
		if (bindingResult.hasErrors()) {

			List<Author> authors = this.authorService.getAllAuthors();

			map.put("authors", authors);

			return "books/editBook";
		}

		book.setAuthor(this.authorService.getAuthorById(book.getAuthor().getAuthorId()));

		this.bookService.updateBook(bookId, book);

		return "redirect:/bookLists";
	}

	@RequestMapping(value = "/deletebook", method = RequestMethod.GET)
	public String deleteBook(@RequestParam int bookId,ModelMap map) {
		if (!isAuthenticated(map)) {
			return "redirect:/";
		}
		this.bookService.deleteBook(bookId);

		return "redirect:/bookLists";
	}

	public boolean isAuthenticated(ModelMap map) {
		if(this.user.getUsername() != null) {
			return true;
		}
		
		return false;
	}
}
