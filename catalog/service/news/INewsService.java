package catalog.service.news;

import catalog.domain.entity.News;
import catalog.domain.mediation.Criteria;
import catalog.domain.mediation.Result;
import catalog.service.except.ServiceException;

import java.util.NavigableMap;

/**
 * Created by user on 29.05.2016.
 */
public interface INewsService {
    void saveNewNews(NavigableMap<Criteria, String> criteria) throws ServiceException;
    Result<News> findNews(NavigableMap<Criteria, String> criteria) throws ServiceException;
}
