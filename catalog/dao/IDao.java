package catalog.dao;

import catalog.dao.exception.DAOException;
import catalog.domain.entity.Catalog;
import catalog.domain.entity.News;

/**
 * Created by user on 29.05.2016.
 */
public interface IDao {
    void saveNews(News news) throws DAOException;
    Catalog getCatalog() throws DAOException;
}


