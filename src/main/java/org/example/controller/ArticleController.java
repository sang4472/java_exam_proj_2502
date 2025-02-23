package org.example.controller;

import org.example.dto.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
  private Scanner sc;
  private List<Article> articles;

  public ArticleController(Scanner sc, List<Article> articles) {
    this.sc = sc;
    this.articles = articles;
  }

  public void showList(String cmd) {
    if (articles.size() == 0 ) {
      System.out.println("게시물이 없습니다.");
      return;
    }

    String searchKeyword = cmd.substring("article list".length()).trim();

    List<Article> forListArticles = articles;

    if ( searchKeyword.length() > 0 ) {
      forListArticles = new ArrayList<>();

      for ( Article article : articles ) {
        if ( article.title.contains(searchKeyword) ) {
          forListArticles.add(article);
        }
      }
    }

    if ( forListArticles.size() == 0 ) {
      System.out.println("검색 결과가 존재하지 않습니다");
      return;
    }

    System.out.println("번호 | 조회 | 제목");
    for ( int i = forListArticles.size() - 1; i >= 0; i-- ) {
      Article article = forListArticles.get(i);

      System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);
    }
  }

  public void doWrite() {
    int id = articles.size() + 1;
    String regDate = Util.getNotDateStr();
    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String body = sc.nextLine();

    Article article = new Article(id, regDate, title, body);

    articles.add(article);

    System.out.printf("%d번 글이 작성되었습니다.\n", id);
  }

  public void showDetail(String cmd) {
    String[] cmdBits = cmd.split(" ");
    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = getArticleById(id);

    if ( foundArticle == null ) {
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

  private Article getArticleById(int id) {
    int index = getArticleIndexById(id);

    if ( index != -1 ) {
      return articles.get(index);
    }

    return null;
  }

  public void doModify(String cmd) {
    String[] cmdBits = cmd.split(" ");
    int id = Integer.parseInt(cmdBits[2]);

    Article foundArticle = getArticleById(id);

    if ( foundArticle == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
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

  public void doDelete(String cmd) {
    String[] cmdBits = cmd.split(" ");
    int id = Integer.parseInt(cmdBits[2]);

    int foundIndex = getArticleIndexById(id);

    if ( foundIndex == -1 ) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articles.remove(foundIndex);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

  private int getArticleIndexById(int id) {
    int i = 0;

    for ( Article article : articles ) {
      if ( article.id == id ) {
        return i;
      }

      i++;
    }

    return -1;
  }

}