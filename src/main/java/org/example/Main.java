package org.example;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);//스캐너 객체 만드는법
    int lastArticleId = 0;

    while ( true ) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();//입력하는것,띄어쓰기 까지 받는게 가능
      cmd = cmd.trim();//양옆공백제거

      if ( cmd.length() == 0 ) {//길이가 0이어도 통과, 넘어가라
        continue;
      }

      if ( cmd.equals("exit") ) {//exit 입력시 컴파일 종료
        break;
      }
      else if ( cmd.equals("article list") ) {//입력란에 article list 입력시 다음 출력
        System.out.println("게시물이 없습니다.");
      }
      else if ( cmd.equals("article write") ) {//입력란에 article write 입력시 제목과 내용이 출력하면서 입력가능
        int id = lastArticleId + 1;//lastArticleId를 0으로 초기값을 설정을 해두어서 +1
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        lastArticleId = id;//1저장,2저장,3저장

        System.out.printf("%d번 글이 작성되었습니다.\n", id);
      }
    }

    sc.close();//스캐너 닫기
    System.out.println("== 프로그램 끝 ==");
  }
}