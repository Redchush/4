package catalog.service;

import catalog.service.except.ServiceException;
import catalog.service.news.INewsService;
import catalog.service.news.impl.INewsServiceImpl;

/**
 * Created by user on 31.05.2016.
 */
public class ServiceFactory {
    public static final String NEWS_HANDLER = "NEWS_HANDLER";

    private static ServiceFactory instance;
    private ServiceFactory(){};

    public static ServiceFactory getInstance() {
        if (instance == null)
        {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public INewsService getINewsService(String type) throws ServiceException {
        switch (type){
            case NEWS_HANDLER:
                return new INewsServiceImpl();
        }
        throw new ServiceException("The Service type " + type + " isn't exist");
    }

}
