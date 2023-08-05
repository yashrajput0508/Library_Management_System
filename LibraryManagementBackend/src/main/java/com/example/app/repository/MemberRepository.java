package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
