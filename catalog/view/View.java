package catalog.view;

import catalog.domain.mediation.Request;
import catalog.domain.mediation.Response;

/**
 * Created by user on 29.05.2016.
 */
public interface View {
    public Request doUserAction();
    public void printAnswer(Response response);
    public void printAnswer(String message);

}
