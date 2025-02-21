package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Integer> nums = new ArrayList<>();
    while (true) {
      System.out.printf("명령어) ");
      String cmd = sc.nextLine();//입력하는것,띄어쓰기 까지 받는게 가능
      cmd = cmd.trim();
      if ( cmd.length() == 0 ) {//cmd의 길이가 0이여도 통과, 넘어가겠다
        continue;
      }
      if ( cmd.equals("exit") ) {//입력란에 exit 입력시 컴파일 종료
        break;
      }
      if ( cmd.equals("num") ) {//equals:객체 간의 내용(value)이 같은지 비교하는 메서드, num입력시 숫자로만 입력가능
        System.out.printf("숫자 : ");
        int num = sc.nextInt();
        sc.nextLine();
        nums.add(num);
      }
      else if ( cmd.equals("nums") ) {//
        System.out.printf("숫자들 : %s\n", nums.toString());
      }
    }
    sc.close();
  }
}