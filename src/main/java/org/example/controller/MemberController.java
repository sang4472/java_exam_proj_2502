package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController {
  private Scanner sc;
  private List<Member> members;

  public MemberController(Scanner sc, List<Member> members) {
    this.sc = sc;
    this.members = members;
  }

  public void doJoin() {
    int id = members.size() + 1;
    String regDate = Util.getNotDateStr();

    String loginId = null;

    while ( true ) {
      System.out.printf("로그인 아이디 : ");
      loginId = sc.nextLine();

      if ( isJoinableLoginId(loginId) == false ) {
        System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
        continue;
      }
      break;
    }

    String loginPw = null;
    String loginPwConfirm = null;

    while ( true ) {
      System.out.printf("로그인 비번 : ");
      loginPw = sc.nextLine();
      System.out.printf("로그인 비번확인 : ");
      loginPwConfirm = sc.nextLine();

      if ( loginPw.equals(loginPwConfirm) == false ) {
        System.out.println("비밀번호를 다시 입력해주세요.");
        continue;
      }
      break;
    }

    System.out.printf("이름 : ");
    String name = sc.nextLine();
    Member member = new Member(id, regDate, loginId, loginPw, name);

    members.add(member);

    System.out.printf("%d번 회원이 생성되었습니다.\n", id);
  }

  private boolean isJoinableLoginId(String loginId) {
    int index = getMemberIndexByLoginId(loginId);

    if ( index == -1 ) {
      return true;
    }

    return false;
  }

  private int getMemberIndexByLoginId(String loginId) {
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