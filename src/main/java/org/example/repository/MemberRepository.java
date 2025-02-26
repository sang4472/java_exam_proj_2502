package org.example.repository;

import org.example.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  public List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();
  }
}