package com.mc.boot.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.boot.member.Member;

public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryExtension{

}
