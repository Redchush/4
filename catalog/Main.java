package catalog;

import catalog.controller.Controller;
import catalog.dao.IDao;
import catalog.dao.exception.DAOException;
import catalog.dao.imp.DaoFactory;
import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;
import catalog.view.View;
import catalog.view.impl.ViewFactory;

/**
 * Created by user on 29.05.2016.
 */
public class Main {
    public static void main(String[] args) throws DAOException {
        IDao dao =  DaoFactory.getInstance().getDao(DaoFactory.xmlType);
       // View view = ViewFactory.getInstance().getView(ViewFactory.SWING);
//        Controller controller = new Controller();
//
//        Request request = view.doUserAction();
//     Response response = controller.doAction(request);
        View view = ViewFactory.getInstance().getView(ViewFactory.CONSOLE);
        Controller controller = new Controller();
        while (true) {
            Request request = view.doUserAction();
            Response response = controller.doAction(request);
            view.printAnswer(response);
        }

//
// "http://www.w3.org/2001/XMLSchema-instance"
// EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
// лит
    }
}
