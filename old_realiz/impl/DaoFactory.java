package old_realiz.impl;

import catalog.dao.IDao;
import catalog.dao.exception.DAOException;


/**
 * Created by user on 29.05.2016.
 */
public class DaoFactory {
    public final static String xmlType = "XML";

    private static DaoFactory instance;
    private DaoFactory(){};

    public static DaoFactory getInstance() {
        if (instance == null)
        {
            instance = new DaoFactory();
        }
        return instance;
    }
    public IDao getDao(String daoType) throws DAOException {
        switch (daoType){
            case xmlType:
                return new DAOImpl();
        }
        throw new DAOException("There is no matching DaoType");
    }
}
