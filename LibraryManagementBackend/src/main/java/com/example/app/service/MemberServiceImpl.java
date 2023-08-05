package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.model.Member;
import com.example.app.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return this.memberRepository.findAll();
	}

	@Override
	public boolean addMember(Member member) {
		// TODO Auto-generated method stub
		Optional<Member> oldmember = this.memberRepository.findById(member.getMemberId());

		if (oldmember.isEmpty()) {

			this.memberRepository.save(member);
			return true;
		}

		return false;
	}

	@Override
	public Member getMemberById(int memberId) {
		// TODO Auto-generated method stub
		Member member = this.memberRepository.findById(memberId).get();
		
		return member;
	}

	@Override
	public void updateMember(int memberId, Member member) {
		// TODO Auto-generated method stub
		this.deleteMember(memberId);
		this.addMember(member);
	}

	@Override
	public void deleteMember(int memberId) {
		// TODO Auto-generated method stub
		this.memberRepository.deleteById(memberId);
	}

}
