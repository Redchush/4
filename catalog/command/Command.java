package catalog.command;

import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;


public interface Command {
   Response execute(Request request);
}
