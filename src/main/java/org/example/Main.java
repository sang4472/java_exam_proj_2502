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

    makeTestData();

    while (true) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();
      cmd = cmd.trim();

      if (cmd.length() == 0) {
        continue;
      }

      if (cmd.equals("exit")) {
        break;
      } else if (cmd.startsWith("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시물이 없습니다.");
          continue;
        }
          //article list 제목 1 할때 제목 1에서  띄어쓰기 몇번 할줄 모르니까 cmd.substring 선언
        String searchKeyword = cmd.substring("article list".length()).trim();//게시물 리스트에 검색 기능 추가
        //article list라고 입력하면 길이를 구하면서 양옆에 공백을 지워준다
        List<Article> forListArticle = articles;//게시물 리스트에 검색 기능 추가
        //기존 articles를 forListArticle에 넣어준다
        if (searchKeyword.length() > 0) {//게시물 리스트에 검색 기능 추가,써치 키워드가 있다,제목, 안녕, 잘가 중에 안녕이라고 검색하면 안녕만 있다
          forListArticle = new ArrayList<>();//forListArticle에다가 새로운 배열을 만들어준다

          for (Article article : articles) {//articles 만큼 돌려야 한다 그중에
            if (article.title.contains(searchKeyword)) {//제목 키워드에 포함되어있다면
              forListArticle.add(article);//해당 article을 list에 담겠다.
            }
          }
        }
        if (forListArticle.size() == 0) {//게시물을 입력했지만 해당 입력한글이 없을경우
          System.out.println("검색 결과가 존재하지 않습니다");
          continue;
        }

        System.out.println("번호 | 조회 | 제목");// 조회수 기능 추가
        for (int i = forListArticle.size() - 1; i >= 0; i--) {
          Article article = forListArticle.get(i);

          System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);// 조회수 기능 추가,%4d -> 4칸 먼저 확보하고 출력
        }
      } else if (cmd.equals("article write")) {

        int id = articles.size() + 1;//GIT, 게시물 작성 시 작성날짜도 저장
        String regDate = Util.getNotDateStr();//GIT, 게시물 작성 시 작성날짜도 저장
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();


        Article article = new Article(id, regDate, title, body);//GIT, 게시물 작성 시 작성날짜도 저장

        articles.add(article);

        System.out.printf("%d번 글이 작성되었습니다.\n", id);
      } else if (cmd.startsWith("article detail ")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]); // "1" => 1

        // article detail 1
        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
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
      } else if (cmd.startsWith("article modify ")) {//게시물 수정 기능 구현
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]); // "1" => 1

        // article detail 1
        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
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

      } else if (cmd.startsWith("article delete ")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        int foundIndex = getArticleIndexById(id);


        if (foundIndex == -1) {
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

  private static Article getArticleById(int id) {
    int index = getArticleIndexById(id);

    if (index != 1) {
      return articles.get(index);
    }

    return null;
  }

  private static int getArticleIndexById(int id) {//같은 static끼리 소통됨
    int i = 0;

    for (Article article : articles) { //향상된 for문
      if (article.id == id) {
        return i;
      }

      i++;//인덱스값을 못찾아서
    }
    return -1;
  }

  private static void makeTestData() {
    System.out.println("테스트 데이터를 생성합니다.");

    articles.add(new Article(1, Util.getNotDateStr(), "제목 1", "내용 1", 10));
    articles.add(new Article(2, Util.getNotDateStr(), "제목 2", "내용 2", 345));
    articles.add(new Article(3, Util.getNotDateStr(), "제목 3", "내용 3", 78));
  }
}

class Article {
  int id;
  String regDate;//GIT, 게시물 작성 시 작성날짜도 저장
  String title;
  String body;
  int hit;// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가


  public Article(int id, String regDate, String title, String body, int hit) {//hit 까지 있을땐 여기로
    this.id = id;
    this.regDate = regDate;//GIT, 게시물 작성 시 작성날짜도 저장
    this.title = title;
    this.body = body;
    this.hit = hit;
  }

  public Article(int id, String regDate, String title, String body) {//body까지 있을땐 여기로
    this(id, regDate, title, body, 0);
  }

  public void increaseHit() {// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가
    hit++;
  }
}