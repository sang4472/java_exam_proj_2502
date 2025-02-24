package org.example;

import org.example.controller.ArticleController;
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
      else if ( cmd.equals("member join") ) {
        memberController.doJoin();
      }
      else if ( cmd.startsWith("article list") ) {
        articleController.showList(cmd);
      }
      else if ( cmd.equals("article write") ) {
        articleController.doWrite();
      }
      else if ( cmd.startsWith("article detail ") ) {
        articleController.showDetail(cmd);
      }
      else if ( cmd.startsWith("article modify ") ) {
        articleController.doModify(cmd);
      }
      else if ( cmd.startsWith("article delete ") ) {
        articleController.doDelete(cmd);
      }

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