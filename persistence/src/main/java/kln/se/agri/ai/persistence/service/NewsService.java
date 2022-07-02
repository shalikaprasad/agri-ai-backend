package kln.se.agri.ai.persistence.service;

import kln.se.agri.ai.commons.model.News;

import java.util.List;

public interface NewsService {

    News createNews(News news);

    News updateNews(News news);

    void deleteNews(Long id);

    List<News> findNewsByCount(int count);

    News find(Long id);
}
