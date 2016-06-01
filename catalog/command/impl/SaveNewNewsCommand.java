package catalog.command.impl;



import catalog.command.Command;
import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;
import catalog.service.ServiceFactory;
import catalog.service.except.ServiceException;
import catalog.service.news.INewsService;

/**
 * Created by Student on 31-May-16.
 */
public class SaveNewNewsCommand  implements Command{

    private static final String SUCCESFULL_ADDING = "You news was added succesfully";
    public static final String ERROR_MSG = "You data in bad format : ";

    public Response execute(Request request){
        ServiceFactory factory = ServiceFactory.getInstance();
        Response response = new Response();
        INewsService service;
        try {
            System.out.println("In Response");
            System.out.println(request.getParameterMap());

            service = factory.getINewsService(ServiceFactory.NEWS_HANDLER);
            service.saveNewNews(request.getParameterMap());

            response.setStatus(Response.DONE);
            response.setMessage(SUCCESFULL_ADDING);


        } catch (ServiceException e) {
            response.setStatus(Response.BAD_REQUEST);
            response.setErrorMessage(ERROR_MSG);

        }
        return response;
    }



}
