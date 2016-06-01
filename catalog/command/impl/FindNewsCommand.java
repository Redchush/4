package catalog.command.impl;

import catalog.command.Command;
import catalog.domain.entity.News;
import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;
import catalog.domain.mediation.Result;
import catalog.service.ServiceFactory;
import catalog.service.except.ServiceException;
import catalog.service.news.INewsService;

/**
 * Created by Student on 31-May-16.
 */
public class FindNewsCommand implements Command {
    public static final String ERROR_MSG = "Can't operate with this data : ";
    @Override
    public Response execute(Request request) {
        ServiceFactory factory = ServiceFactory.getInstance();
        Response response = new Response();
        Result<News> result;
        INewsService service;
        try {
            service = factory.getINewsService(ServiceFactory.NEWS_HANDLER);
            System.out.println("In find command");
            System.out.println(request);
            result = service.findNews(request.getParameterMap());

            response.setStatus(Response.OK);
            response.setMessage(result);

        } catch (ServiceException e) {
            response.setStatus(Response.BAD_REQUEST);
            response.setErrorMessage(ERROR_MSG + e.getMessage());
        }
        return response;
    }
}
