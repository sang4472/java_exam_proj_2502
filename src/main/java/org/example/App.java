package org.example;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.ExportController;
import org.example.controller.MemberController;

import java.util.Scanner;

public class App {
  public void start() {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in);

    MemberController memberController = new MemberController(sc);
    ArticleController articleController = new ArticleController(sc);
    ExportController exportController = new ExportController(sc);
    articleController.makeTestData();
    memberController.makeTestData();

    while (true) {
      System.out.print("명령어) ");
      String cmd = sc.nextLine();
      cmd = cmd.trim();

      if (cmd.length() == 0) {
        continue;
      }

      if (cmd.equals("exit")) {
        break;
      }

      String[] cmdBits = cmd.split(" "); // article detail 1

      if (cmdBits.length == 1) {
        System.out.println("존재하지 않는 명령어 입니다.");
        continue;
      }

      String controllerName = cmdBits[0]; // article/member
      String actionMethodName = cmdBits[1]; // detail/join

      Controller controller = null;

      if (controllerName.equals("article")) {
        controller = articleController;
      } else if (controllerName.equals("member")) {
        controller = memberController;
      } else if (controllerName.equals("export")) {
        controller = exportController;
      } else {
        System.out.println("존재하지 않는 명령어 입니다.");
        continue;
      }
      // article/member    //   detail/login
      String actionName = controllerName + "/" + actionMethodName;
      // article/detail           memer/login

      switch (actionName) {
        case "article/write":
        case "article/delete":
        case "article/modify":
        case "member/logout":
          if ( Controller.isLogined() == false ) {
            System.out.println("로그인 후 이용해주세요.");
            continue;
          }
          break;
      }

      switch (actionName) {
        case "member/join":
        case "member/login":
          if ( Controller.isLogined() ) {
            System.out.println("로그아웃 후 이용해주세요.");
            continue;
          }
          break;
      }


      controller.doAction(cmd, actionMethodName);
    }

    sc.close();
    System.out.println("== 프로그램 끝 ==");
  }
}