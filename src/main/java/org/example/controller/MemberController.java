package org.example.controller;

import org.example.container.Container;
import org.example.dto.Member;
import org.example.service.MemberService;
import org.example.util.Util;

import java.util.Scanner;

public class MemberController extends Controller {
  private Scanner sc;
  private String cmd;
  private String actionMethodName;
  private MemberService memberService;

  public MemberController(Scanner sc) {
    this.sc = sc;
    memberService = Container.memberService;
  }

  public void makeTestData() {
    System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

    Container.memberRepository.add(new Member(Container.memberRepository.getNewId(), Util.getNotDateStr(), "admin", "admin", " 관리자"));
    Container.memberRepository.add(new Member(Container.memberRepository.getNewId(), Util.getNotDateStr(), "user1", "user1", " 유저1"));
    Container.memberRepository.add(new Member(Container.memberRepository.getNewId(), Util.getNotDateStr(), "user2", "user2", " 유저2"));
  }

  public void doAction(String cmd, String actionMethodName) {
    this.cmd = cmd;
    this.actionMethodName = actionMethodName;

    switch (actionMethodName) {
      case "join":
        doJoin();
        break;
      case "login":
        doLogin();
        break;
      case "logout":
        doLogout();
        break;
      default:
        System.out.println("존재하지 않는 명령어입니다.");
        break;
    }
  }

  private void doLogout() {
    loginedMember = null;
    System.out.println("로그아웃 되었습니다.");
  }

  private void doLogin() {
    System.out.printf("로그인 아이디 : ");
    String loginId = sc.nextLine();
    System.out.printf("로그인 비번 : ");
    String loginPw = sc.nextLine();

    Member member = memberService.getMemberByLoginId(loginId);

    if ( member == null ) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }

    if ( member.loginPw.equals(loginPw) == false ) {
      System.out.println("비밀번호가 맞지 않습니다.");
      return;
    }

    loginedMember = member;

    System.out.printf("로그인 성공! %s님 환영합니다!\n", loginedMember.name);
  }

  public void doJoin() {
    int id = Container.memberRepository.getNewId();
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

    memberService.join(member);

    System.out.printf("%d번 회원이 생성되었습니다.\n", id);
  }

  private boolean isJoinableLoginId(String loginId) {
    int index = memberService.getMemberIndexByLoginId(loginId);

    if ( index == -1 ) {
      return true;
    }

    return false;
  }
}