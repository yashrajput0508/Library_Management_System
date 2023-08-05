package com.example.app.service;

import java.util.List;

import com.example.app.model.Member;
import com.example.app.model.PaginationResult;

public interface MemberService {
	
	public List<Member> getAllMembers();
	
	public boolean addMember(Member member);
	
	public Member getMemberById(int memberId);
	
	public void updateMember(int memberId,Member member);
	
	public void deleteMember(int memberId);
	
	public PaginationResult<Member> getPaginatedMembers(int currentPage, int pageSize);
}
