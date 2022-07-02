package kln.se.agri.ai.persistence.service.impl;

import kln.se.agri.ai.commons.model.News;
import kln.se.agri.ai.persistence.dao.NewsDao;
import kln.se.agri.ai.persistence.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public News createNews(News news) {
        return newsDao.create(news);
    }

    @Override
    public News updateNews(News news) {
        return newsDao.update(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsDao.delete(id);
    }

    @Override
    public List<News> findNewsByCount(int count) {
        return newsDao.findNewsByCount(count);
    }

    @Override
    public News find(Long id) {
        return newsDao.find(id);
    }
}
