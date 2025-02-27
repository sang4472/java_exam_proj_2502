package org.example.repository;

import org.example.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository extends Repository {
  public List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();
  }

  public void add(Member member) {
    members.add(member);
    lastId++;
  }
}