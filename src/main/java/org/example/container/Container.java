package org.example.container;

import org.example.repository.ArticleRepository;
import org.example.repository.MemberRepository;

public class Container {
  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;

  static {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();
  }
}