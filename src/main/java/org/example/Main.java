package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static List<Article> articles; //private쓰는 이유 같은 이름이 나올수 있으니까

  static {
    articles = new ArrayList<>();
  }

  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);

    int lastArticleId = 0;

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
      else if ( cmd.equals("article list") ) {
        if (articles.size() == 0 ) {
          System.out.println("게시물이 없습니다.");
          continue;
        }

        System.out.println("번호 | 조회 | 제목");// 조회수 기능 추가
        for ( int i = articles.size() - 1; i >= 0; i-- ) {
          Article article = articles.get(i);

          System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);// 조회수 기능 추가,%4d -> 4칸 먼저 확보하고 출력
        }
      }
      else if ( cmd.equals("article write") ) {
        int id = lastArticleId + 1;
        lastArticleId = id;//GIT, 게시물 작성 시 작성날짜도 저장
        String regDate = Util.getNotDateStr();//GIT, 게시물 작성 시 작성날짜도 저장
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        lastArticleId = id;
        Article article = new Article(id, regDate, title, body);//GIT, 게시물 작성 시 작성날짜도 저장

        articles.add(article);

        System.out.printf("%d번 글이 작성되었습니다.\n", id);
      }
      else if ( cmd.startsWith("article detail ") ) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]); // "1" => 1

        // article detail 1
        Article foundArticle = getArticleById(id);

        if ( foundArticle == null ) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }

        foundArticle.increaseHit();//increaseHit증가, 게시물 리스팅 출력 형식 수정, 조회수 기능 추가

        System.out.printf("번호 : %d\n", foundArticle.id);
        System.out.printf("날짜 : %s\n", "2025-12-12 12:12:12");
        System.out.printf("날짜 : %s\n", foundArticle.regDate);//GIT, 게시물 작성 시 작성날짜도 저장
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);
        System.out.printf("조회 : %d\n", foundArticle.hit);// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가
      }

      else if ( cmd.startsWith("article modify ") ) {//게시물 수정 기능 구현
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]); // "1" => 1

        // article detail 1
        Article foundArticle = getArticleById(id);

        if ( foundArticle == null ) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }

        System.out.printf("제목 : ");//게시물 수정 기능 구현
        String title = sc.nextLine();//게시물 수정 기능 구현
        System.out.printf("내용 : ");//게시물 수정 기능 구현
        String body = sc.nextLine();//게시물 수정 기능 구현

        foundArticle.title = title;//게시물 수정 기능 구현
        foundArticle.body = body;//게시물 수정 기능 구현

        System.out.printf("%d번 게시물이 수정되었습니다.", id);//게시물 수정 기능 구현

      }

      else if ( cmd.startsWith("article delete ") ) {//index값 찾는것
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        int foundIndex = getArticleIndexById(id);// 특정 게시물 찾는 메서드를 구현해서 중복 제거



        if ( foundIndex == -1 ) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }

        articles.remove(foundIndex);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
      }

    }

    sc.close();
    System.out.println("== 프로그램 끝 ==");
  }

  private static Article getArticleById(int id) {// 특정 게시물 찾는 메서드를 구현해서 중복 제거
    int index = getArticleIndexById(id);//특정 게시물 찾는 메서드를 구현해서 중복 제거

    if(index != 1) {//특정 게시물 찾는 메서드를 구현해서 중복 제거
      return articles.get(index);//특정 게시물 찾는 메서드를 구현해서 중복 제거
    }

    return null;
  }

  private static int getArticleIndexById(int id) {//같은 static끼리 소통됨, 특정 게시물 찾는 메서드를 구현해서 중복 제거
    int i = 0;// 특정 게시물 찾는 메서드를 구현해서 중복 제거

    for (Article article : articles) { //향상된 for문,특정 게시물 찾는 메서드를 구현해서 중복 제거
      if (article.id == id) {//특정 게시물 찾는 메서드를 구현해서 중복 제거
        return i;//특정 게시물 찾는 메서드를 구현해서 중복 제거
      }

      i++;//인덱스값을 못찾아서
    }
    return -1;//특정 게시물 찾는 메서드를 구현해서 중복 제거
  }
}

class Article {
  int id;
  String regDate;//GIT, 게시물 작성 시 작성날짜도 저장
  String title;
  String body;
  int hit;// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가


  public Article( int id, String regDate, String title, String body){
    this.id = id;
    this.regDate = regDate;//GIT, 게시물 작성 시 작성날짜도 저장
    this.title = title;
    this.body = body;
    this.hit = 0;// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가
  }

  public void increaseHit() {// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가
    hit++;
  }
}