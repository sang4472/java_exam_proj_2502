package org.example;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;
import org.example.dto.Article;
import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private List<Article> articles;
  private List<Member> members;

  public App() {
    articles = new ArrayList<>();
    members = new ArrayList<>();
  }

  public void start() {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);

    makeTestData();

    MemberController memberController = new MemberController(sc, members);
    ArticleController articleController = new ArticleController(sc, articles);

    while ( true ) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();
      cmd = cmd.trim();

      if ( cmd.length() == 0 ) {
        continue;
      }

      if ( cmd.equals("exit") ) {
        break;
      }

      String[] cmdBits = cmd.split(" "); // article detail 1

      if ( cmdBits.length == 1 ) {
        System.out.println("존재하지 않는 명령어 입니다.");
        continue;
      }

      String controllerName = cmdBits[0]; // article/member
      String actionMethodName = cmdBits[1]; // detail/join

      Controller controller = null;

      if (controllerName.equals("article") ) {
        controller = articleController;
      }
      else if (controllerName.equals("member") ) {
        controller = memberController;
      }
      else {
        System.out.println("존재하지 않는 명령어 입니다.");
        continue;
      }
      controller.doAction(cmd, actionMethodName);

    }

    sc.close();
    System.out.println("== 프로그램 끝 ==");
  }


  private void makeTestData() {
    System.out.println("테스트 데이터를 생성합니다.");

    articles.add(new Article(1, Util.getNotDateStr(), "제목 1", "내용 1", 10));
    articles.add(new Article(2, Util.getNotDateStr(), "제목 2", "내용 2", 345));
    articles.add(new Article(3, Util.getNotDateStr(), "제목 3", "내용 3", 78));
  }
}