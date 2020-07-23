package kln.se.agri.ai.dashboard.dao;

import kln.se.agri.ai.commons.model.News;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public interface NewsDao extends CRUDDao<News> {

    List<News> findNewsByCount(int count);

}
