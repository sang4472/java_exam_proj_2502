package org.example.container;

import org.example.repository.ArticleRepository;
import org.example.repository.MemberRepository;
import org.example.service.ArticleService;

public class Container {
  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;
  public static ArticleService articleService;

  static {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();
    articleService = new ArticleService();
  }
}