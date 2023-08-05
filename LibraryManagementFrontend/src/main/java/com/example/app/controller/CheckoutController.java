package com.example.app.controller;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.app.model.Author;
import com.example.app.model.Book;
import com.example.app.model.Checkout;
import com.example.app.model.Member;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;
import com.example.app.service.BookService;
import com.example.app.service.CheckoutService;
import com.example.app.service.MemberService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class CheckoutController {

	private CheckoutService checkoutService;
	private BookService bookService;
	private MemberService memberService;
	private User user;
	
	public CheckoutController(CheckoutService checkoutService, BookService bookService
			, MemberService memberService,User user) {
		this.checkoutService = checkoutService;
		this.bookService = bookService;
		this.memberService = memberService;
		this.user = user;
	}
	
	@RequestMapping("/checkoutLists")
	public String checkoutLists(@RequestParam(name = "page", defaultValue = "1") int page,ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		int pageSize = 3; // Number of records to display per page

        PaginationResult<Checkout> paginationResult = checkoutService.getPaginatedCheckoutLists(page, pageSize);

		map.put("paginationResult", paginationResult);
		return "checkouts/allCheckoutLists";
	}
	
	@RequestMapping(value = "/addcheckout", method = RequestMethod.GET)
	public String addCheckoutGet(ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		Checkout checkout = new Checkout();

		map.addAttribute("checkout", checkout);
		
		List<Book> book = bookService.getAllBooks();
		List<Member> member = memberService.getAllMembers();
		
		Predicate<? super Book> predicate = (oldbook)->oldbook.isAvailability()==true;
		book = book.stream().filter(predicate).toList();
		
		map.addAttribute("books",book);
		map.addAttribute("members",member);
		
		return "checkouts/addCheckout";
	}

	@RequestMapping(value = "/addcheckout", method = RequestMethod.POST)
	public String addCheckoutPost(ModelMap map, @Valid Checkout checkout, BindingResult bindingResult) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		if (bindingResult.hasErrors()) {
		
			List<Book> book = bookService.getAllBooks();
			List<Member> member = memberService.getAllMembers();
		
			Predicate<? super Book> predicate = (oldbook)->oldbook.isAvailability()==true;
			book = book.stream().filter(predicate).toList();
			
			map.addAttribute("books",book);
			map.addAttribute("members",member);
			
			return "checkouts/addCheckout";
		}

		boolean isSuccess = this.checkoutService.addCheckout(checkout);
		
		if (!isSuccess) {
			
			List<Book> book = bookService.getAllBooks();
			List<Member> member = memberService.getAllMembers();
		
			Predicate<? super Book> predicate = (oldbook)->oldbook.isAvailability()==true;
			book = book.stream().filter(predicate).toList();
			
			map.addAttribute("books",book);
			map.addAttribute("members",member);
			
			map.addAttribute("customError", "Checkout Id Already Exist");
			return "checkouts/addCheckout";
		}
		
		Book book = bookService.getBookById(checkout.getBook().getBookId());
		Member member = memberService.getMemberById(checkout.getMember().getMemberId());

		checkout.setBook(book);
		checkout.setMember(member);
		
		if(checkout.getReturnDate()==null) {
			this.bookService.updateAvailability(book.getBookId(), false);
		}else {
			this.bookService.updateAvailability(book.getBookId(), true);
		}
		
		return "redirect:/checkoutLists";
	}
	
	@RequestMapping(value = "/editcheckout", method = RequestMethod.GET)
	public String editCheckoutGet(ModelMap map, @RequestParam int checkoutId) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		Checkout checkout = this.checkoutService.getCheckoutById(checkoutId);

		map.addAttribute("checkout", checkout);

		List<Book> book = bookService.getAllBooks();
		List<Member> member = memberService.getAllMembers();
		
		Predicate<? super Book> predicate = (oldbook)->(oldbook.isAvailability()==true 
				|| oldbook.getBookId()==checkout.getBook().getBookId());
		
		book = book.stream().filter(predicate).toList();
		
		map.addAttribute("books",book);
		map.addAttribute("members",member);

		return "checkouts/editCheckout";
	}

	@RequestMapping(value = "/editcheckout", method = RequestMethod.POST)
	public String editCheckoutPost(@RequestParam int checkoutId, @Valid Checkout checkout, BindingResult bindingResult, ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		if (bindingResult.hasErrors()) {
			
			List<Book> book = bookService.getAllBooks();
			List<Member> member = memberService.getAllMembers();
		
			Predicate<? super Book> predicate = (oldbook)->(oldbook.isAvailability()==true 
					|| oldbook.getBookId()==checkout.getBook().getBookId());
			
			map.addAttribute("books",book);
			map.addAttribute("members",member);
			
			return "checkouts/editCheckout";
		}
		
		Book book = this.checkoutService.getCheckoutById(checkoutId).getBook();
		this.bookService.updateAvailability(book.getBookId(), true);

		this.checkoutService.updateCheckout(checkoutId, checkout);
		
		book = this.checkoutService.getCheckoutById(checkout.getCheckoutId()).getBook();
		
		if(checkout.getReturnDate()==null) {
			this.bookService.updateAvailability(book.getBookId(), false);
		}else {
			this.bookService.updateAvailability(book.getBookId(), true);
		}
		
		return "redirect:/checkoutLists";
	}

	@RequestMapping(value = "/deletecheckout", method = RequestMethod.GET)
	public String deleteCheckout(@RequestParam int checkoutId,ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		this.checkoutService.deleteCheckout(checkoutId);

		return "redirect:/checkoutLists";
	}
	
	public boolean isAuthenticated(ModelMap map) {
		if(this.user.getUsername() != null) {
			return true;
		}
		
		return false;
	}
}
