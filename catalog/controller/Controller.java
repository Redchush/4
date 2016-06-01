package catalog.controller;

import catalog.command.Command;
import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;

/**
 * Created by user on 30.05.2016.
 */
public class Controller {
    CommandHelper helper = new CommandHelper();

    public Response doAction(Request request){
        String commandNAme = request.getCommandName();
        Command command = helper.getCommand(commandNAme);
        Response response = command.execute(request);
        return response;
    }
}
