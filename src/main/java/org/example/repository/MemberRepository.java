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

  public Member getMemberByLoginId(String loginId) {
    int index = getMemberIndexByLoginId(loginId);

    if ( index == -1 ) {
      return null;
    }

    return members.get(index);
  }

  public int getMemberIndexByLoginId(String loginId) {
    int i = 0;

    for ( Member member : members ) {
      if ( member.loginId.equals(loginId) ) {
        return i;
      }
      i++;
    }

    return -1;
  }
}