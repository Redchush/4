package catalog.domain.mediation;

import java.util.*;

/**
 * Created by user on 31.05.2016.
 */
public class Request {
    private String commandName;

    private NavigableMap<Criteria, String> parametersMap;

    public Request(NavigableMap<Criteria, String> parametersMap) {
        this.parametersMap = parametersMap;
    }

    public Request() {
    }

    public void setCommandName(String name) {
        this.commandName = name;
    }

    public String getCommandName() {
        return commandName;
    }

    // as map

    public Collection<String> getParameterNames(){
         return parametersMap.values();
    }
    public Set<Criteria> getParameterValues(String name){
        return parametersMap.keySet();
    }
    public NavigableMap<Criteria, String> getParameterMap(){
        return parametersMap;
    }
    public void setParametersMap(NavigableMap<Criteria, String> parametersMap) {
        this.parametersMap = parametersMap;
    }
    public String getParameter(Criteria name){
        return parametersMap.get(name);
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", parametersMap=" + parametersMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        if (commandName != null ? !commandName.equals(request.commandName) : request.commandName != null) return false;
        return parametersMap != null ? parametersMap.equals(request.parametersMap) : request.parametersMap == null;

    }

    @Override
    public int hashCode() {
        int result = commandName != null ? commandName.hashCode() : 0;
        result = 31 * result + (parametersMap != null ? parametersMap.hashCode() : 0);
        return result;
    }
}
