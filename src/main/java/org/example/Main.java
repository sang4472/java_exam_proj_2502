package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);//스캐너 객체 만드는법

    List<Article> articles = new ArrayList<>();

    int lastArticleId = 0;

    while (true) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();//입력하는것,띄어쓰기 까지 받는게 가능
      cmd = cmd.trim();//양옆공백제거

      if (cmd.length() == 0) {//길이가 0이어도 통과, 넘어가라
        continue;
      }

      if (cmd.equals("exit")) {//exit 입력시 컴파일 종료
        break;
      } else if (cmd.equals("article list")) {//입력란에 article list 입력시 다음 출력
        if (articles.size() == 0) {
          System.out.println("게시물이 없습니다.");
          continue;
        }

        System.out.println("번호 | 제목");
        for (int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);

          System.out.printf("%d | %s\n", article.id, article.title);

        }
      } else if (cmd.equals("article write")) {//입력란에 article write 입력시 제목과 내용이 출력하면서 입력가능
        int id = lastArticleId + 1;//lastArticleId를 0으로 초기값을 설정을 해두어서 +1
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        Article article = new Article(id, title, body);

        lastArticleId = id;//1저장,2저장,3저장

        articles.add(article);

        System.out.printf("%d번 글이 작성되었습니다.\n", id);
      }
      //else if (cmd.equals("article detail 1")) {//article detail 1문장이랑 비교하는건데 1이 다를수밖에 없어 다음으로 바꿈
      else if (cmd.startsWith("article detail ")) {//startsWith: cmd가 article detail이 문장으로 시작하는가의 의미
        String[] cmdBits = cmd.split(" ");//조각
        //article [0]
        //detail [1]
        //1 [2]
        int id = Integer.parseInt(cmdBits[2]);// "1"=>1

        //int id = Integer.parseInt("1");//문자 1를 parseInt를 넣어주면
        //"1"=> 1 깨끗한 정수 1로 변환해서 id에 넣어준다

        Article foundArticle = null;//article detail 1

        for (int i = 0; i < articles.size(); i++) {//article 1번이 있는지 없는지 확인
          Article article = articles.get(i);

          if (article.id == id) {//참이면 저 쓰레기 값에 해당 article를 넣어준다
            foundArticle = article;
            break;
          }
        }

        if (foundArticle == null) {//foundArticle이 for문을 다 돌고 왔는데도 여전히 null이라면 존재않다는 뜻
          System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
          continue;
        }

        System.out.printf("번호 : %d\n", foundArticle.id);
        System.out.printf("날짜 : %s\n", "2025-12-12 12:12:12");
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);

      }

      else if (cmd.startsWith("article delete ")) {//startsWith: cmd가 article detail이 문장으로 시작하는가의 의미
        String[] cmdBits = cmd.split(" ");//조각
        int id = Integer.parseInt(cmdBits[2]);// "1"=>1


        int foundIndex = -1;//index 0부터 시작하니까 첫시작을 -1로, -1이면 못찾았다.

        for (int i = 0; i < articles.size(); i++) {//article 1번이 있는지 없는지 확인
          Article article = articles.get(i);

          if (article.id == id) {//참이면 저 쓰레기 값에 해당 article를 넣어준다
            foundIndex = i;//삭제 기능
            break;
          }
        }

        if (foundIndex == -1) {//foundIndex가 -1이랑 같으면 다음 출력
          System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
          continue;
        }

        articles.remove(foundIndex);
        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

      }
    }

    sc.close();//스캐너 닫기
    System.out.println("== 프로그램 끝 ==");
  }
}

class Article {
  int id;
  String title;
  String body;

  public Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }
}