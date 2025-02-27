package org.example.repository;

import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository extends Repository {
  public List<Article> articles;

  public ArticleRepository() {
    articles = new ArrayList<>();
  }

  public void add(Article article) {
    articles.add(article);
    lastId++;
  }
}