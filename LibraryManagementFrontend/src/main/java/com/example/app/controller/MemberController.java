package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.app.model.Author;
import com.example.app.model.Member;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;
import com.example.app.service.MemberService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class MemberController {

	private MemberService memberService;
	private User user;
	
	public MemberController(MemberService memberService,User user) {
		this.memberService = memberService;
		this.user = user;
	}
	
	@RequestMapping("/memberLists")
	public String memberLists(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		int pageSize = 3; // Number of records to display per page

		
		PaginationResult<Member> paginationResult = this.memberService.getPaginatedMembers(page, pageSize);

		map.put("paginationResult", paginationResult);
		
		return "members/allMembers";
	}
	
	@RequestMapping(value="/addmember",method = RequestMethod.GET)
	public String addMemberGet(ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		Member member = new Member();
		
		map.addAttribute("member",member);
		
		return "members/addMember";
	}
	
	@RequestMapping(value="/addmember",method = RequestMethod.POST)
	public String addMemberPost(ModelMap map,@Valid Member member, BindingResult bindingResult) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		if(bindingResult.hasErrors()) {
			return "members/addMember";
		}
		
		boolean isSuccess = this.memberService.addMember(member);
		
		if(!isSuccess) {
			map.addAttribute("customError", "Member Id Already Exist");
            return "members/addMember";
		}
		
		return "redirect:/memberLists";
	}
	
	@RequestMapping(value="/editmember", method = RequestMethod.GET)
	public String editMemberGet(ModelMap map,@RequestParam int memberId) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		Member member = this.memberService.getMemberById(memberId);
		
		map.addAttribute("member",member);
		
		return "members/editMember";
	}
	
	@RequestMapping(value="/editmember", method = RequestMethod.POST)
	public String editAuthorPost(@RequestParam int memberId,@Valid Member member,BindingResult bindingResult,ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		if(bindingResult.hasErrors()) {
			return "members/editMember";
		}
		
		this.memberService.updateMember(memberId,member);
		
		return "redirect:/memberLists";
	}
	
	@RequestMapping(value="/deletemember", method = RequestMethod.GET)
	public String deleteMember(@RequestParam int memberId,ModelMap map) {
		if(!isAuthenticated(map)) {
			return "redirect:/";
		}
		
		this.memberService.deleteMember(memberId);
		
		return "redirect:/memberLists"; 
	}
	
	public boolean isAuthenticated(ModelMap map) {
		if(this.user.getUsername() != null) {
			return true;
		}
		
		return false;
	}
}
