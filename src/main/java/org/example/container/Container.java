package org.example.container;

import org.example.repository.ArticleRepository;
import org.example.repository.MemberRepository;
import org.example.service.ArticleService;
import org.example.service.ExportService;
import org.example.service.MemberService;

public class Container {
  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;
  public static ArticleService articleService;
  public static MemberService memberService;
  public static ExportService exportService;

  static {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();
    articleService = new ArticleService();
    memberService = new MemberService();
    exportService = new ExportService();
  }
}