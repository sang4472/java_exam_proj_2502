package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);//스캐너 객체 만드는법

    List<Article> articles = new ArrayList<>();//5,<Article>만 저장
    int lastArticleId = 0;

    while (true) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();
      cmd = cmd.trim();

      if (cmd.length() == 0) {
        continue;
      }

      if (cmd.equals("exit")) {
        break;
      } else if (cmd.equals("article list")) {
        System.out.println("게시물이 없습니다.");
        if (articles.size() == 0) {//6,아무것도 없을때 다음 출력
          System.out.println("게시물이 없습니다.");
          continue;
        }
        System.out.println("번호 | 제목");//8

        for (int i = articles.size() - 1; i >= 0; i--) {//7,-1해야 index 전까지 가야된다,저장한것이 최신게 먼저 나온다
          Article article = articles.get(i);

          System.out.printf("%d | %s\n", article.id, article.title);//9
        }
      } else if (cmd.equals("article write")) {
        int id = lastArticleId + 1;
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        lastArticleId = id;

        Article article = new Article(id, title, body);//2,class 만든다음 객체생성, 생성자가 없으니까 3번에 만듬

        articles.add(article);//4

        System.out.printf("%d번 글이 작성되었습니다.\n", id);
      }
    }

    sc.close();
    System.out.println("== 프로그램 끝 ==");
  }
}

class Article {//1,제목과 내용을 저장
  int id;
  String title;
  String body;

  public Article(int id, String title, String body) {//3
    this.id = id;
    this.title = title;
    this.body = body;
  }
}