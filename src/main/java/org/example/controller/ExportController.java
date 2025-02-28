package org.example.controller;

import org.example.container.Container;
import org.example.service.ExportService;

import java.util.Scanner;

public class ExportController extends Controller {
  private Scanner sc;
  private String cmd;
  private ExportService exportService;

  public ExportController(Scanner sc) {
    this.sc = sc;
    exportService = Container.exportService;
  }

  public void doAction(String cmd, String actionMethodName) {
    this.cmd = cmd;

    switch (actionMethodName) {
      case "html":
        doHtml();
        break;
      default:
        System.out.println("존재하지 않는 명령어 입니다");
        break;
    }
  }

  private void doHtml() {
    System.out.println("== html 생성을 시작합니다. ==");
    exportService.makeHtml();
  }

  public void makeTestData() {
  }
}