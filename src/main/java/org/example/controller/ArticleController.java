package org.example.controller;

import org.example.container.Container;
import org.example.dto.Article;
import org.example.dto.Member;
import org.example.service.ArticleService;
import org.example.util.Util;

import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
  private Scanner sc;
  private String cmd;
  private String actionMethodName;
  private ArticleService articleService;

  public ArticleController(Scanner sc) {
    this.sc = sc;
    articleService = Container.articleService;
  }

  public void makeTestData() {
    System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");

    Container.articleRepository.add(new Article(Container.articleRepository.getNewId(), Util.getNotDateStr(), 1,  "제목 1", "내용 1", 10));
    Container.articleRepository.add(new Article(Container.articleRepository.getNewId(), Util.getNotDateStr(), 2,  "제목 2", "내용 2", 345));
    Container.articleRepository.add(new Article(Container.articleRepository.getNewId(), Util.getNotDateStr(), 2,  "제목 3", "내용 3", 78));
  }

  public void doAction(String cmd, String actionMethodName) {
    this.cmd = cmd;
    this.actionMethodName = actionMethodName;

    switch (actionMethodName) {
      case "write":
        doWrite();
        break;
      case "list":
        showList();
        break;
      case "detail":
        showDetail();
        break;
      case "modify":
        doModify();
        break;
      case "delete":
        doDelete();
        break;
      default:
        System.out.println("존재하지 않는 명령어입니다.");
        break;
    }
  }

  public void showList() {
    String searchKeyword = cmd.substring("article list".length()).trim();

    List<Article> forListArticles = articleService.getForPrintArticles(searchKeyword);

    if ( forListArticles.size() == 0 ) {
      System.out.println("검색결과가 존재하지 않습니다.");
      return;
    }

    System.out.println("번호 |  작성자  | 조회 | 제목");
    for (int i = forListArticles.size() - 1; i >= 0; i--) {
      Article article = forListArticles.get(i);

      String writerName = null;

      List<Member> members = Container.memberRepository.members;

      for ( Member member : members ) {
        if ( article.memberId == member.id ) {
          writerName = member.name;
          break;
        }
      }

      System.out.printf("%4d | %6s | %4d | %s\n", article.id, writerName, article.hit, article.title);
    }
  }

  public void doWrite() {
    int id = Container.articleRepository.getNewId();
    String regDate = Util.getNotDateStr();
    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String body = sc.nextLine();

    Article article = new Article(id, regDate, loginedMember.id, title, body);

    articleService.add(article);

    System.out.printf("%d번 글이 작성되었습니다.\n", id);
  }

  public void showDetail() {
    String[] cmdBits = cmd.split(" ");

    if (cmdBits.length <= 2) {
      System.out.println("게시물 번호를 입력해주세요.");
      return;
    }

    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = articleService.getArticleById(id);

    if (foundArticle == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    foundArticle.increaseHit();

    System.out.printf("번호 : %d\n", foundArticle.id);
    System.out.printf("날짜 : %s\n", foundArticle.regDate);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.body);
    System.out.printf("조회 : %d\n", foundArticle.hit);
  }

  public void doModify() {
    String[] cmdBits = cmd.split(" ");

    if (cmdBits.length <= 2) {
      System.out.println("게시물 번호를 입력해주세요.");
      return;
    }

    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = articleService.getArticleById(id);

    if (foundArticle == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    if ( foundArticle.memberId != loginedMember.id ) {
      System.out.println("권한이 없습니다.");
      return;
    }

    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String body = sc.nextLine();

    foundArticle.title = title;
    foundArticle.body = body;

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete() {
    String[] cmdBits = cmd.split(" ");

    if (cmdBits.length <= 2) {
      System.out.println("게시물 번호를 입력해주세요.");
      return;
    }

    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = articleService.getArticleById(id);

    if (foundArticle == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    if ( foundArticle.memberId != loginedMember.id ) {
      System.out.println("권한이 없습니다.");
      return;
    }

    articleService.remove(foundArticle);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }



}