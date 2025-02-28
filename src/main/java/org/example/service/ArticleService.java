package org.example.service;

import org.example.container.Container;
import org.example.dto.Article;
import org.example.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    this.articleRepository = Container.articleRepository;
  }

  public List<Article> getForPrintArticles(String searchKeyword) {
    return articleRepository.getArticles(searchKeyword);
  }

  public List<Article> getForPrintArticles() {
    return articleRepository.getArticles(null);
  }

  public void add(Article article) {
    articleRepository.add(article);
  }

  public Article getArticleById(int id) {
    return articleRepository.getArticleById(id);
  }

  public void remove(Article foundArticle) {
    articleRepository.remove(foundArticle);
  }
}