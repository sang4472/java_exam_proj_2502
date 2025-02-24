package org.example.dto;

public class Article extends Dto {
  public String title;
  public String body;
  public int hit;// 게시물 리스팅 출력 형식 수정, 조회수 기능 추가


  public Article  (int id, String regDate, String title, String body, int hit) {//hit 까지 있을땐 여기로
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