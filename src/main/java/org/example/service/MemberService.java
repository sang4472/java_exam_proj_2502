package org.example.service;

import org.example.container.Container;
import org.example.dto.Member;
import org.example.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public int getMemberIndexByLoginId(String loginId) {
    return memberRepository.getMemberIndexByLoginId(loginId);
  }

  public void join(Member member) {
    memberRepository.add(member);
  }
}