package org.example.repository;

import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  public List<Article> articles;

  public ArticleRepository() {
    articles = new ArrayList<>();
  }
}