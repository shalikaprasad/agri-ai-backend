package kln.se.agri.ai.dashboard.dao.hibernate;

import kln.se.agri.ai.commons.model.News;
import kln.se.agri.ai.persistence.dao.NewsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("newsDao")
@Transactional
public class NewsDaoImpl extends CRUDDaoImpl<News> implements NewsDao {

    private final static Logger logger = LoggerFactory.getLogger(NewsDaoImpl.class);

    @Override
    public List<News> findNewsByCount(int count) {
        try{
            return (List<News>) entityManager.createNamedQuery(News.GET_NEWS_BY_COUNT)
                    .setParameter("count", count)
                    .getResultList();
        }catch (Exception e){
            logger.error(">> No News for given number::", e);
            return null;
        }
    }
}
