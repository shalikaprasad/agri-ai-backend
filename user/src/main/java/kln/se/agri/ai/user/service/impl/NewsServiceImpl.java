package kln.se.agri.ai.user.service.impl;

import kln.se.agri.ai.dashboard.model.News;
import kln.se.agri.ai.dashboard.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
