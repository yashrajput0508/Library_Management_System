package com.example.app.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Member;
import com.example.app.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping
	public ResponseEntity<List<Member>> getallMembers(){
		
//		List<Member> members = this.memberService.getAllMembers();
//        List<EntityModel<Member>> memberResources = new ArrayList<>();
//
//        for (Member member : members) {
//            memberResources.add(EntityModel.of(member,
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).getMemberById(member.getId())).withSelfRel(),
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).updateMember(member, member.getId())).withRel("updateMember"),
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).deleteMember(member.getId())).withRel("deleteMember")
//            ));
//        }
//
//        return CollectionModel.of(memberResources,
//            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).getallMembers()).withSelfRel());

		List<Member> members = this.memberService.getAllMembers();
        return ResponseEntity.ok(members);
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<Member> getMemberById(@PathVariable int memberId) {
		Member member = this.memberService.getMemberById(memberId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping
	public ResponseEntity<String> addMember(@RequestBody Member member) {
		
		boolean added = this.memberService.addMember(member);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Member added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add member.");
        }
	}
	
	@PutMapping("/{memberId}")
	public ResponseEntity<Void> updateMember(@RequestBody Member member,@PathVariable int memberId) {
		this.memberService.updateMember(memberId, member);
        
		return ResponseEntity.ok().build();
        
	}
	
	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteMember(@PathVariable int memberId) {
		
		this.memberService.deleteMember(memberId);
		return ResponseEntity.ok().build();
	}
}
