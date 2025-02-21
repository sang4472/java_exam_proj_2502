package org.example;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("==프로그램 시작==");
    Scanner sc = new Scanner(System.in);//스캐너 객체 만드는법,

    System.out.print("명령어) ");
    String cmd = sc.nextLine();//입력하는것,띄어쓰기 까지 받는게 가능
    System.out.printf("입력된 명령어 : %s\n", cmd);


    sc.close();//스캐너 닫기(안하면오류)



    System.out.println("==프로그램 끝==");
  }
}